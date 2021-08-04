<template>
  <v-card :color="cardColor" dark>
    <v-card-title :class="titleSize">
      {{ activityTitle }}
    </v-card-title>
    <v-card-subtitle v-sanitized-html="activity && activity.title" />
  </v-card>
</template>

<script>
export default {
  props: {
    activity: {
      type: Object,
      default: null,
    },
  },
  computed: {
    isComment() {
      return this.activity && this.activity.activityId;
    },
    activityTitle() {
      return this.isComment && 'Custom Comment UI' || 'Custom Activity UI';
    },
    activityContent() {
      return this.activity && this.activity.title;
    },
    titleSize() {
      return this.isComment && 'text-h6' || 'text-h5';
    },
    cardColor() {
      return this.isComment && '#952175' || '#385F73';
    },
  }
};
</script>