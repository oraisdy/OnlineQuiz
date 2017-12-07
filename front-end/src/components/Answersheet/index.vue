<template>
<table class='answers-table-container'>
  <tr hidden><th colspan="2"></th></tr>
  <tr class="answers-table" cellspacing="0" cellpadding="0">
    <td class="answers-table-col">
      <table cellspacing="0" cellpadding="0">
 
      <tr class="answers-table-header"><th><div>题号</div></th><th><div>答案</div></th></tr>
   
      <tr v-for='problem in problems1' :key="problem.index">
        <td class='answers-table-td'>
          <div class='cell' >
             <router-link :to="paths.PAPER+'#pb'+problem.index">{{problem.index}}</router-link>
          </div>
        </td>
        <td class='answers-table-td'><div class='cell'>{{problem.answers}}</div></td>
      </tr>
  
  </table>
    </td>
    <td class="answers-table-col">
      <table cellspacing="0" cellpadding="0">
 
      <tr class="answers-table-header"><th><div>题号</div></th><th><div>答案</div></th></tr>
   
      <tr v-for='problem in problems2' :key="problem.index">
        <td class='answers-table-td'>
          <div class='cell' >
             <router-link :to="paths.PAPER+'#pb'+problem.index">{{problem.index}}</router-link>
          </div>
        </td>
        <td class='answers-table-td'><div class='cell'>{{problem.answers}}</div></td>
      </tr>
  
  </table>
    </td>
  </tr>
</table>
  
</template>

<script>
import { paths } from '../../router'
import { mapGetters, mapActions } from 'vuex'

export default {
    // computed: mapGetters()({ problems: 'problems' }),

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
                    pb.index = index
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
                    pb.index = Math.ceil(this.problems.length / 2) + index + 1
                    return pb
                })
        }
    },
    created() {}
}
</script>

<style lang="less">
@import '../../variables.less';

*,
*::before,
*::after {
    box-sizing: border-box;
}

#app {
    line-height: 1.4;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: @vue-blue;
}

.container {
    position: relative;
    top: @top-nav-height;
    min-width: 400px;
    width: 80%;
    margin: 0 auto;
}

h1 {
    text-align: center;
}
a {
    text-decoration: none;
    color: @vue-blue;
}
.answers-table-container {
    width: 80%;
    border-collapse: collapse;
    margin-top: @top-nav-height;

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
    margin: 2rem 0;
    display: table;

    // display: table;
    // border-collapse: separate;
    // border-spacing: 0;

    // flex: 1;
    text-align: center;

    .answers-table-col {
        vertical-align: top;
        width: 50%;

        // border: 1px solid @dark-grey;

        .answers-table-header {
            background: @grey;
            & > th {
                padding: 12px 0;
            }

            // padding: 12px 0;
            // border: 1px solid @dark-grey;
        }
        .answers-table-td {
            padding: 12px 0;
            border: 1px solid @grey;
        }
    }
}
</style>
