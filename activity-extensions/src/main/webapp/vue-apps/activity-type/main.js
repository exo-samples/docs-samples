import SampleActivityType from './SampleActivityType.vue';

export function init() {
  extensionRegistry.registerExtension('activity', 'type', {
    /* Activity.type. 'default' corresponds
    to all activities which doesn't have
    a corresponding extension */
    type: 'aCustomType',
    options: {
      // Redefine all the activity display by a custom one
      getExtendedComponent: () => ({
        component: SampleActivityType,
        overrideHeader: false,
        overrideFooter: false,
      }),
      // Whether to display edit button or not when user have permission
      canEdit: () => true,
      // The content to display in Activity Editor (AKA Composer)
      getBodyToEdit: activity => {
        const templateParams = activity.templateParams;
        return Vue.prototype.$utils.trim(window.decodeURIComponent(templateParams
          && templateParams.default_title
          && templateParams.default_title
          || activity.title
          || activity.body
          || ''));
      },
      canShare: () => true,
    },
  });
}