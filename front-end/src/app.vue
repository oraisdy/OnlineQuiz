<template>
    <div>
        <div class="nav">
            <router-link to="/" exact>Home</router-link>
            <router-link to="/css">支持css</router-link>
            <router-link to="/stylus">支持stylus</router-link>
            <router-link to="/less">支持less</router-link>
            <router-link to="/sass">支持sass</router-link>
            <router-link to="/image">支持图片</router-link>
            <router-link to="/iconfont">支持图标字体</router-link>
            <router-link to="/async">异步组件</router-link>
        </div>
        <div class="view">
            <router-view></router-view>
        </div>
    </div>
</template>

