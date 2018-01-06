const path = require("path");
const projectRoot = path.resolve(__dirname, "../");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack");
const ExtractTextPlugin = require("extract-text-webpack-plugin");

function resolve(relPath) {
    return path.resolve(__dirname, relPath);
}

module.exports = {
    entry: {
        app: "./src/quiz/index.js",
        manage: "./src/manage/index.js"
    },
    output: {
        path: path.resolve(__dirname, "../dist"),
        // publicPath: path.resolve(__dirname, '../public'),
        filename: "[name].js"
    },
    resolve: {
        extensions: [".js", ".vue"],
        alias: {
            vue$: "vue/dist/vue.esm.js",
            src: path.resolve(__dirname, "../src"),
            assets: path.resolve(__dirname, "../src/assets"),
            components: path.resolve(__dirname, "../src/components")
        }
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                include: [resolve("src"), resolve("test")],
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            },
            {
                test: /\.vue$/,
                use: {
                    loader: "vue-loader"
                    // options: utils.vueLoaderOptions()
                }
            },
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader"
                })
            },
            {
                test: /\.less$/,
                use: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: ["css-loader", "less-loader"]
                })
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
                use: {
                    loader: "url-loader",
                    options: {
                        limit: 10000,
                        name: "images/[name].[hash:7].[ext]"
                    }
                }
            },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            limit: 10000,
                            name: "fonts/[name].[hash:7].[ext]"
                        }
                    }
                ]
            }
        ]
    },
    plugins: [
        // new HtmlWebpackPlugin({
        //     filename: 'index.html',
        //     template: 'index.html',
        //     inject: true
        // }),
        new ExtractTextPlugin("style.css"),
        new HtmlWebpackPlugin({
            inject: true,
            chunks: ["app"],
            template: "index.html",
            filename: "index.html"
        }),
        new HtmlWebpackPlugin({
            inject: true,
            chunks: ["manage"],
            template: "index.html",
            filename: "manage.html"
        }),
        new webpack.DefinePlugin({
            "process.env.NODE_ENV": JSON.stringify(
                process.env.NODE_ENV || "development"
            )
        })
    ]
};
