var Sniff = function() {
	'use strict';
	
	return {
		init : function() {
			var that=this;
			var bodyHeight=$("body").height();
			
			var $sniffTable = $('#sniff-table').bootstrapTable({"height":bodyHeight-55-5,
				"onLoadSuccess":function(){$("#btn-search-sniff").button('reset');}});
			
			$("#btn-search-sniff").on('click',function(){
				 var $btn = $(this).button('loading');
				 $sniffTable.bootstrapTable('selectPage', 1);
			})
		},
		seachFlowParams : function (params) {
			var cdtions = formSerialize($("#form-sniff-seach"));
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
	Sniff.init();
});