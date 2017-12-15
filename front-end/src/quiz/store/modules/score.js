import score from '../../api/score'
import * as types from '../mutation-types'

const state = {
    score: null
}

const getters = {
    score: state => state.score
}

const actions = {
    getScore({ commit }, { examid, userid }) {
        score.getScore(examid, userid).then(json => {
            if (json.status === 200 && json.data) {
                commit(types.SCORE_RECEIVED, { score: json.data })
            }
        })
    }
}

const mutations = {
    [types.SCORE_RECEIVED](state, { score }) {
        state.score = score[0].score
    }
}

export default { state, getters, actions, mutations }
