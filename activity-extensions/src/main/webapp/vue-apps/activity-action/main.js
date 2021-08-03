import SampleActivityActionExtension from './SampleActivityActionExtension.vue';

export function init() {
  extensionRegistry.registerComponent('ActivityFooter', 'activity-footer-action', {
    id: 'flip',
    vueComponent: SampleActivityActionExtension,
    rank: 0,
  });
}