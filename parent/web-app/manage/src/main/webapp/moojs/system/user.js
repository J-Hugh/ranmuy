var SystemUser = function() {
	'use strict';

	var bodyHeight=$("body").height();
	var $userTable;
	
	return {
		init : function() {
			/*初始化*/
			var that=this;
			$userTable = $('#user-table').bootstrapTable({"height":bodyHeight-10-5,
				"onLoadSuccess":function(){$("#btn-search-user").button('reset');}});
			
			$userTable.bootstrapTable().on('load-success.bs.table',function() {
				$('[name="del-user"]').on('click', function() {
					that.del_user($userTable.bootstrapTable('getData')[$(this).data('index')]);
				});
				$('[name="edit-user"]').on('click', function() {
					$("#form-edit-user input[name='index']").val($(this).data('index'));
					that.show_edit_model($userTable.bootstrapTable('getData')[$(this).data('index')]);
				});
			});
			
			$('#append-user-modal-div').on('hide.bs.modal', function () {
				reset($("#form-append-user"));
			})
			
			/*表单验证*/
			$("#form-append-user").validationEngine('attach', {
				onValidationComplete : function(form, status) {
				that.append_user(status);
			}});
			$("#form-edit-user").validationEngine('attach', {
				onValidationComplete : function(form, status) {
				that.edit_user(status);
			}});
			/*搜索*/
			$("#btn-search-user").on('click',function(){
				 var $btn = $(this).button('loading');
				 $userTable.bootstrapTable('selectPage', 1);
			})
			/*表单重置*/
			$('#btn-reset').on('click',function(){
				reset($("#form-append-user"));
			})
			/*生成随机码*/
			$("#btn-random").on('click',function(){
				$("input[name='uPassword']").val($.password(8));
				$("input[name='uPassword']").blur();
			})
		},
		append_user : function(status){
			if(status){
				$.ajax({
					type : "POST",
					data : $("#form-append-user").serialize(),
					url : basePath + "system/append_user.moo" ,
					success : function(data,XMLHttpRequest) {
						if (200 === data.status) {
							toastr.success(data.msg,'系统提示');
							$('#append-user-modal-div').modal('hide');
							$userTable.bootstrapTable('selectPage', 1);
						} else {
							toastr.warning(data.msg,'系统提示');
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						if(XMLHttpRequest.status === 403){
							toastr.warning('你没有权限','权限不足');
						}else{
							toastr.error('系统发现未知错误 请联系管理员','系统错误');
						}
					}
				});
			}
		},
		show_edit_model : function(row){
			console.log(row);
			reset($("#form-edit-user"));
			$("#form-edit-user input[name='uId']").val(row.uId);
			$("#form-edit-user input[name='uName']").val(row.uName);
			$("#form-edit-user input[name='uPassword']").val(row.uPassword);
			$("#form-edit-user input[name='phoneNumber']").val(row.phoneNumber);
			$("#form-edit-user select[name='roleId']").val(row.roleId);
			
			$("#edit-user-modal-div").modal("show");
		},
		edit_user : function(status){
			if(status){
				swal({
					title : "慎重",
					text : "更新后 数据不能恢复了哦！",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "继续",
					cancelButtonText : "算了",
					closeOnConfirm : true,
					closeOnCancel : true
				},
				function(isConfirm) {
					if (isConfirm) {
						$.ajax({
							type : "POST",
							data : $("#form-edit-user").serialize(),
							url : basePath + "system/edit_user.moo" ,
							success : function(data,XMLHttpRequest) {
								if (200 === data.status) {
									toastr.success(data.msg,'系统提示');
									$userTable.bootstrapTable('refresh');
									$('#edit-user-modal-div').modal('hide');
								} else {
									toastr.warning(data.msg,'系统提示');
								}
							},
							error:function(XMLHttpRequest, textStatus, errorThrown){
								if(XMLHttpRequest.status === 403){
									toastr.warning('你没有权限','权限不足');
								}else{
									toastr.error('系统发现未知错误 请联系管理员','系统错误');
								}
							}
						});
					}
				});
			}
		},
		del_user : function(row){
			swal({
				title : "系统确认",
				text : "确认删除?",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "继续",
				cancelButtonText : "取消",
				closeOnConfirm : true,
				closeOnCancel : true
			}, function(isConfirm) {
				if (isConfirm) {
					$.ajax({
						type : "get",
						url : basePath + "system/del_user.moo?uId="+row.uId ,
						success : function(data,XMLHttpRequest) {
							if (200 === data.status) {
								toastr.success(data.msg,'系统提示');
								$userTable.bootstrapTable('selectPage', 1);
							} else {
								toastr.warning(data.msg,'系统提示');
							}
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							if(XMLHttpRequest.status === 403){
								toastr.warning('你没有权限','权限不足');
							}else{
								toastr.error('系统发现未知错误 请联系管理员','系统错误');
							}
						}
					});
				}
			});
		},
		
		state_formatter : function(value,row,index){
			if(value == "1"){
				return "<span class='label label-primary'>启用中</span>";
			}else if(value == "2"){
				return "<span class='label label-danger'>已停用</span>";
			}else{
				return "<span class='label label-default'>未知</span>";
			}
		},
		operate_formatter : function(value,row,index){
			var html=[];
			html.push(sprintf("<button name='del-user' data-index='%s' type='button' class='btn btn-primary btn-xs btn-outline'><i class='fa fa-folder'></i> 删除</button>  ",index));
			//html.push(sprintf("<button name='copy-user' data-index='%s' type='button' class='btn btn-primary btn-xs btn-outline'><i class='fa fa-pencil'></i> 复制 </button>  ",index));
			html.push(sprintf("<button name='edit-user' data-index='%s' type='button' class='btn btn-primary btn-xs btn-outline'><i class='fa fa-pencil'></i> 编辑 </button>",index));
			return html.join("");
		},
		seachFlowParams : function (params) {
			var cdtions = formSerialize($("#form-user-seach"));
			cdtions.offset = params.offset;
			cdtions.limit = params.limit;
			cdtions.sort = params.sort;
			cdtions.order = params.order;
			return cdtions;
		}
	}
}();
$(function(){
	SystemUser.init();
});
