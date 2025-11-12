<template>
  <div id="birthdayAppMain" class="pa-4">
    <span class="birthday-app-title">{{ $t('birthdayApp.i18n.label') }}</span>
    <birthday-connections-list birthday-items="" />
  </div>
</template>
export default {
  data: () => ({
    connectionsBirthdays : null,
  }),
};
