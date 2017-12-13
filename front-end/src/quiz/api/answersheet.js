import axios from 'axios'
import * as config from '../config'

export default {
    postOne(ansSht) {
        console.log('ansSht', ansSht)
        return axios.post(`${config.QS_API_URL}/saveAnswerSheet`, ansSht)
    }
}
