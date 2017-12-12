import paper from '../../api/paper'
import * as types from '../mutation-types'

export const STORAGE_KEY = 'online-quiz'

// initial state
const state = {
    problems: [],
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
        paper.getOne('id').then(json => {
            if (json.status === 200 && json.data) {
                const paper = json.data
                return commit(types.PAPER_RECEIVED, { paper })
            } else return commit(types.PAPER_FETCH_FAILURE)
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
