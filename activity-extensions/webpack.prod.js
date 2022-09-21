const path = require('path');
const ESLintPlugin = require('eslint-webpack-plugin');
const { VueLoaderPlugin } = require('vue-loader')

const config = {
  mode: 'production',
  context: path.resolve(__dirname, '.'),
  module: {
    rules: [
      {
        test: /\.css$/,
          use: [
              'vue-style-loader',
              'css-loader'
          ],
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          'babel-loader',
        ]
      },
      {
        test: /\.vue$/,
        use: [
          'vue-loader',
        ]
      }
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
  plugins: [
    new ESLintPlugin({
      files: [
        './src/main/webapp/vue-apps/*.js',
        './src/main/webapp/vue-apps/*.vue',
        './src/main/webapp/vue-apps/**/*.js',
        './src/main/webapp/vue-apps/**/*.vue',
      ],
    }),
    new VueLoaderPlugin()
  ],
  externals: {
    vue: 'Vue',
    vuetify: 'Vuetify',
    jquery: '$',
  },
};

module.exports = config;
