import paper from '../../api/paper'
import * as types from '../mutation-types'

export const STORAGE_KEY = 'online-quiz'

// initial state
const state = {
    problems: JSON.parse(window.localStorage.getItem(STORAGE_KEY) || '[]'),
    title: null
}

// getters
const getters = {
    problems: state => state.problems,
    title: state => state.title
}

// actions
const actions = {
    getPaper({ commit }) {
        paper.getOne(paper => {
            commit(types.PAPER_RECEIVED, { paper })
        })
    }
}

// mutations
const mutations = {
    [types.PAPER_RECEIVED](state, { paper }) {
        state.problems = paper.problems
        state.title = paper.title
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
