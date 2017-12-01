import Vue from 'vue'
import App from './App'
import { Menu, Submenu, MenuItem, Button } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Button)

Vue.config.productionTip = false

new Vue({
    el: '#app',
    render: h => h(App)
})
// new Vue({
//   el: '#app',
//   template: '<App/>',
//   components: { App }
// })
