const path = require('path');
const merge = require('webpack-merge');
const webpackCommonConfig = require('./webpack.common.js');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const app = 'vue-sample';

// add the server path to your server location path
const exoServerPath = "D:/MyWorkSpace/exo-servers/platform-5.1.x-SNAPSHOT-sample";

let config = merge(webpackCommonConfig, {
  output: {
    path: path.resolve(exoServerPath + '/webapps/'+ app + '/'),
    filename: 'js/[name].bundle.js'
  },
  devtool: 'inline-source-map'
});

config.plugins.push(new CopyWebpackPlugin([{from: 'src/main/webapp/lang/*.json', to: './lang', flatten: true}]));

module.exports = config;
