
var findVideos = function(){
	var optionstring = "";
	   dojo.xhrGet({
           url : findUrl,
           preventCache : true,
           handleAs:'json',
           content : {
               name : encodeURIComponent(dojo.string.trim(dojo.byId("_name_id").value))
           },
           load : function(response) {
        	   for(var item in response)
        	   {
        		   //alert(item);
        		   optionstring += "<option value=\""+ response[item].sid +"\" >"+  decodeURIComponent(response[item].name) +"</option>";
        	   }
        	   //alert(optionstring);
        	   dojo.byId("videoSelect").innerHTML = optionstring;
               //console.log("xhr get success:", response);
              
           },
           error : function(error) {
               console.log("xhr get failed:", error);
               return error;
           }
       });
}
dojo.connect(dojo.byId("button_search"),"onclick",findVideos);

var selectChange = function(){
	var vSelect =document.getElementById('videoSelect');
	dojo.byId('_gid_id').value =  vSelect.options[vSelect.selectedIndex].value ;
};
dojo.connect(dojo.byId("videoSelect"),"onchange",selectChange);
