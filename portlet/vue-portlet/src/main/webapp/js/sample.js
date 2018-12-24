(function() {

  // getting language of the PLF 
  var lang = eXo ? eXo.env ? eXo.env.portal ? eXo.env.portal.language ? eXo.env.portal.language : 'en' : 'en' : 'en' : 'en';
  
  // should expose the locale ressources as REST API
  var url = '/vue-sample/mocks/locale_' + lang + '.json';

  // create a bootsrap component
  Vue.component('app', {
    template: '<hello name="VueJS"></hello>'
  });

  // create a component that say hello
  Vue.component('hello', {
    template: '<h1>{{ $t("exoplatform.samples.vue.hello") }} {{ name }}</h1>',
    props: ['name']
  });
  
  function init() {
    // getting locale ressources
    exoi18n.loadLanguageAsync(lang, url).then(i18n => {
      // init Vue app when locale ressources are ready
      new Vue({
        el: '#vueSample',
        template: '<app/>',
        i18n
      });
    });
  }

  return {'init': init}

})()