import Vue from 'vue'
import VueRouter from 'vue-router'

const paths = {
    // APP: '/',
    PAPER: '/',
    ANSWERSHEET: '/answersheet'
}

// 0. 如果使用模块化机制编程，導入Vue和VueRouter，要调用 Vue.use(VueRouter)
Vue.use(VueRouter)

// 1. 定义（路由）组件。
// 可以从其他文件 import 进来
import Paper from '../components/Paper'
import Answersheet from '../components/Answersheet'

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
    { path: paths.PAPER, component: Paper },
    { path: paths.ANSWERSHEET, component: Answersheet }
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
    routes, // （缩写）相当于 routes: routes
    mode: 'history',
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

// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
// const app = new Vue({
//     router
//   }).$mount('#app')
