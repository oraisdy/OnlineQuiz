<template>
	<div id="app">
		<!-- <Navbar :quiz="quiz"/> -->
		<div class="container">	
            <Quiz />
            <!-- <h1>{{quiz.title}}</h1> -->
            <!-- <router-view  :quiz="quiz"></router-view>		 -->
		<!-- <Paper :quiz="quiz"/> -->
		</div>
	</div>
</template>

<script>
import Quiz from '../Quiz'
import { mapGetters, mapActions } from 'vuex'

export default {
    components: {
        Quiz
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
}

h1 {
    text-align: center;
}
</style>