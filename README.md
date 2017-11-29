# OnlineQuiz

### For Development
1. 运行ServiceRegistryService项目开启服务注册，访问localhost:1111可以查看运行服务列表
2. 运行QuestionCollection，QuizSystem，JudgeWorker, EmailSystem项目，分别运行在2222，3333，4444, 5555端口
3. 访问localhost:3333/consumer 可以看到QS调用QC服务后执行的结果