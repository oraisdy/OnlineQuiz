import Vue from 'vue'
import Vuex from 'vuex'
// import plugins from './plugins'
import paper from './modules/paper'
import answersheet from './modules/answersheet'
import score from './modules/score'

import createLogger from './plugins/logger'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

const store = new Vuex.Store({
    // actions,
    // getters
    modules: { paper, answersheet, score },
    // strict: debug,
    plugins: debug ? [createLogger()] : []
})
console.log(store)

export default store
