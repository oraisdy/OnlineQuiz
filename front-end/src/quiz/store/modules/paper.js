import paper from '../../api/paper'
import * as types from '../mutation-types'

export const STORAGE_KEY = 'online-quiz'

// initial state
const state = {
    problems: [],
    examid: null,
    userid: null,
    title: null,
    authcode: localStorage.getItem(`${STORAGE_KEY}.authcode`) || null
}

// getters
const getters = {
    problems: state => state.problems,
    title: state => state.title
}

// actions
const actions = {
    getPaper({ commit }, authcode) {
        paper.getOne(authcode).then(json => {
            if (json.status === 200 && json.data) {
                const paper = json.data
                console.log(paper.question[0].id)
                return commit(types.PAPER_RECEIVED, { paper })
            } else return commit(types.PAPER_FETCH_FAILURE)
        })
    }
}

// mutations
const mutations = {
    [types.PAPER_RECEIVED](state, { paper }) {
        state.problems = paper.question
        state.examid = paper.examid
        state.userid = paper.userid
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
