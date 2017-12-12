###getScore
**request:**  
get   
**name, type, description**  
userid, int  
quizid, int  
**url**  
/getScore?userid=141250050&quizid=5  
**result**  
[{"exam_id":5,"user_id":141250050,"score":0}]  
**url**  
/getScore?quizid=5  
**result**  
[{"exam_id":5,"user_id":141250050,"score":0},{"exam_id":5,"user_id":141250051,"score":0}]

###calScore
**request:**  
get   
**name, type, description**  
userid, int  
quizid, int  
**url**  
/calScore?userid=141250047&quizid=1  
**result**   
{"exam_id":1,"user_id":141250047,"total":4}  
**url**  
/getScore?quizid=1  
**result**   
[{"exam_id":1,"user_id":141250047,"total":4},{"exam_id":1,"user_id":141250050,"total":4}]  

###updateScore
**request:**  
get   
**name, type, description**  
userid, int  
quizid, int  
score, int  
**url**  
/updateScore?userid=141250047&quizid=1&score=100  
**result**   
success：1 false：0 此时只改变该次测试该名同学成绩为100分  
**url**  
/getScore?quizid=1&score=0  
**result**   
success：1 false：0 此时该次测试所有同学为0分