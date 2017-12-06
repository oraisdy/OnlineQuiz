<template>
    <div>
        <h1>{{quiz.title}}</h1>
        <div v-if="problems.length">
            <ProblemItem class="problem-section" :id="'pb'+(index+1)" v-for="(problem, index) in problems" :key="problem.id" :problem="problem" :index="index"
            />
        </div>
        <button class='submit-btn' @click.prevent="submitAnswers">交卷</button>
    </div>
</template>


<script>
import _ from 'lodash'
import ProblemItem from './ProblemItem.vue'
import { mapGetters, mapActions } from 'vuex'
export default {
    props: ['quiz'],
    components: {
        ProblemItem
    },
    computed: mapGetters({
        title: 'title',
        problems: 'problems'
    }),
    watch: {
        problems: {
            handler: function(vals, oldVals) {
                console.log(vals)
            },
            deep: true
        }
    },
    created() {
        this.$store.dispatch('getPaper')
    },
    methods: {
        submitAnswers() {
            var answers = this.problems.map(pb => ({
                id: pb.id,
                answers: pb.options
                    .filter(ans => ans.checked)
                    .map(ans => ans.id)
            }))
            this.$store.dispatch('postAnswersheet', answers)
        }
    }
}

function mapProblemsToAnswers(pbs) {
    return pbs.map(pb => ({
        id: pb.id,
        answers: pb.options.filter(ans => ans.checked).map(ans => ans.id)
    }))
}
</script>