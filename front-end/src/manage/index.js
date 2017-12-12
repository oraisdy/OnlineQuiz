import Vue from "vue";
import store from "./store";
import router from "./router";
import {
    Menu,
    Submenu,
    MenuItem,
    Button,
    Form,
    Input,
    Col,
    Upload,
    Table,
    TableColumn,
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
} from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(Button);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Upload);
Vue.use(Col);
Vue.use(Row);
Vue.use(Form);
Vue.use(TimePicker);
Vue.use(Checkbox);
Vue.use(Input);
Vue.use(Switch);
Vue.use(FormItem);
Vue.use(Select);
Vue.use(Option);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(CheckboxGroup);
Vue.use(DatePicker);

const app = new Vue({
    router,
    store
}).$mount("#app");
