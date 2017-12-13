<template>
<div class="top-nav">
    <div class="banner"><h3>Online Quiz</h3></div>
    <!-- <div class=""> -->
        <div v-if="isPaper" class="banner-btn banner-right"><router-link :to="paths.ANSWERSHEET">交卷</router-link></div>
        <div v-if="isAnswerSheet" class="banner-btn banner-right"><router-link :to="paths.PAPER">返回考试</router-link></div>
        <CountDown :end_time="quiz.end_at" class="count-down banner-right"></CountDown>
        
    <!-- </div> -->
</div>
</template>

<script>
import CountDown from './CountDown.vue'
import { paths } from '../../router'
export default {
    props: ['quiz'],
    data() {
        return {
            paths,
            isPaperFlag: true
        }
    },
    computed: {
        isPaper() {
            return this.$route.matched[1].path === '/paper'
        },
        isAnswerSheet() {
            return this.$route.matched[1].path === '/answersheet'
        }
    },
    components: { CountDown }
}
</script>
<style lang="less">
@import '../../variables.less';

.top-nav {
    width: 100%;
    height: @top-nav-height;
    position: fixed;
    top: 0;
    background: @white;
    z-index: 100;
    overflow: hidden;

    & > div {
        display: inline-block;
        vertical-align: middle;
        line-height: @top-nav-height;
        text-align: center;
        & > h1,
        h2,
        h3,
        h4,
        p {
            margin: 0;
        }
    }
    .banner {
        float: left;
        padding-left: 2rem;
        width: 200px;
    }
    .banner-right {
        float: right;
    }
    .banner-btn {
        width: 100px;
        display: inline-block;
        vertical-align: top;
        margin: 0 10px;
        padding: 0;
        background: @vue-blue;
        & > a {
            color: @white;

            text-decoration: none;
        }
    }
}
</style>
