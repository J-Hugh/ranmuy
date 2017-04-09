var Site = function() {
	'use strict';
	
	return {
		init : function() {
			var that=this;
			var bodyHeight=$("body").height();
			//console.log(bodyHeight);
			var $siteTable = $('#site-table').bootstrapTable({"height":bodyHeight-55-5,
				"onLoadSuccess":function(){$("#btn-search-site").button('reset');}});
			
			$("#btn-search-site").on('click',function(){
				 var $btn = $(this).button('loading');
				 $siteTable.bootstrapTable('selectPage', 1);
			})
		},
		seachFlowParams : function (params) {
			var cdtions = formSerialize($("#form-site-seach"));
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
	Site.init();
});