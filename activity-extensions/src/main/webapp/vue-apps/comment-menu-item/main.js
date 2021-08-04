export function init() {
  extensionRegistry.registerExtension('activity', 'comment-action', {
    id: 'changeType',
    labelKey: 'Change to Custom Type',
    isEnabled: (activity, comment) => comment && comment.type !== 'aCustomType',
    click: (activity, comment) => {
      fetch(`/portal/rest/v1/social/activities/${comment.id}`, {
        'headers': {
          'content-type': 'application/json',
        },
        'body': JSON.stringify({
          type: 'aCustomType',
        }),
        'method': 'PUT',
        'credentials': 'include'
      }).then(() => {
        comment.type = 'aCustomType';
        document.dispatchEvent(new CustomEvent('activity-comment-updated', {detail: comment}));
      });
    },
  });
}