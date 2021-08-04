<template>
  <v-btn
    small
    color="blue-grey"
    dark
    @click="flipActivity">
    <v-icon
      small
      color="white"
      class="me-2">
      fa-sync-alt
    </v-icon>
    Try this!
  </v-btn>
</template>
<script>
export default {
  methods: {
    flipActivity() {
      const activityParentElement = this.$el.closest('.activity-detail');
      activityParentElement.classList.remove('flipActivity');
      window.setTimeout(() => {
        activityParentElement.classList.add('flipActivity');
      }, 50);
    },
  },
};
</script>

<style type="text/css">
.flipActivity {
  transition: transform 2s ease-in-out;
  transform: rotateY(360deg);
}
</style>