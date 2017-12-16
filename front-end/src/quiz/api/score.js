import axios from 'axios'
import * as config from '../config'
export default {
    getScore(quizid, userid) {
        return axios.get(`${config.JW_API_URL}/getScore`, {
            params: { quizid, userid }
        })
    }
}
