<template>
    <div id="app">
        <div class="container">
            <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
                <el-menu-item index="1"> 创建考试
                    <!--<router-link :to="paths.QUIZ">创建考试</router-link>-->
                </el-menu-item>
                <el-menu-item index="2"> 题库管理
                    <!--<router-link :to="paths.REPOSITORY">题库管理</router-link>-->
                </el-menu-item>
            </el-menu>
            <router-view class="content"></router-view>
        </div>
    </div>
</template>

<script>
import { paths } from "../../router"
import { mapGetters, mapActions } from 'vuex'

export default {

    data() {
        return { paths, activeIndex: '1' }
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
            switch (key) {
                case '1':
                    this.$router.push(paths.QUIZ); break;
                case '2': this.$router.push(paths.REPOSITORY); break;
            }
        }
    }
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
    top: @top-nav-height+20px;
    min-width: 400px;
    width: 80%;
    margin: 0 auto;

    .content {
        padding-top: 50px;
    }
}

h1 {
    text-align: center;
}

a {
    text-decoration: none;
}
</style>