const merge = require("webpack-merge");
const baseConfig = require("./webpack.base.js");
const webpack = require("webpack");
const UglifyJsPlugin = require("uglifyjs-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");

module.exports = merge(baseConfig, {
    plugins: [
        /* 压缩 js (同样可以压缩 css) */
        new UglifyJsPlugin(),
        new CopyWebpackPlugin(["public"])
    ]
});
