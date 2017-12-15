<template>
    <div>       
        <h1>{{exam && exam.name}}</h1>
        <p>当前考生：{{user && user.name}}({{user&&user.email}})<br>
        考试科目：{{exam && exam.subject}}  试题数目：{{exam && exam.questionCount}}</p>
        <div v-if="isResultPage" class='score-wrapper'>
            <div class="score-value">{{score}}</div>
        </div>
        <div v-if="problems.length">
            <ProblemItem class="problem-section" :id="'pb'+(index+1)" v-for="(problem, index) in problems" :key="problem.id" :problem="problem"
                :index="index" :isResultPage="isResultPage"/>
        </div>
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
        problems: 'problems',
        isResultPage: 'isResultPage',
        score: 'score'
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
        if (this.isResultPage)
            this.$store.dispatch('getScore', {
                examid: this.$store.state.paper.examid,
                userid: this.$store.state.paper.userid
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
@import '../../variables.less';

p {
    text-align: center;
    line-height: 2em;
}
.score-wrapper {
    background-image: url('/score-bg-for-answer.png');
    background-size: 100%;
    width: 108px;
    height: 125px;
    right: -3px;
    top: -15px;
    position: absolute;
    text-align: center;
    .score-value {
        line-height: 150px;
        font-size: 40px;
        color: @red;
    }
}
</style>
