import Vue from 'vue'
import store from './store'
import App from './components/App'
import { Menu, Submenu, MenuItem, Button } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Button)

Vue.config.productionTip = false

new Vue({
    store, // inject store to all children
    el: '#app',
    render: h => h(App)
})
// new Vue({
//   el: '#app',
//   template: '<App/>',
//   components: { App }
// })
