const path = require('path');
const merge = require('webpack-merge');

const config = {
  mode: 'production',
  context: path.resolve(__dirname, '.'),
  module: {
    rules: [
      {
        test: /\.css$/,
        use: ['vue-style-loader', 'css-loader']
      },
      {
        test: /\.vue$/,
        use: [
          'vue-loader',
          'eslint-loader',
        ]
      },
    ]
  },
  entry: {
    activityType: './src/main/webapp/vue-apps/activity-type/main.js',
    activityBody: './src/main/webapp/vue-apps/activity-body/main.js',
    activityMenuItem: './src/main/webapp/vue-apps/activity-menu-item/main.js',
    activityAction: './src/main/webapp/vue-apps/activity-action/main.js',
    commentBody: './src/main/webapp/vue-apps/comment-body/main.js',
    commentMenuItem: './src/main/webapp/vue-apps/comment-menu-item/main.js',
    commentAction: './src/main/webapp/vue-apps/comment-action/main.js',
  },
  output: {
    path: path.join(__dirname, 'target/activity-extensions/'),
    filename: 'js/[name].bundle.js',
    libraryTarget: 'amd'
  },
  externals: {
    vue: 'Vue',
    jquery: '$',
    vuetify: 'Vuetify'
  }
};

module.exports = config;