import './initComponents.js'; // initialize other VueJS components
import app from './components/birthdayAppMain.vue'; // import the main VueJS application

// Get the user language
const lang = eXo && eXo.env && eXo.env.portal && eXo.env.portal.language || 'en';

// Get the name of the resource bundle
const resourceBundleName = 'locale.portlet.birthdayApp';

// Get the URL to load the resource bundles
const url = `${eXo.env.portal.context}/${eXo.env.portal.rest}/i18n/bundle/${resourceBundleName}-${lang}.json`;

// getting the resource bundles
exoi18n.loadLanguageAsync(lang, url)
  .then(i18n => {
    // init Vue app when locale resources are ready
    new Vue({
      render: h => h(app),
      i18n
    }).$mount('#birthdayApp'); // mount the application on the HTML element with id = 'vue_webpack_sample'
  });
