const _quiz = {
    title: 'Quiz: 软件过程与管理',
    start_at: new Date('2017-11-30T19:24:00'),
    end_at: new Date('2017-12-11T19:24:00'),
    question_num: 3
}

export default {
    getOne(cb) {
        setTimeout(() => {
            cb(_quiz)
        }, 100)
    }
}
