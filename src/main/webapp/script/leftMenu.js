var preClassName = "";
var preClassId = "";
dojo.require("dojo.cookie");
function changeMenu(id){
	dojo.attr(id,'class');
    var t = new dojo.fx.Toggler({ 
	   node: "div_"+id, 
	   showDuration: 300, 
	   hideDuration: 300, 
	   showFunc: dojo.fx.wipeIn, 
	   hideFunc: dojo.fx.wipeOut
	}); 
	if(dojo.attr(id,'class')=="list_tilte"){
	  dojo.attr(id,'class','list_tilte_onclick');
	  t.show(); 
	  t.hide(); 
	}else{
	  dojo.attr(id,'class','list_tilte');
	  t.show();
	}
}
function change_Menu(sourceId,targetId){
   if(preClassName !=""){
	  dojo.attr(preClassName,'class','left_back');
   }
   if(preClassId !=""){
	  dojo.attr(preClassId,'style','display:none');
   }
   if(dojo.attr(sourceId,'class')=="left_back"){
	  dojo.attr(sourceId,'class','left_back_onclick');
	  dojo.attr(targetId,'style','display:block');
   }else{
	  dojo.attr(sourceId,'class','left_back');
   }
   preClassName = sourceId;
   preClassId = targetId;
   dojo.cookie("left",sourceId);
   dojo.cookie("right",targetId);
}
dojo.addOnLoad(function(){
	if(dojo.cookie('left')!=undefined && dojo.cookie('right')!=undefined){
	  change_Menu(dojo.cookie('left'),dojo.cookie('right'));
	}else{
	  change_Menu('left_c_sysuser','right_sysManager');
	}
});