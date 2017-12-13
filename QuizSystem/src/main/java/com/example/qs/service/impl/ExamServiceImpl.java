package com.example.qs.service.impl;

import com.example.qs.Util.DateUtil;
import com.example.qs.Util.Encrypt;
import com.example.qs.dao.ChoiceDao;
import com.example.qs.dao.ExamDao;
import com.example.qs.dao.ScorevalueDao;
import com.example.qs.entity.*;
import com.example.qs.service.ExamService;
import com.example.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamDao examDao;

    @Autowired
    private ScorevalueDao scorevalueDao;

    @Autowired
    private ChoiceDao choiceDao;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Value("${application.es.name}")
    private String esName;

    @Value("${application.qc.name}")
    private String qcName;

    @Override
    public ResponseInfo generateQuiz(Quiz quiz) {

        Exam exam = new Exam();
        exam.setName(quiz.getName());
        exam.setStartat(DateUtil.transferDate(quiz.getStart_time()));
        exam.setEndat(DateUtil.calEndTime(quiz.getStart_time(), quiz.getLast_time()));
        exam.setQuestionCount(quiz.getProblem_count());
        exam.setSubject(quiz.getSubject());

        exam = examDao.save(exam);

        /*
        保存分值
         */
        saveScorevalues(exam, quiz.getScore_value());

        /*
        保存用户
         */
        List<User> candidates = new ArrayList<>();
        for(String s : quiz.getClasses()) {
            List<User> students = userService.showStudentsByClass(s);
            candidates.addAll(students);
        }
        userService.corelationExamUser(exam.getId(), candidates);

        /*
        发送密码
         */
        sendPasswords(exam, candidates);

        ResponseInfo responseBody = new ResponseInfo();
        responseBody.setResponseStatus(1);
        responseBody.setResponseBody(exam);
        return  responseBody;
    }

    public void saveScorevalues(Exam exam, List<Tag> scorevalue) {
        String subject = exam.getSubject();
        ServiceInstance serviceInstance = loadBalancerClient.choose(qcName);
        String baseurl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/getQuestions?subject="+subject;
        for(int i = 1;i<=scorevalue.size();i++) {
            Tag tag = scorevalue.get(i-1);
            List<String> tags = tag.getTags();
            int value = tag.getValue();
            for (String t : tags) {
                String url = baseurl+"&tag="+t;
                List<LinkedHashMap> questions = restTemplate.getForObject(url, List.class);
                for (LinkedHashMap q : questions) {
                    ScoreValue pk = new ScoreValue();
                    pk.setExamid(exam.getId());
                    pk.setQuestionid(Integer.parseInt(q.get("id").toString()));
                    pk.setProblemnum(i);
                    pk.setScorevalue(value);
                    scorevalueDao.save(pk);
                }
            }
        }

    }

    @Override
    public void sendPasswords(Exam exam, List<User> candidates) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(esName);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/sendEmail";
        for (User u : candidates) {
            String subject = exam.getName()+"考试密码";
            String content = Encrypt.encode(String.valueOf(exam.getId())+" "+String.valueOf(u.getId()));
            Email email = new Email();
            email.setSubject(subject);
            email.setContent(content);
            email.setReceiver(u.getEmail());
            restTemplate.postForEntity(url, email, String.class);
        }
    }

    @Override
    public Map<String, Object> generatePaper(String authcode) {
        Map<String, Object> result = new HashMap<>();

        String origin = Encrypt.decode(authcode);
//        String origin = "1 141250047";
        String[] temp = origin.split(" ");
        int examid = Integer.parseInt(temp[0]);
        int userid = Integer.parseInt(temp[1]);

        result.put("examid", examDao.findById(examid));
        result.put("userid",userService.findById(userid));
        ArrayList<Integer> questionIds = new ArrayList<>();
        List<ScoreValue> scoreValues = scorevalueDao.findByExamid(examid);
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < scoreValues.size(); i++) {
            if (!nums.contains(scoreValues.get(i).getProblemnum())){
                nums.add(scoreValues.get(i).getProblemnum());
            }
        }

        for (int i = 0; i < nums.size(); i++) {
            List<ScoreValue> values = scorevalueDao.findByExamidAndProblemnum(examid,nums.get(i));
            Random random = new Random();
            int index = random.nextInt(values.size());
            int count = 0;
            while (questionIds.contains(values.get(index).getQuestionid())){
                count ++;
                index = random.nextInt(values.size());
                if (count > 100){
                    break;
                }
            }
            questionIds.add(values.get(index).getQuestionid());
        }
        ServiceInstance serviceInstance = loadBalancerClient.choose(esName);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/getQuestionsByIDs";
        Vector<Question> questions = restTemplate.postForObject(url,questionIds,Vector.class);
        result.put("question",questions);
        return result;
    }

    @Override
    public ResponseInfo saveAnswer(Map<String, Object> choices) {

        int examid = Integer.parseInt(choices.get("examid").toString());
        int userid = Integer.parseInt(choices.get("userid").toString());

        List<Map<String, Object>> choice = (List<Map<String,Object>>) choices.get("choice");
        for(int i=1;i<=choice.size();i++) {
            Map<String, Object> object = choice.get(i-1);
            int questionid = Integer.parseInt(object.get("questionid").toString());
            List<Integer> answers = (List)object.get("answerid");
            for(Integer ans : answers) {
                Choice pk = new Choice();
                pk.setExamid(examid);
                pk.setUserid(userid);
                pk.setQuestionid(questionid);
                pk.setAnswerid(ans);
                pk.setProblemnum(i);
                choiceDao.save(pk);
            }
        }
        ResponseInfo responseBody = new ResponseInfo();
        responseBody.setResponseStatus(1);
        responseBody.setResponseBody(choice);
        return responseBody;
    }

    @Override
    public Map<String, Object> getAnswers(int examid, int userid) {
        ChoicePK pk = new ChoicePK();
        pk.setExamid(examid);
        pk.setUserid(userid);
        List<Choice> choices = choiceDao.findByExamidAndUserid(examid, userid);
        Map<String, Object> res = new HashMap<>();
        res.put("choices",choices);
        return res;
    }

}
