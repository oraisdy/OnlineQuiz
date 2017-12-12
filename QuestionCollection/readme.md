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

### getQuestionsByIDs

**request**: POST

**url:** /getQuestionsByIDs

**请求参数**

| Body名称 | Body类型             | 说明            |
| ------ | ------------------ | ------------- |
| ids    | ArrayList<Integer> | 传入的questionId |

**返回结果**

***success***:

```
{
    "examid": 1,
    "questions": [
        {
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
        }
    ],
    "userid": 2
}
```

***failed***:

[]

### getQuestions

**request**: GET

**url:** /getQuestions

**请求参数**

| 参数名称    | 参数类型   | 说明      |
| ------- | ------ | ------- |
| tag     | String | 问题标签，可选 |
| subject | String | 所属科目，可选 |

**返回结果**

***success***:

```
{
    "examid": 1,
    "questions": [
        {
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
        }
    ],
    "userid": 2
}
```

***failed***:

[]

### getAllSubjects

**request**: GET

**url:** /getAllSubjects

**请求参数**

| 参数名称 | 参数类型 | 说明   |
| ---- | ---- | ---- |
| 无    | 无    | 无    |

**返回结果**

***success***:

```
["software","软件工程"]
```

***failed***:

null

### getTags

**request**: GET

**url:** /getTags

**请求参数**

| 参数名称    | 参数类型   | 说明    |
| ------- | ------ | ----- |
| subject | String | 科目，可选 |

**返回结果**

***success***:

```
["tag","错"]
```

***failed***:

null

