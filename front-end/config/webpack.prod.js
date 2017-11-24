const merge = require('webpack-merge');
const baseConfig = require('./webpack.base.js');
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = merge(baseConfig, {
    plugins: [
        new HtmlWebpackPlugin({
            template: 'index.html',
            inject: true,
        }),
    ]
});
