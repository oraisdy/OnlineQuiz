<template>
<div class='content'>
  <p>请输入考试码</p>
  <div><input @keydown="enterQuiz"/></div>
</div>
</template>

<script>
import { paths } from '../../router'
export default {
    methods: {
        enterQuiz(e) {
            console.log(e)
            if (e.keyCode === 13) {
                // this.$store.dispatch('getPaper', {
                //     // authcode: e.target.value,
                //     authcode: 'hmEHHxrs9jZyIoS7IHs3gg=='
                // })
                this.$router.push(`${paths.PAPER}?authcode=${e.target.value}`)
            }
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
