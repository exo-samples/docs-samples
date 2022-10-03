import TodoList from './components/todo-list-component.vue';

const components = {
  'todo-list': TodoList,
};

for (const key in components) {
  Vue.component(key, components[key]);
}
