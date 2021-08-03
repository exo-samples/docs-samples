import SampleActivityBodyExtension from './SampleActivityBodyExtension.vue';

export function init() {
  extensionRegistry.registerComponent('ActivityContent', 'activity-content-extensions', {
    id: 'hasFavoriteWordActivity', // Unique Identifier of the extension
    isEnabled: (params) => {
      const activity = params && params.activity;
      return activity && activity.title && !activity.activityId && activity.title.toLowerCase().includes('favorite');
    },
    vueComponent: SampleActivityBodyExtension,
    rank: 50, // display rank comparing to other body extensions
  });
}