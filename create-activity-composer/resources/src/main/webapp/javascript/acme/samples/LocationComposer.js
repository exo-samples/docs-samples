(function($) {
	var LocationComposer = {
		ENTER_KEY_CODE: 13,
		onLoad: function(params) {
			LocationComposer.configure(params);
			LocationComposer.init();
		},
		configure: function(params) {
			this.locationValid = params.locationValid || false;
			this.inputLocationId = params.inputLocationId || 'InputLocation';
			this.checkinButtonId = params.checkinButtonId || 'CheckinButton';
			this.checkinUrl = decodeURI(params.checkinUrl || "");
			this.location = params.location || '';
		},
		init: function() {
			LocationComposer = this;
			if (this.locationValid === "false") {
				this.inputLocation = $('#' + this.inputLocationId);
				this.checkinButton = $('#' + this.checkinButtonId);
				var LocationComposer = this;
				var inputLocation = this.inputLocation;
				var checkinBtn = this.checkinButton;
				inputLocation.on('focus', function(evt) {
					if (inputLocation.val() === '') {
						inputLocation.val('');
					}
				});
				this.inputLocation.on('keypress', function(evt) {
					if (LocationComposer.ENTER_KEY_CODE == (evt.which ? evt.which : evt.keyCode)) {
						$(checkinBtn).click();
					}
				});
				this.checkinButton.removeAttr('disabled');
				this.checkinButton.on('click', function(evt) {
					if (inputLocation.val() === '') {
						return;
					}
					var url = LocationComposer.checkinUrl.replace(/&amp;/g, "&") + '&objectId=' + encodeURI(inputLocation.val()) +
					'&ajaxRequest=true';
					ajaxGet(url, function() {
						try {
							$('textarea#composerInput').exoMentions('showButton', function() {});
							} catch (e) {
							console.log(e);
						}
					});
				});
			}
			var closeButton = $('#UIActivityComposerContainer').find('a.uiIconClose:first');
			if (closeButton.length > 0) {
				closeButton.on('click', function() {
					$('textarea#composerInput').exoMentions('clearLink', function() { });
				});
			}
		}
	};
	return LocationComposer;
})(jq);