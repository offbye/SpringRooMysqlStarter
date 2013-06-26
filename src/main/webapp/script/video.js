
function addImg(obj) {
	var src = obj.parentNode.parentNode;
	var idx = rowindex(src);
	var tbl = document.getElementById('gallery-table');
	var row = tbl.insertRow(idx + 1);
	var cell = row.insertCell(-1);
	cell.innerHTML = src.cells[0].innerHTML.replace(
			/(.*)(addImg)(.*)(\[)(\+)/i, "$1removeImg$3$4-");
}

function removeImg(obj) {
	var row = rowindex(obj.parentNode.parentNode);
	var tbl = document.getElementById('gallery-table');

	tbl.deleteRow(row);
}

function dropImg(imgId) {
	var div = document.getElementById(imgId);
	div.parentNode.removeChild(div);
}


function addVideoClip(obj) {
	var src = obj.parentNode.parentNode;
	var idx = rowindex(src);
	var tbl = document.getElementById('videoclip-table');
	var row = tbl.insertRow(idx + 1);
	var cell = row.insertCell(-1);
	cell.innerHTML = src.cells[0].innerHTML.replace(
			/(.*)(addVideoClip)(.*)(\[)(\+)/i, "$1removeVideoClip$3$4-");
}

function removeVideoClip(obj) {
	var row = rowindex(obj.parentNode.parentNode);
	var tbl = document.getElementById('videoclip-table');

	tbl.deleteRow(row);
}