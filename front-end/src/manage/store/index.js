import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from './plugins/logger'

import questions from './modules/questions'
import create from './modules/create'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

const store = new Vuex.Store({
    modules: { questions, create },
    // strict: debug,
    plugins: debug ? [createLogger()] : []
})

export default store
