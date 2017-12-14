import axios from 'axios'
import * as config from '../config'
export default {
    getAll(quizid) {
        return axios.get(`${config.JW_API_URL}/getScore`, {
            params: { quizid }
        })
    }
}
