const path = require('path');
const merge = require('webpack-merge');
const webpackCommonConfig = require('./webpack.common.js');

// the display name of the war
const app = 'vue-webpack-sample';

// add the server path to your server location path
const exoServerPath = "D:/MyWorkSpace/exo-servers/platform-5.1.x-SNAPSHOT-sample";

let config = merge(webpackCommonConfig, {
  output: {
    path: path.resolve(`${exoServerPath}/webapps/${app}/`),
    filename: 'js/[name].bundle.js'
  },
  devtool: 'inline-source-map'
});

module.exports = config;
