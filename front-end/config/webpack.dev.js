const merge = require('webpack-merge')
const baseConfig = require('./webpack.base.js')
const path = require('path')
const webpack = require('webpack')

module.exports = merge(baseConfig, {
    devtool: 'cheap-module-source-map',
    devServer: {
        // publicBase: path.join(__dirname, "../public")
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NamedModulesPlugin(), // HMR shows correct file names in console on update.
        new webpack.NoEmitOnErrorsPlugin()
    ]
})
