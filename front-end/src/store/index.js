import Vue from 'vue'
import Vuex from 'vuex'
// import plugins from './plugins'
import paper from './modules/paper'
import quiz from './modules/quiz'

Vue.use(Vuex)

export default new Vuex.Store({
    // actions,
    // getters
    modules: { paper, quiz }
})
