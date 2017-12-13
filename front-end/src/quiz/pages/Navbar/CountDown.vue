<template>
  <div>{{hours}}:{{minutes}}:{{ seconds }}</div>
</template>


<script>
export default {
    props: ['end_time'],
    data: function() {
        const time_left = (this.end_time.getTime() - Date.now()) / 1000
        return {
            timer: time_left > 0 ? Math.trunc(time_left) : 0
        }
    },
    mounted: function() {
        setInterval(() => {
            const time_left = (this.end_time.getTime() - Date.now()) / 1000
            this.timer = time_left > 0 ? Math.trunc(time_left) : 0
        }, 1000)
    },
    computed: {
        seconds: function() {
            return padNumber(this.timer % 60)
        },
        minutes: function() {
            return padNumber(Math.trunc(this.timer / 60) % 60)
        },
        hours: function() {
            return padNumber(Math.trunc(this.timer / 60 / 60))
        }
    }
}
function padNumber(num) {
    var str = '00' + num
    return str.substr(str.length - 2, 2)
}
</script>
