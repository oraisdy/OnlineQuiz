import axios from 'axios'
import * as config from '../config'
// import mock from './mock'
// mock(axios)

export default {
    getOne({ authcode }) {
        return axios.get(`${config.QS_API_URL}/generatePaper`, {
            params: { authcode }
        })
    }
}
