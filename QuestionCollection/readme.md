###getQuestionsByTagAndSubject

**request:**  GET   

**url:** /getQuestionsByTagAndSubject

**请求参数**

| 参数名称    | 参数类型   | 说明     |
| ------- | ------ | ------ |
| tag     | String | 试题的标签  |
| subject | String | 试题所属科目 |

**返回结果**

    [{
        "id": 1,
        "title": "hahaha",
        "allAnswers": [
            {
                "id": 1,
                "content": "对",
                "questionId": 1,
                "type": 1
            }
        ],
        "answerNumber": 1,
        "subject": "software",
        "tag": "tag"
    }]


###saveTheInputExcel

**request**: POST

**url:** /saveTheInputExcel

**请求参数**

| Body名称 | Body类型        | 说明         |
| ------ | ------------- | ---------- |
| file   | MultipartFile | 导入的Excel文件 |

**返回结果**

***success***:

```
[{
    "id": 1,
    "title": "hahaha",
    "allAnswers": [
        {
            "id": 1,
            "content": "对",
            "questionId": 1,
            "type": 1
        }
    ],
    "answerNumber": 1,
    "subject": "software",
    "tag": "tag"
}]
```

***failed***:

null

