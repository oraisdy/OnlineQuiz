<template>
    <div class="problem-section" :class="{mark:isMarked(problem.id)}">
        <div class="mark"></div>
        <div class="title" @dblclick="e => mark(problem.id)">
            [{{index+1}}] {{problem.title}}
        </div>
        <div class='options'>
            <label class='option' v-for="(option, index) in problem.allAnswers" :key="index">
                <problem-option-input v-model="option.checked"/>
                <span>{{index+1}} {{option.content}}</span>
            </label>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
export default {
    props: ['problem', 'index'],
    data() {
        return { markedPbs: this.$store.state.answersheet.marks }
    },
    methods: {
        isMarked(pbId) {
            return this.markedPbs.indexOf(pbId) !== -1
        },
        mark(pbId) {
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
    template: '<input type="checkbox" @change="onChange" :checked="value" />',
    methods: {
        onChange(event) {
            this.$emit('input', event.target.checked)
        }
    },
    props: { value: { type: Boolean, default: false } }
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
        }
    }

    &.mark {
        border-left: 12px @blue solid;
    }
}
</style>