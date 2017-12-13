 - saveStudents

**请求方法**

POST

**请求参数格式**

```
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
```

**返回结果**


  - generateQuiz
 
**请求方法**

POST

**请求参数格式**
```
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
```

**返回结果**
```
{
    "responseStatus": 1,
    "responseBody": {
        "id": 14,
        "name": "test222",
        "startat": "2017-12-01 13:17:42",
        "endat": "2017-12-01 15:17:42",
        "questionCount": 1,
        "subject": "软件工程"
    }
}

```

  - generatePaper

**请求方法**

GET

**请求参数格式**

authcode    String

**返回结果**
```
{
    "question":[
        {
            "id":13,
            "title":"test",
            "allAnswers":[
                {
                    "id":49,
                    "content":"对",
                    "questionId":13,
                    "type":1
                },
                {
                    "id":50,
                    "content":"对",
                    "questionId":13,
                    "type":1
                }
            ],
            "answerNumber":3,
            "subject":"软件工程",
            "tag":"123"
        }
    ],
    "examid":{
        "id":14,
        "name":"test222",
        "startat":"2017-12-01 13:17:42.0",
        "endat":"2017-12-01 15:17:42.0",
        "questionCount":1,
        "subject":"????"
    },
    "userid":{
        "id":141250051,
        "name":"b",
        "email":"141250037@smail.nju.edu.cn",
        "role":"Student",
        "classnum":"201403"
    }
}


```

  - saveAnswerSheet
  
**请求方法**

POST

**请求参数格式**
```
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
```
**返回结果**

```
{
    "responseStatus": 1,
    "responseBody": [
        {
            "questionid": 10,
            "answerid": []
        }
    ]
}
```

  - getAnswers
  
**请求方法**

GET

**请求参数格式**

examid   int    考试id

userid  int 学生id


**返回结果**
```
{
"choices": [
  {
"examid": 12,
"userid": 141250051,
"questionid": 4,
"answerid": 9,
"problemnum": 1
},
  {
"examid": 12,
"userid": 141250051,
"questionid": 4,
"answerid": 10,
"problemnum": 1
},
  {
"examid": 12,
"userid": 141250051,
"questionid": 5,
"answerid": 17,
"problemnum": 2
}
],
```

  - getAllClasses
  
**请求方法**

GET

**请求参数格式**


**返回结果**
```

[
  "201401",
  "201403",
  "??",
  "班级"
],
```
