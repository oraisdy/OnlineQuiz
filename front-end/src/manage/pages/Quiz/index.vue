<template>
    <el-form :model="quiz" :rules="rules" ref="quiz" label-width="120px" class="demo-quiz">
        <el-form-item label="考试名称" prop="name">
            <el-input v-model="quiz.name"></el-input>
        </el-form-item>
        <el-form-item label="考试课程" prop="subject">
            <el-input v-model="quiz.subject"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" required>
            <el-row>
                <el-col :span="11">
                    <el-form-item prop="start_date">
                        <el-date-picker type="date" placeholder="选择日期" v-model="quiz.start_date" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                    <el-form-item prop="start_time">
                        <el-time-picker type="fixed-time" placeholder="选择时间" v-model="quiz.start_time" style="width: 100%;"></el-time-picker>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form-item>
        <el-form-item label="考试时长" prop="lasted_time" required>
            <el-col :span="12">
                <el-input v-model.number="quiz.lasted_time" auto-complete="off"></el-input>
            </el-col>
            <el-col :span="12">
                <span>分钟</span>
            </el-col>
        </el-form-item>
        <el-form-item label="描述信息" prop="description">
            <el-input type="textarea" v-model="quiz.description"></el-input>
        </el-form-item>
        <el-form-item v-for="(cls, index) in quiz.classes" :label="index===0?'考生选择':''" :key="index" :prop="'classes.' + index + '.value'" :rules="[
                                                                                {required:true, message:'请添加参加考试的学生',trigger:'blur'}
                                                                            ]">
            <el-select v-model="cls.value" placeholder="请选择参加考试的班级">
                <el-option :label="clsName" :value="clsName" v-for="(clsName,index) in classes" :key="index"></el-option>
            </el-select>
            <el-button @click.prevent="removeClass(cls)" v-if="index > 0">删除</el-button>
            <el-button @click="importFromExcel">导入数据</el-button>
            <el-button @click="addClass" v-if="index === quiz.classes.length-1">添加班级</el-button>
        </el-form-item>
        <el-form-item label="试题数目" prop="problem_count" required>
            <el-input @input="onProblemCountChanged" v-model.number="quiz.problem_count" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item v-for="(pb, index) in quiz.score_value" :label="index===0?'分值设置':''" :key="index" :prop="'score_value.' + index + '.value'" :rules="[
                                                                                {required:true, message:'请设置每一道题目的出题范围和分值',trigger:'blur'}
                                                                            ]">
            <el-col :span="12">
                <el-select v-model="pb.scope" multiple placeholder="试题类型">
                    <el-option :label="pbType" :value="pbType" v-for="(pbType,index) in types" :key="index"></el-option>
                </el-select>
            </el-col>
            <el-col :span="12">
                <el-input v-model='pb.value' placeholder="分值"></el-input>
            </el-col>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('quiz')">立即创建</el-button>
            <el-button @click="resetForm('quiz')">重置</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
export default {
    data() {
        return {
            quiz: {
                name: '',
                subject: '',
                start_date: '',
                start_time: '',
                lasted_time: '',
                description: '',
                problem_count: 1,   //default value
                score_value: [{ scope: [], value: '' }],
                classes: [{ value: '' }]
            },
            classes: ['201401', '201402'],
            types: ['软件过程', 'PSP', '体系结构'],
            rules: {
                name: [
                    {
                        required: true,
                        message: '请输入考试名称',
                        trigger: 'blur'
                    },
                    {
                        min: 1,
                        max: 100,
                        message: '长度在 1 到 100 个字符',
                        trigger: 'blur'
                    }
                ],
                subject: [
                    {
                        required: true,
                        message: '请输入考试课程',
                        trigger: 'change'
                    },
                    {
                        min: 1,
                        max: 100,
                        message: '长度在 1 到 100 个字符',
                        trigger: 'blur'
                    }
                ],
                start_date: [
                    {
                        type: 'date',
                        required: true,
                        message: '请选择日期',
                        trigger: 'change'
                    }
                ],
                start_time: [
                    {
                        type: 'date',
                        required: true,
                        message: '请选择时间',
                        trigger: 'change'
                    }
                ],
                lasted_time: [
                    {
                        min: 1,
                        max: Number.MAX_SAFE_INTEGER,
                        type: 'integer',
                        required: true,
                        message: `数值在 1 到 ${Number.MAX_SAFE_INTEGER} 之间`,
                        trigger: 'blur'
                    }
                ],
                problem_count: [
                    {
                        min: 1,
                        max: 100,
                        type: 'integer',
                        required: true,
                        message: `数值在 1 到 100 之间`,
                        trigger: 'blur'
                    }
                ],
                description: [
                    {
                        required: false,
                        message: '请填写描述信息',
                        trigger: 'blur'
                    }
                ]
            }
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    console.log(this.quiz)
                    alert('submit!')
                } else {
                    console.log('error submit!!')
                    return false
                }
            })
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        },
        onProblemCountChanged(val) {
            console.log(val)
            var score_value = [];
            for (var i = 0; i < val; i++)
                score_value.push({ scope: [], value: '' });
            this.quiz.score_value = score_value;
        },
        removeClass(item) {
            var index = this.quiz.classes.indexOf(item)
            if (index !== -1) {
                this.quiz.classes.splice(index, 1)
            }
        },
        addClass() {
            this.quiz.classes.push({
                value: ''
            })
        },
        importFromExcel() {

        }
    }
}
</script>
<style lang="less">
.line {
    text-align: center;
}
</style>