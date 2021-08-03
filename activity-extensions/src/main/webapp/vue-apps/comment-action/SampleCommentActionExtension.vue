<template>
  <v-btn
    x-small
    link
    text
    @click="flipActivity">
    <v-icon
      x-small
      class="me-2"
      v-text="buttonIcon" />
    {{ buttonTitle }}
  </v-btn>
</template>
<script>
export default {
  data: () => ({
    zoomedIn: false,
  }),
  computed: {
    buttonTitle() {
      return this.zoomedIn && 'Zoom Out' || 'Zoom In';
    },
    buttonIcon() {
      return this.zoomedIn && 'fa-search-minus' || 'fa-search-plus';
    },
  },
  methods: {
    flipActivity() {
      const commentParentElement = this.$el.closest('.activity-comment');
      if (this.zoomedIn) {
        commentParentElement.querySelector('.rich-editor-content').style.fontSize = '';
      } else {
        commentParentElement.querySelector('.rich-editor-content').style.fontSize = '24px';
      }
      this.zoomedIn = !this.zoomedIn;
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