import SampleCommentActionExtension from './SampleCommentActionExtension.vue';

export function init() {
  extensionRegistry.registerComponent('ActivityCommentFooter', 'activity-comment-footer-action', {
    id: 'zoomIn',
    vueComponent: SampleCommentActionExtension,
    rank: 50,
  });
}