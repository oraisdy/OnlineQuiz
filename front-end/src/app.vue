<template>
	<div id="app">
		<Nav/>
		<div class="container">			
		<Paper/>
		</div>
	</div>
</template>

<script>
import Paper from './components/Paper/Paper.vue'
import Nav from './components/Nav/Nav.vue'

export default {
	components: {
		Paper, Nav
	}
}
</script>

<style lang="less">
@import './variables.less';

*, *::before, *::after {
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