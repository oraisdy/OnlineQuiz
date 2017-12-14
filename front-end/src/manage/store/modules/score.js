import * as types from '../mutation-types'
import score from '../../api/score'

const state = {
    results: null
}
const actions = {
    getScores({ commit }, quizid) {
        score.getAll(quizid).then(json => {
            if (json && json.data) {
                commit(types.SCORES_RECEIVED, { results: json.data })
            }
        })
    }
}

const mutations = {
    [types.SCORES_RECEIVED](state, { results }) {
        state.results = results
    }
}

export default {
    actions,
    state,
    mutations
}
