import axios from 'axios'
import mock from './mock'
mock(axios)

export default {
    // getOne(cb) {
    //     setTimeout(() => cb(_paper), 100)
    // }
    getOne(id) {
        return axios.get('/paper')
    }
}
