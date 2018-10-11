<template>
  <h1>{{ $t("exoplatform.samples.vue.hello") }} {{ name }}</h1>
</template>

<script>
export default {
  props: {
    name: {
      type: String,
      default: ''
    }
  }
};
</script>