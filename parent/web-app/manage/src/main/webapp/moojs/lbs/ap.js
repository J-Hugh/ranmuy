var LbsAp = function() {
	'use strict';

	var bodyHeight=$("body").height();
	var $userTable;
	
	return {
		init : function() {
			/*初始化*/
			var that=this;
			$userTable = $('#user-table').bootstrapTable({"height":bodyHeight-55-5,
				"onLoadSuccess":function(){$("#btn-search-user").button('reset');}});
			
			$userTable.bootstrapTable().on('load-success.bs.table',function() {
				$('[name="del-user"]').on('click', function() {
					that.del_user($userTable.bootstrapTable('getData')[$(this).data('index')]);
				});
				$('[name="copy-user"]').on('click', function() {
					that.copy_user($userTable.bootstrapTable('getData')[$(this).data('index')]);
				});
			});
			/*时间插件*/
			$('#data_1').datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			/*搜索*/
			$("#btn-search-user").on('click',function(){
				 var $btn = $(this).button('loading');
				 $userTable.bootstrapTable('refresh',{query: {offset: 0,limit:15}});
			})
			
		},
		stats_formatter : function(value,row,index){
			var html = [];
			if(row['active'] < 7200){
				 html.push("<span class='label label-danger'>故障</span>&nbsp;");
			}
			if(row['maxDelayed'] > 20){
				html.push("<span class='label label-warning'>延时</span>&nbsp;");
			}
			return html.join("");
		},
		seachFlowParams : function (params) {
			var cdtions = formSerialize($("#form-user-seach"));
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
	LbsAp.init();
});
