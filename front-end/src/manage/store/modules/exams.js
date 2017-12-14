import * as types from '../mutation-types'
import exams from '../../api/exams'

const state = {
    exams: null
}
const actions = {
    getAllExams({ commit }) {
        exams.getAll().then(json => {
            if (json && json.data) {
                commit(types.EXAMS_RECEIVED, { exams: json.data })
            }
        })
    }
}

const mutations = {
    [types.EXAMS_RECEIVED](state, { exams }) {
        state.exams = exams
    }
}

export default {
    actions,
    state,
    mutations
}
