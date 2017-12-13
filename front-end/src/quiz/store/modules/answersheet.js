import answersheet from '../../api/answersheet'
import * as types from '../mutation-types'

export const STORAGE_KEY = 'online-quiz'

const state = {
    problems:
        JSON.parse(window.localStorage.getItem(`${STORAGE_KEY}.draft`)) || [],
    marks: JSON.parse(window.localStorage.getItem(`${STORAGE_KEY}.marks`)) || []
}

const getters = {
    // problems: state => state.problems
}

const actions = {
    postAnswersheet({ commit }, ansSht) {
        answersheet.postOne(ansSht).then(json => {
            if (json.status === 200) {
                return commit(types.ANSWERSHEET_POSTED, { msg: '提交成功' })
            } else commit(types.ANSWERSHEET_POST_FAILURE, { msg: json })
        })
    },
    saveAnswersheet({ commit }, ansSht) {
        localStorage.setItem(
            `${STORAGE_KEY}.draft`,
            JSON.stringify(ansSht.problems)
        )
        commit(types.ANSWERSHEET_SAVED, { ansSht })
    },
    saveMarkedItems({ commit }, markedPbs) {
        localStorage.setItem(`${STORAGE_KEY}.marks`, JSON.stringify(markedPbs))
        commit(types.MARKED_ITEMS_SAVED, { markedPbs })
    }
}

const mutations = {
    [types.ANSWERSHEET_POSTED](state, { msg }) {
        console.log(msg)
    },
    [types.ANSWERSHEET_SAVED](state, { ansSht }) {
        state.problems = ansSht.problems
        console.log('saves', ansSht)
    },
    [types.MARKED_ITEMS_SAVED](state, { markedPbs }) {
        state.marks = markedPbs
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
