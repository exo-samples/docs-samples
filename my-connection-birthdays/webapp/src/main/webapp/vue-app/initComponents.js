import birthdayAppMain from './components/birthdayAppMain.vue';
import birthdayConnectionsList from './components/connectionsList.vue';

const components = {
  'birthday-main': birthdayAppMain,
  'birthday-connections-list': birthdayConnectionsList,
};
for (const key in components) {
  Vue.component(key, components[key]);
}
