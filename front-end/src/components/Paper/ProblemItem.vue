<template>
    <div class="problem-section">
        <div class="title">
            [{{index+1}}] {{problem.title}}
        </div>
        <div class='options'>
            <label class='option' v-for="(option, key, index) in problem.options" :key="index">
                <input type='radio' v-model="option.checked" />
                <span>{{index+1}} {{key}}</span>
            </label>
        </div>
    </div>
</template>

<script>
export default {
    props: ['problem', 'index'],

    methods: {
        onSelect(e) {
            e.preventDefault()

            var checked = e.currentTarget.children[0].checked
            e.currentTarget.children[0].checked = !checked
        }
    }
}
</script>

<style lang="less">
@import '../../variables.less';
.problem-section {
    border-radius: 5px;
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