<template>
<div class="top-nav">
    <div class="banner"><h3>Online Quiz</h3></div>
    <CountDown :end_time="end_time" class="count-down"></CountDown>
</div>
</template>

<script>
import CountDown from "./CountDown.vue";

export default {
    components: { CountDown },
    data: function() {
        return { end_time: new Date("2017-11-30T19:24:00") };
    }
};
</script>
<style lang="less">
@import "../../variables.less";

.top-nav {
    width: 100%;
    height: @top-nav-height;
    position: fixed;
    top: 0;
    background: @white;
    z-index: 100;

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
    .count-down {
        float: right;
        padding-right: 2rem;
        width: 150px;
    }
}
</style>
