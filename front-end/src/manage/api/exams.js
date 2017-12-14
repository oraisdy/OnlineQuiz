import axios from 'axios'
import * as config from '../config'

export default {
    getAll() {
        return axios.get(`${config.QS_API_URL}/getAllExams`)
    }
}
