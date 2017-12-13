import axios from 'axios'
import * as config from '../config'

export default {
    postOne(quiz) {
        console.log('quiz', quiz)
        return axios.post(`${config.QS_API_URL}/generateQuiz`, quiz)
    },
    getClasses() {
        return axios.get(`${config.QS_API_URL}/getAllClasses`)
    },
    getTags() {
        return axios.get(`${config.QC_API_URL}/getTags`)
    },
    getAllSubjects() {
        return axios.get(`${config.QC_API_URL}/getAllSubjects`)
    }
}
