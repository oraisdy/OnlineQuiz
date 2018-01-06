import paper from "../../api/paper";
import * as types from "../mutation-types";

export const STORAGE_KEY = "online-quiz";

// initial state
const state = {
    problems: [],
    examid: null,
    userid: null,
    exam: null,
    user: null,
    isResultPage: null,
    authcode: localStorage.getItem(`${STORAGE_KEY}.authcode`) || null
};

// getters
const getters = {
    problems: state => state.problems,
    exam: state => state.exam,
    user: state => state.user,
    isResultPage: state => state.isResultPage
};

// actions
const actions = {
    getPaper({ commit }, authcode) {
        paper.getOne(authcode).then(json => {
            if (json.status === 200 && json.data) {
                const paper = json.data;
                return commit(types.PAPER_RECEIVED, { paper });
            } else return commit(types.PAPER_FETCH_FAILURE);
        });
    }
};

// mutations
const mutations = {
    [types.PAPER_RECEIVED](state, { paper }) {
        state.problems = paper.question;
        state.exam = paper.exam;
        state.user = paper.user;
        state.examid = paper.exam.id;
        state.userid = paper.user.id;
        state.isResultPage = paper.flag;
    }
};

export default {
    state,
    getters,
    actions,
    mutations
};
