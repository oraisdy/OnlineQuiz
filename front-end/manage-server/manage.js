const Koa = require('koa')
const morgan = require('koa-morgan')
const path = require('path')
const static = require('koa-static')
const fs = require('fs')
const manage = new Koa()

app.enableCache = true
// logger
manage.use(
    morgan(
        ':remote-addr - :remote-user [:date[clf]] ":method :url HTTP/:http-version" :status :res[content-length] :response-time ms'
    )
)
// static assets
manage.use(static(path.resolve(__dirname, '..', 'dist')))
//异步读取文件的形式
manage.use(async (ctx, next) => {
    ctx.type = 'html'
    ctx.body = await fs.createReadStream(
        path.resolve(__dirname, '..', 'dist', 'manage.html')
    )
})
module.exports = manage
