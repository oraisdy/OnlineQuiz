<template>
    <div>
        <h1>{{quiz.title}}</h1>
        <div v-if="problems.length">
            <ProblemItem class="problem-section" v-for="(problem, index) in problems" :key="problem.id" :problem="problem" :index="index"
            />
        </div>

    </div>
</template>


<script>
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
    created() {
        this.$store.dispatch('getPaper')
    }
}
</script>