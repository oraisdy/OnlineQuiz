<template>
    <div class="problem-section">
        <div class="title">
            [{{index+1}}] {{problem.title}}
        </div>
        <div class='options'>
            <label class='option' v-for="(option, index) in problem.options" :key="index">
                <problem-option-input v-model="option.checked"/>
                <span>{{index+1}} {{option.content}}</span>
            </label>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
export default {
    props: ['problem', 'index']
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
}
</style>