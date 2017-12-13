import Vue from 'vue'
import VueRouter from 'vue-router'

const paths = {
    ENTRANCE: '/entrance',
    PAPER: '/paper',
    ANSWERSHEET: '/answersheet'
}

Vue.use(VueRouter)

import App from '../pages/App'
import Entrance from '../pages/Entrance'
import Paper from '../pages/Paper'
import Answersheet from '../pages/Answersheet'

const routes = [
    {
        path: '/',
        component: App,
        children: [
            { path: '', redirect: paths.ENTRANCE },
            { path: paths.ENTRANCE, component: Entrance },
            { path: paths.PAPER, component: Paper },
            { path: paths.ANSWERSHEET, component: Answersheet }
        ]
    }
]

const router = new VueRouter({
    routes,
    // mode: 'history',
    scrollBehavior(to, from, savedPosition) {
        if (to.hash) {
            return {
                selector: to.hash
            }
        }
    }
})

export default router
export { paths }
