<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="考试名称"> </el-table-column>
        <el-table-column prop="subject" label="科目"> </el-table-column>
        <el-table-column prop="questionCount" label="试题数"> </el-table-column>
        <el-table-column prop="startat" label="开考时间"> </el-table-column>
        <el-table-column prop="endat" label="结束时间"> </el-table-column>
        <el-table-column label="操作">
            <template slot-scope='scope'>
            <el-button size='mini'><router-link :to="paths.SCORE+'?quizid='+scope.row.id">查看成绩</router-link></el-button>
            </template>
        </el-table-column>
        
    </el-table>
</template>

  <script>
import { paths } from '../../router'
export default {
    data() {
        return { paths }
    },
    computed: {
        tableData() {
            return this.$store.state.exams.exams || []
        }
    },
    created() {
        this.$store.dispatch('getAllExams')
    }
}
</script>
<style lang="less">
@import '../../variables.less';
a {
    color: @blue;
    text-decoration: none;
}
</style>
