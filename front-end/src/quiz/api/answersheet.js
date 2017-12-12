const _resp = {
    msg: '提交成功'
}

export default {
    postOne(ansSht, cb, errCb) {
        console.log(ansSht)
        setTimeout(() => cb(_resp), 100)
    }
}
