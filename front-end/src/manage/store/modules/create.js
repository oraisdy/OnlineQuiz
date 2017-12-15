import create from '../../api/create'
import * as types from '../mutation-types'
const state = {
    quiz: null,
    classes: [],
    tags: [],
    subjects: [],
    createMsg: null
}

const actions = {
    postQuiz({ commit }, quiz) {
        return create.postOne(quiz).then(json => {
            if (json.status === 200 && json.data) {
                commit(types.QUIZ_POSTED, {
                    quiz: json.data.responseBody
                })
                Message('创建成功')
            } else commit(types.QUIZ_POST_FAILURE)
        })
    },
    getClasses({ commit }) {
        return create.getClasses().then(json => {
            if (json.status === 200 && json.data) {
                commit(types.CLASSES_RECEIVED, {
                    classes: json.data
                })
            }
        })
    },
    getTags({ commit }) {
        return create.getTags().then(json => {
            if (json.status === 200 && json.data) {
                commit(types.TAGS_RECEIVED, {
                    tags: json.data
                })
            }
        })
    },
    getSubjects({ commit }) {
        return create.getAllSubjects().then(json => {
            if (json.status === 200 && json.data) {
                commit(types.SUBJECTS_RECEIVED, {
                    subjects: json.data
                })
            }
        })
    }
}

const mutations = {
    [types.QUIZ_POSTED](state, { quiz }) {
        state.quiz = quiz
        state.createMsg = '创建成功'
        Message('创建成功')
    },
    [types.CLASSES_RECEIVED](state, { classes }) {
        state.classes = classes
    },
    [types.TAGS_RECEIVED](state, { tags }) {
        state.tags = tags
    },
    [types.SUBJECTS_RECEIVED](state, { subjects }) {
        state.subjects = subjects
    },
    [types.QUIZ_POST_FAILURE](state) {
        state.createMsg = '创建失败'
    }
}

export default {
    actions,
    state,
    mutations
}
