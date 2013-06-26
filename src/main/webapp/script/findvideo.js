var findVideos = function() {
	var name = dojo.string.trim(dojo.byId("_name_id").value);
	if (!name){
		return;
	}
	dojo.xhrGet({
		url : genPath('/videos/find'),
		preventCache : true,
		handleAs : 'json',
		content : {
			name : encodeURIComponent(name)
		},
		load : function(response) {
			var videoSelect = document.getElementById('videoSelect');
			videoSelect.options.length = 0;

			for ( var item in response) {
				// alert(item);
				var opt = document.createElement('OPTION');
				opt.text = decodeURIComponent(response[item].name);
				opt.value = response[item].sid;
				videoSelect.options.add(opt);
			}
		},
		error : function(error) {
			console.log("xhr get failed:", error);
			return error;
		}
	});
}
dojo.connect(dojo.byId("button_search"), "onclick", findVideos);