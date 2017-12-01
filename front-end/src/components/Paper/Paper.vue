<template>
    <div>
        <h1>{{title}}</h1>
        <div v-if="problems.length">
            <ProblemItem class="problem-section" v-for="(problem, index) in problems" :key="problem.id" :problem="problem" :index="index"
            />
        </div>

    </div>
</template>


<script>
    import ProblemItem from './ProblemItem.vue'
    export default {
        components: {
            ProblemItem
        },
        data() {
            return {
                title: 'Quiz：软件过程与管理',
                problems: [{
                        id: '1',
                        title: 'problem 1',
                        options: {
                            'option 1': {
                                checked: false
                            },
                            'option2': {
                                checked: false
                            },
                            'option 3': {
                                checked: false
                            },
                            'option 4': {
                                checked: false
                            }
                        }
                    },
                    {
                        id: '2',
                        title: 'problem 2',
                        options: {
                            'option 1': {
                                checked: false
                            },
                            'option2': {
                                checked: false
                            },
                            'option 3': {
                                checked: false
                            },
                            'option 4': {
                                checked: false
                            }
                        }
                    }
                ]
            }
        }
    }
</script>