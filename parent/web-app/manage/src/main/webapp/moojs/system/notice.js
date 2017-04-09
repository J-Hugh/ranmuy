var SystemNotice = function() {
	'use strict';

	var bodyHeight=$("body").height();
	var $userTable = $('#user-table').bootstrapTable({"height":bodyHeight-58-17-35});
	
	return {
		init : function() {
			var that=this;
			$('.i-checks').iCheck({
		        checkboxClass: 'icheckbox_square-green',
		        radioClass: 'iradio_square-green',
		    });
			$('#append-notice-btn').on('click',function(){
				$('#notice-list-div').addClass("fadeOutLeft").removeClass("fadeInLeft");
				$('#append-notice-div').show().addClass("fadeInRight").removeClass("fadeOutRight");
			});
			$('#back-btn').on('click',function(){
				$('#notice-list-div').addClass("fadeInLeft").removeClass("fadeOutLeft");
				$('#append-notice-div').addClass("fadeOutRight").removeClass("fadeInRight");
			});
			
		},
		state_formatter : function(value,row,index){
			if(value == "01"){
				return "<span class='label label-primary'>启用中</span>";
			}else if(value == "02"){
				return "<span class='label label-danger'>已停用</span>";
			}else{
				return "<span class='label label-default'>未知</span>";
			}
		},
		operate_formatter : function(value,row,index){
			var html=[];
			html.push(sprintf("<a data-index='%s' class='btn btn-white btn-sm'><i class='fa fa-folder'></i> 查看 </a>",index));
			html.push(sprintf("<a data-index='%s' class='btn btn-white btn-sm'><i class='fa fa-pencil'></i> 编辑 </a>",index));
			return html.join("");
		}
	}
}();
$(function() {
	SystemNotice.init();
});
