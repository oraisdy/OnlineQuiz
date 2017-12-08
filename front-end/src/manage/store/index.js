import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from './plugins/logger'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {},
    // strict: debug,
    plugins: debug ? [createLogger()] : []
})
console.log(store)

export default store
