<template>
<div class='content'>
  <p>请输入考试码</p>
  <div><input @keydown="enterQuiz"/></div>
</div>
</template>

<script>
export default {
    methods: {
        enterQuiz(e) {
            console.log(e)
        }
    }
}
</script>
<style lang="less">
.content {
    width: 100%;
    text-align: center;
}
</style>
