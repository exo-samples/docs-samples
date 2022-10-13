const path = require('path');
const {merge} = require('webpack-merge');
const webpackProductionConfig = require('./webpack.prod.js');

// the display name of the war
const app = 'my-connections-birthday-webapp';

// add the server path to your server location path
const exoServerPath = "/exo-server";

let config = merge(webpackProductionConfig, {
    mode: "development",
  output: {
    path: path.resolve(`${exoServerPath}/webapps/${app}/`)
  },
  devtool: 'inline-source-map'
});

module.exports = config;
