<template>
<div class='content'>
<table class='answers-table-container'>
  <tr hidden><th colspan="2"></th></tr>
  <tr class="answers-table" cellspacing="0" cellpadding="0">
    <td class="answers-table-col">
      <table cellspacing="0" cellpadding="0">
 
      <tr class="answers-table-header"><th><div>题号</div></th><th><div>答案</div></th></tr>
   
      <tr v-for='problem in problems1' :key="problem.index" class='cell' :class="{warning:problem.answers && problem.answers.length <=0 , hint:isMarked(problem.id)}">
        <td class='answers-table-td'>
          <div >
             <router-link :to="paths.PAPER+'#pb'+problem.index">{{problem.index}}</router-link>
          </div>
        </td>
        <td class='answers-table-td'>{{problem.answers.map(ans => ans.index)}}</td>
      </tr>
  
  </table>
    </td>
    <td class="answers-table-col">
      <table cellspacing="0" cellpadding="0">
 
      <tr class="answers-table-header"><th><div>题号</div></th><th><div>答案</div></th></tr>
   
      <tr v-for='problem in problems2' :key="problem.index" class="cell" :class="{warning:problem.answers && problem.answers.length <=0, hint:isMarked(problem.id)}">
        <td class='answers-table-td'>
          <div>
             <router-link :to="paths.PAPER+'#pb'+problem.index">{{problem.index}}</router-link>
          </div>
        </td>
        <td class='answers-table-td'>{{problem.answers.map(ans => ans.index)}}</td>
      </tr>
  
  </table>
    </td>
  </tr>
</table>
  <button @click="submitAnswers" class='submit-btn'>确定交卷</button>
  </div>
</template>

<script>
import { paths } from '../../router'
import { mapGetters, mapActions } from 'vuex'

export default {
    data: function() {
        return {
            problems: this.$store.state.answersheet.problems,
            paths
        }
    },
    computed: {
        problems1: function() {
            return this.problems
                .slice(0, Math.ceil(this.problems.length / 2))
                .map((pb, index) => {
                    pb.index = index + 1
                    return pb
                })
        },
        problems2: function() {
            return this.problems
                .slice(
                    Math.ceil(this.problems.length / 2),
                    this.problems.length
                )
                .map((pb, index) => {
                    pb.index = Math.ceil(this.problems.length / 2) + index + 2
                    return pb
                })
        }
    },
    methods: {
        isMarked(pbId) {
            console.log(
                'this.$store.state.answersheet',
                this.$store.state.answersheet
            )
            return this.$store.state.answersheet.marks.indexOf(pbId) !== -1
        },
        submitAnswers() {
            var answers = this.$store.state.answersheet.problems.map(pb => ({
                questionid: pb.id,
                answerid: pb.answers.map(ans => ans.id)
            }))
            this.$store.dispatch('postAnswersheet', {
                examid: this.$store.state.paper.examid,
                userid: this.$store.state.paper.userid,
                choice: answers
            })
        }
    }
}
</script>

<style lang="less">
@import '../../variables.less';

a {
    text-decoration: none;
    color: @vue-blue;
}
.content {
    text-align: center;
}
.answers-table-container {
    border-collapse: collapse;
    width: 100%;
    margin: 2rem 0;

    table,
    div,
    td,
    th,
    tr {
        padding: 0;
        border-collapse: collapse;
    }
    table,
    tr,
    div {
        width: 100%;
    }
}
.answers-table {
    border-radius: 5px;
    border: 1px solid @dark-grey;
    display: table;

    text-align: center;

    .answers-table-col {
        vertical-align: top;
        width: 50%;

        .answers-table-header {
            background: @grey;
            & > th {
                padding: 12px 0;
            }
        }
        .answers-table-td {
            padding: 12px 0;
            border: 1px solid @grey;
        }
        .cell {
            &.warning {
                background: @cl-warning;
            }
            &.hint {
                background: @cl-hint;
            }
        }
    }
}
.submit-btn {
    background: @vue-blue;
    color: @white;
    width: 100px;
    height: 40px;
    line-height: 40px;
    font-size: 1em;
    padding: 0;
    margin: 0 auto;
    text-align: center;
}
</style>
