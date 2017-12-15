<template>
    <div class="problem-section" :class="{mark:isMarked(problem.id)}">
        <div class="mark"></div>
        <div class="title" @dblclick="e => mark(problem.id)">
            [{{index+1}}] {{problem.title}}
        </div>
        <div class='options'>
            <label class='option' 
            :class="{'student-answer':isStudentAnswer(problem,option),'correct-answer':isCorrectAnswer(problem,option)}" 
                v-for="(option, index) in problem.allAnswers" 
                :key="index">
                <problem-option-input v-model="option.checked" :disabled="isResultPage>0"/>
                <span>{{index+1}} {{option.content}}</span>
            </label>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import _ from 'lodash'
export default {
    props: ['problem', 'index', 'isResultPage'],
    data() {
        // console.log(this.problemProp)
        // var problem = _.cloneDeep(this.problemProp)
        if (this.isResultPage) {
            this.problem.allAnswers.forEach(ans => {
                // console.log(ans)
                if (this.problem.studentAns.indexOf(ans.id) >= 0)
                    ans.checked = true
            })
        }
        return {
            markedPbs: this.$store.state.answersheet.marks
            // problem
        }
    },
    methods: {
        // computed: {
        //     problem() {
        //         // var problem = _.clone(this.problemProp)
        //         // console.log('pro', problem)
        //         if (this.isResultPage) {
        //             this.problem.allAnswers.forEach(ans => {
        //                 console.log(ans)
        //                 if (this.problem.studentAns.indexOf(ans.id) >= 0)
        //                     ans.checked = true
        //             })
        //         }
        //         return this.problem
        //     }
        // },
        isStudentAnswer(problem, option) {
            return (
                this.isResultPage && problem.studentAns.indexOf(option.id) >= 0
            )
        },
        isCorrectAnswer(problem, option) {
            return this.isResultPage && option.type === 1
        },
        isMarked(pbId) {
            return this.markedPbs.indexOf(pbId) !== -1
        },

        mark(pbId) {
            if (this.isResultPage) return
            var index = this.markedPbs.indexOf(pbId)
            if (index !== -1) {
                this.markedPbs.splice(this.markedPbs.indexOf(pbId), 1)
            } else {
                this.markedPbs.push(pbId)
            }
            this.$store.dispatch('saveMarkedItems', this.markedPbs)
        }
    }
}

Vue.component('problem-option-input', {
    template:
        '<input type="checkbox" @change="onChange" :checked="value" :disabled="disabled"/>',
    methods: {
        onChange(event) {
            this.$emit('input', event.target.checked)
        }
    },
    props: { value: { type: Boolean, default: false }, disabled: Boolean }
})
</script>

<style lang="less">
@import '../../variables.less';
.problem-section {
    border-radius: 5px;
    margin-top: @top-nav-height;
    border: 1px solid @dark-grey;
    margin: 2rem 0;

    .title {
        background: @grey;
        padding: 1rem;
    }
    .options {
        .option {
            display: block;
            border-bottom: 1px solid @grey;
            padding: 1rem;

            &.student-answer {
                background-color: @cl-danger;
            }
            &.correct-answer {
                background-color: @cl-success;
            }
        }
    }

    &.mark {
        border-left: 12px @blue solid;
    }
}
</style>