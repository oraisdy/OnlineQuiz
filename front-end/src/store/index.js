import Vue from 'vue'
import Vuex from 'vuex'
// import plugins from './plugins'
import paper from './modules/paper'
import quiz from './modules/quiz'
import answersheet from './modules/answersheet'
import createLogger from './plugins/logger'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

const store = new Vuex.Store({
    // actions,
    // getters
    modules: { paper, quiz, answersheet },
    // strict: debug,
    plugins: debug ? [createLogger()] : []
})
console.log(store)

export default store
