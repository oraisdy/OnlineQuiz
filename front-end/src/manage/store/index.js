import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from './plugins/logger'

import questions from './modules/questions'
import create from './modules/create'
import exams from './modules/exams'
import score from './modules/score'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

const store = new Vuex.Store({
    modules: { questions, create, exams, score },
    // strict: debug,
    plugins: debug ? [createLogger()] : []
})

export default store
