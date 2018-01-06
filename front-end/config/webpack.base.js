const path = require("path");
const webpack = require("webpack");
const projectRoot = path.resolve(__dirname, "../");
const HtmlWebpackPlugin = require("html-webpack-plugin");
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
        filename: "[name].[chunkhash].js"
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
        new ExtractTextPlugin("style.css"),
        new webpack.DefinePlugin({
            "process.env.NODE_ENV": JSON.stringify(
                process.env.NODE_ENV || "development"
            )
        }),
        /* decouple your vendor code from your application code */
        new webpack.optimize.CommonsChunkPlugin({
            name: "vendor",
            minChunks: function(module) {
                // This prevents stylesheet resources with the .css or .scss extension
                // from being moved from their original chunk to the vendor chunk
                if (
                    module.resource &&
                    /^.*\.(css|less)$/.test(module.resource)
                ) {
                    return false;
                }
                return (
                    module.context &&
                    module.context.indexOf("node_modules") !== -1
                );
            }
        }),
        new webpack.HashedModuleIdsPlugin(),
        new HtmlWebpackPlugin({
            // files: {
            //     css: ['style.css']
            // },
            inject: true,

            chunks: ["app", "vendor"],
            template: "index.html",
            filename: "app.html"
        }),
        new HtmlWebpackPlugin({
            // files: {
            //     css: ['style.css']
            // },
            inject: true,
            chunks: ["manage", "vendor"],
            template: "index.html",
            filename: "manage.html"
        })
    ]
};
