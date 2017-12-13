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