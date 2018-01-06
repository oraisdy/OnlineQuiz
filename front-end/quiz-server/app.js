const Koa = require('koa')
const morgan = require('koa-morgan')
const path = require('path')
const static = require('koa-static')
const fs = require('fs')
const app = new Koa()

// app.enableCache = true
// logger
app.use(
    morgan(
        ':remote-addr - :remote-user [:date[clf]] ":method :url HTTP/:http-version" :status :res[content-length] :response-time ms'
    )
)

// static assets
app.use(
    static(path.resolve(__dirname, '..', 'dist'), {
        maxage: 365 * 24 * 60 * 60
    })
)

//异步读取文件的形式
app.use(async (ctx, next) => {
    ctx.type = 'html'
    ctx.body = await fs.createReadStream(
        path.join(__dirname, '../dist/app.html')
    )
})
module.exports = app
