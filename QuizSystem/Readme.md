 - saveStudents

**请求方法**
POST

**请求参数格式**
[
  {
    "email": "141250030@nju.edu.cn",
    "classnum": "201403",
    "name": "a"
  },
  {
    "email": "141250037@nju.edu.cn",
    "classnum": "201403",
    "name": "b"
  }
]
**返回结果**


  - generateQuiz
 
**请求方法**
POST

**请求参数格式**
{
    "name": "test",
    "subject": "软件工程",
    "start_time": "2017-12-09T13:17:42.000Z",
    "last_time": 120,
    "description": "",
    "problem_count": 2,
    "score_value": [
        {
            "tags": [
                "123"
            ],
            "value": 50
        },
        {
            "tags": [
                "123"
            ],
            "value": 50
        }
    ],
    "classes": [
        "201403"
    ]
}
**返回结果**

  - generatePaper

**请求方法**
GET

**请求参数格式**
authcode:String
**返回结果**
{
"examid": 12,
"questions": [],
"userid": 141250051
}

  - saveAnswerSheet
**请求方法**
POST

**请求参数格式**
{
    "examid": 12,
    "userid": 141250051,
    "choice": [
        {
            "questionid": 4,
            "answerid": [9,10]
        },
        {
            "questionid":5,
            "answerid": [17]
        }
    ]
}
**返回结果**


