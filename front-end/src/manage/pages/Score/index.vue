<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="user_id" label="考试名称"> </el-table-column>
        <el-table-column prop="score" label="得分"> </el-table-column>
    </el-table>
</template>

  <script>
export default {
    computed: {
        tableData() {
            return this.$store.state.score.results || []
        }
    },
    created() {
        this.$store.dispatch('getScores', this.$route.query.quizid)
    }
}
</script>