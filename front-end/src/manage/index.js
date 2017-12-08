import Vue from 'vue'
import store from './store'
import router from './router'
import App from './pages/App'
import {
    Menu,
    Submenu,
    MenuItem,
    Button,
    Form,
    Input,
    Col,
    Row,
    TimePicker,
    Checkbox,
    CheckboxGroup,
    FormItem,
    Select,
    Option,
    DatePicker,
    Radio,
    RadioGroup,
    Switch
} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Button)
Vue.use(Col)
Vue.use(Row)
Vue.use(Form)
Vue.use(TimePicker)
Vue.use(Checkbox)
Vue.use(Input)
Vue.use(Switch)
Vue.use(FormItem)
Vue.use(Select)
Vue.use(Option)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(CheckboxGroup)
Vue.use(DatePicker)

// Vue.config.productionTip = false

// new Vue({
//     router,
//     store, // inject store to all children
//     el: '#app'
//     // render: h => h(App)
// })
new Vue({
    el: '#app',
    template: '<App/>',
    components: { App }
})

// const app = new Vue({
//     router,
//     store
//     // template: '<App/>',
//     // components: { App }
// }).$mount('#app')
