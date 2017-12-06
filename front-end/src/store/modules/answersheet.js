import answersheet from '../../api/answersheet'
import * as types from '../mutation-types'

const state = {
    answers: []
}

const getters = {
    answers: state => state.answers
}

const actions = {
    postAnswersheet({ commit }, ansSht) {
        answersheet.postOne(
            ansSht,
            ({ msg }) => commit(types.ANSWERSHEET_POST_SUCCESS, { msg }),
            () => commit(types.ANSWERSHEET_POST_FAILURE)
        )
    }
}

const mutations = {
    [types.ANSWERSHEET_POST_SUCCESS](state, { msg }) {
        console.log(msg)
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
