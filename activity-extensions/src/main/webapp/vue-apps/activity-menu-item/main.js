export function init() {
  extensionRegistry.registerExtension('activity', 'action', {
    id: 'changeType',
    labelKey: 'Change to Custom Type',
    isEnabled: (activity) => activity && activity.type !== 'aCustomType',
    click: (activity) => {
      fetch(`/portal/rest/v1/social/activities/${activity.id}`, {
        'headers': {
          'content-type': 'application/json',
        },
        'body': JSON.stringify({
          type: 'aCustomType',
        }),
        'method': 'PUT',
        'credentials': 'include'
      }).then(() => document.dispatchEvent(new CustomEvent('activity-updated', {detail: activity.id})));
    },
  });
}