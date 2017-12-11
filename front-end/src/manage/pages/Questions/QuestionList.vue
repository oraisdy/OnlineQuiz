<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="subject" label="科目" width="180"> </el-table-column>
        <el-table-column prop="tag" label="类型" width="180"> </el-table-column>
        <el-table-column prop="title" label="题干"> </el-table-column>
        <el-table-column prop="answers" label="答案"> </el-table-column>
        
    </el-table>
</template>

  <script>
import { mapGetters, mapActions } from 'vuex'

export default {
    computed: {
        tableData() {
            return this.$store.state.questions.questions.map(qst => {
                qst.answers = qst.allAnswers.map(ans => ans.content)
                return qst
            })
        }
    },
    created() {
        this.$store.dispatch('getQuestions')
    }
}
</script>