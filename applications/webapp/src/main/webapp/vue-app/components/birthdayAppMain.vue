<template>
  <div id="birthdayAppMain">
    <span class="birthday-app-title">{{ $t('birthdayApp.i18n.label') }}</span>
    <birthday-connections-list birthday-items="" />
  </div>
</template>
export default {
  data: () => ({
    connectionsBirthdays : null,
  }),
};
