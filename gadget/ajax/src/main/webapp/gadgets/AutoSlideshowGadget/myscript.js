function getImages() {
	//This function uses AJAX to send GET request to server's DriverConnector API and receive XML response
	jQuery.ajax({
		type: "GET",
		url: "/portal/rest/wcmDriver/getFoldersAndFiles?driverName=Collaboration&currentFolder=Users/r___/ro___/roo___/root/Public&currentPortal=intranet&repositoryName=repository&workspaceName=collaboration&filterBy=Image",
		contentType: "application/xml; charset=utf-8",
		dataType: "xml",
		success: function (data, status, jqXHR) {
			var strResults=new XMLSerializer().serializeToString(data.documentElement);
			//build dynamic html content for "slideshow" div tag
			traverseXMLDoc(strResults, "slideshow");
		},
		//error report
		error: function (jqXHR, status) {
			//error handler
			alert("Cannot retrieve data!");
		}
	});
}

function traverseXMLDoc(xmlDoc, idOfContainerDomElement){
	//This function traverses through the whole XML response returned from server
	var $xmlDocObjChildren, $contentDiv;
	$contentDiv = $('#' + idOfContainerDomElement);
	if ($contentDiv.length === 0) {
		throw new Error('There are no DOM elements with this id: "' + idOfContainerDomElement + '"');
	}
	//Information of each image object is contained in "File" tag
	$xmlDocObjChildren = $(xmlDoc).find("File");
	$xmlDocObjChildren.each(function(index, Element) {
		var	$currentObject = $(this),
				childElementCount = Element.childElementCount,
		//Image's url is contained in "url" attribute
		currentNodeType = $currentObject.attr('url');
		//Adding dynamic content into gadget's body
		$contentDiv.append('<div><img src="'+currentNodeType+'"></div>');
	});
}
