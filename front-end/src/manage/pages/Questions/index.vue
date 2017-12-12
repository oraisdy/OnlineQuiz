
<template>
    <div>
        <QuestionList/>
        <el-upload class="upload" action="http://10.73.181.47:2222/saveTheInputExcel" :on-preview="handlePreview" :on-remove="handleRemove" multiple :limit="3" :on-exceed="handleExceed">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xslx文件，且不超过500kb</div>
        </el-upload>
    </div>
</template>

<script>
import QuestionList from './QuestionList'
export default {
    components: {
        QuestionList
    },
    data() {
        return {
            fileList: []
        }
    },
    methods: {
        handleRemove(file, fileList) {
            console.log(file, fileList)
        },
        handlePreview(file) {
            console.log(file)
        },
        handleExceed(files, fileList) {
            this.$message.warning(
                `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length +
                    fileList.length} 个文件`
            )
        }
    }
}
</script>

<style lang="less">
.upload {
    margin-top: 40px;
}
</style>
