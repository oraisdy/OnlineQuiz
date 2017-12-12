import questions from '../../api/questions'
import * as types from '../mutation-types'
const state = {
    questions: []
}

const actions = {
    getQuestions({ commit }) {
        return questions.getAll('tag', 'software').then(json => {
            if (json.status === 200 && json.data) {
                return commit(types.QUESTIONS_RECEIVED, {
                    questions: json.data
                })
            } else commit(types.QUESTIONS_FETCH_FAILURE)
        })
    }
}

const mutations = {
    [types.QUESTIONS_RECEIVED](state, { questions }) {
        state.questions = questions
    }
}

export default {
    actions,
    state,
    mutations
}
