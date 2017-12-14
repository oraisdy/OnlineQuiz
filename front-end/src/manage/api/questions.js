import axios from 'axios'
import * as config from '../config'
export default {
    getAll(tag, subject) {
        return axios.get(`${config.QC_API_URL}/getQuestions`)
    }
}
