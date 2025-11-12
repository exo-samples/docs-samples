import './initComponents.js'; // initialize other VueJS components

// Get the user language
const lang = eXo && eXo.env && eXo.env.portal && eXo.env.portal.language || 'en';

// Get the name of the resource bundle
const resourceBundleName = 'locale.portlet.birthdayApp';
const appId = 'birthdayApp';

// Get the URL to load the resource bundles
const url = `${eXo.env.portal.context}/${eXo.env.portal.rest}/i18n/bundle/${resourceBundleName}-${lang}.json`;
Vue.use(Vuetify);
const vuetify = new Vuetify(eXo.env.portal.vuetifyPreset);

export function init() {
  exoi18n.loadLanguageAsync(lang, url).then(i18n => {
    // init Vue app when locale ressources are ready
    Vue.createApp({
      template: `<birthday-main id="${appId}" />`,
      vuetify,
      i18n
    }, `#${appId}`, 'Birthday');
  });
}
