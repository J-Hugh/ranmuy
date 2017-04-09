var Netplay = function() {
	'use strict';
	
	return {
		init : function() {
			var that=this;
			var bodyHeight=$("body").height();
			//console.log(bodyHeight);
			var $netplayTable = $('#netplay-table').bootstrapTable({"height":bodyHeight-55-5,
				"onLoadSuccess":function(){$("#btn-search-netplay").button('reset');}});
			
			$("#btn-search-netplay").on('click',function(){
				 var $btn = $(this).button('loading');
				 $netplayTable.bootstrapTable('selectPage', 1);
			})
		},
		seachFlowParams : function (params) {
			var cdtions = formSerialize($("#form-netplay-seach"));
			console.log(cdtions);
			cdtions.offset = params.offset;
			cdtions.limit = params.limit;
			cdtions.sort = params.sort;
			cdtions.order = params.order;
			return cdtions;
		}
	}
}();
$(function(){
	Netplay.init();
});