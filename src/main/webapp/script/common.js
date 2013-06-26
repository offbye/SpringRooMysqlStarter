if (typeof dojo.isFF != undefined) {
	try {
		HTMLElement.prototype.__defineGetter__("innerText", function() {
			var anyString = "";
			var childS = this.childNodes;
			for ( var i = 0; i < childS.length; i++) {
				if (childS[i].nodeType == 1)
					anyString += childS[i].tagName == "BR" ? "\n"
							: childS[i].innerText;
				// anyString += childS[i].innerText;
				else if (childS[i].nodeType == 3)
					anyString += childS[i].nodeValue;
			}
			return anyString;
		});
		HTMLElement.prototype.__defineSetter__("innerText", function(sText) {
			this.textContent = sText;
		});
	} catch (e) {
	}

}

function $(_id) {
	return dojo.byId(_id);
}

var Browser = new Object();

Browser.isMozilla = (typeof document.implementation != 'undefined')
		&& (typeof document.implementation.createDocument != 'undefined')
		&& (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != -1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != -1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != -1);

function rowindex(tr) {
	if (Browser.isIE) {
		return tr.rowIndex;
	} else {
		table = tr.parentNode.parentNode;
		for (i = 0; i < table.rows.length; i++) {
			if (table.rows[i] == tr) {
				return i;
			}
		}
	}
}

function genPath(url) {
	var pathName = window.document.location.pathname;
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return projectName + url;
}