<template>
  <div id="todoList">
    <input v-model="newTodo" @keyup.enter="addTodo">
    <ul>
      <li v-for="(todo, index) in todos" :key="index">
        <span :id="'todo' + index">{{ todo.text }}</span>
        <button :id="'btnRemove' + index" @click="removeTodo($index)">X</button>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data() {
    return {
      newTodo: '',
      todos: [
        { text: 'Add some todos' }
      ]
    };
  },
  methods: {
    addTodo: function () {
      const text = this.newTodo.trim();
      if (text) {
        this.todos.push({ text: text });
        this.newTodo = '';
      }
    },
    removeTodo: function (index) {
      this.todos.splice(index, 1);
    }
  }
};
</script>
