import answersheet from '../../api/answersheet'
import * as types from '../mutation-types'

export const STORAGE_KEY = 'online-quiz'

const state = {
    problems:
        JSON.parse(window.localStorage.getItem(`${STORAGE_KEY}.draft`)) || []
}

const getters = {
    // problems: state => state.problems
}

const actions = {
    postAnswersheet({ commit }, ansSht) {
        answersheet.postOne(
            ansSht,
            ({ msg }) => commit(types.ANSWERSHEET_POSTED, { msg }),
            () => commit(types.ANSWERSHEET_POST_FAILURE)
        )
    },
    saveAnswersheet({ commit }, ansSht) {
        localStorage.setItem(
            `${STORAGE_KEY}.draft`,
            JSON.stringify(ansSht.problems)
        )
        commit(types.ANSWERSHEET_SAVED, { ansSht })
    }
}

const mutations = {
    [types.ANSWERSHEET_POSTED](state, { msg }) {
        console.log(msg)
    },
    [types.ANSWERSHEET_SAVED](state, { ansSht }) {
        state.problems = ansSht.problems
        console.log('saves', ansSht)
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
