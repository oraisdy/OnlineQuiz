import quiz from '../../api/quiz'
import * as types from '../mutation-types'

const state = {
    quiz: {
        end_at: new Date()
    }
}

const getters = {
    quiz: state => state.quiz
}

const actions = {
    getQuiz({ commit }) {
        quiz.getOne(quiz => {
            commit(types.QUIZ_RECEIVED, { quiz })
        })
    }
}

const mutations = {
    [types.QUIZ_RECEIVED](state, { quiz }) {
        state.quiz = quiz
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
