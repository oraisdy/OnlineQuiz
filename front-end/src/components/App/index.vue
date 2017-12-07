<template>
	<div id="app">
		<Navbar :quiz="quiz"/>
		<div class="container">	
            <h1>{{quiz.title}}</h1>
            <router-view  :quiz="quiz"></router-view>		
		<!-- <Paper :quiz="quiz"/> -->
		</div>
	</div>
</template>

<script>
import Paper from '../Paper'
import Navbar from '../Navbar'
import { mapGetters, mapActions } from 'vuex'

export default {
    components: {
        Paper,
        Navbar
    },
    computed: mapGetters({
        quiz: 'quiz'
    }),
    created() {
        this.$store.dispatch('getQuiz')
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
    top: @top-nav-height;
    min-width: 400px;
    width: 80%;
    margin: 0 auto;
}

h1 {
    text-align: center;
}
</style>