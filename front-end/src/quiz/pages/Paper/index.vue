<template>
    <div>       
        <h1>{{exam && exam.name}}</h1>
        <p>当前考生：{{user && user.name}}({{user&&user.email}})<br>
        考试科目：{{exam && exam.subject}}  试题数目：{{exam && exam.questionCount}}</p>
        <div v-if="problems.length">
            <ProblemItem class="problem-section" :id="'pb'+(index+1)" v-for="(problem, index) in problems" :key="problem.id" :problem="problem"
                :index="index" />
        </div>
        <!-- <button class='submit-btn' @click.prevent="submitAnswers">
            <router-link :to="paths.ANSWERSHEET">交卷</router-link>
        </button> -->
    </div>
</template>


<script>
import _ from 'lodash'
import { paths } from '../../router'
import ProblemItem from './ProblemItem'
import { mapGetters, mapActions } from 'vuex'

export default {
    // props: ['quiz'],
    components: {
        ProblemItem
    },
    data: function() {
        return { paths }
    },
    computed: mapGetters({
        exam: 'exam',
        user: 'user',
        problems: 'problems'
    }),
    watch: {
        problems: {
            handler: function(vals, oldVals) {
                var answers = mapProblemsToAnswers(vals)
                this.$store.dispatch('saveAnswersheet', { problems: answers })
            },
            deep: true
        }
    },
    created() {
        this.$store.dispatch('getPaper', {
            authcode: 'hmEHHxrs9jZyIoS7IHs3gg=='
        })
    }
}

function mapProblemsToAnswers(pbs) {
    return pbs.map(pb => ({
        id: pb.id,
        answers: pb.allAnswers
            .map((ans, index) => ({
                id: ans.id,
                index: index + 1,
                checked: ans.checked
            }))
            .filter(ans => ans.checked)
    }))
}
</script>

<style lang="less">
p {
    text-align: center;
    line-height: 2em;
}
</style>
