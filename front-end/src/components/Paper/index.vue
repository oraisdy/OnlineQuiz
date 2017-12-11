<template>
    <div>       
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
    props: ['quiz'],
    components: {
        ProblemItem
    },
    data: function() {
        return { paths }
    },
    computed: mapGetters({
        title: 'title',
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
        this.$store.dispatch('getPaper')
    }
    // methods: {
    //     submitAnswers() {
    //         var answers = mapProblemsToAnswers(this.problems)
    //         this.$store.dispatch('saveAnswersheet', { problems: answers })
    //         // var answers = this.problems.map(pb => ({
    //         //     id: pb.id,
    //         //     answers: pb.options
    //         //         .filter(ans => ans.checked)
    //         //         .map(ans => ans.id)
    //         // }))
    //         // this.$store.dispatch('postAnswersheet', answers)
    //     }
    // }
}

function mapProblemsToAnswers(pbs) {
    return pbs.map(pb => ({
        id: pb.id,
        answers: pb.options.filter(ans => ans.checked).map(ans => ans.id)
    }))
}
</script>