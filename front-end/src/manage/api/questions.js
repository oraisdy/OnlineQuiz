import axios from 'axios'
import * as config from '../config'
export default {
    getAll(tag, subject) {
        return axios.get(
            config.QUESTIONS_API_URL + '/getQuestionsByTagAndSubject',
            {
                params: {
                    tag,
                    subject
                }
            }
        )
    }
}
