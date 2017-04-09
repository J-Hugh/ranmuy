var SystemRole = function() {
	'use strict';
	var bodyHeight = $("body").height();
	// console.log(bodyHeight);
	var $role_table = $('#role-table').bootstrapTable({"height" : bodyHeight - 58 - 17});
	//当前选中的角色
	var briskRole;
	return {
		init : function() {
			var that = this;
			$(".full-height-scroll").slimScroll({height : bodyHeight - 200,color : '#EFF'});
			
			//加载资源树
			that.loading_tree();
			
			$('#append-role-modal-div').on('hide.bs.modal', function () {
				reset($("#form-append-role"));
			})
			/*表单重置*/
			$('#btn-reset').on('click',function(){
				reset($("#form-append-role"));
			})
			
			$role_table.bootstrapTable().on('load-success.bs.table',function() {
				$('[name="edit-role"]').on('click', function() {
					briskRole = $role_table.bootstrapTable('getData')[$(this).data('index')];
					that.edit_role_modal()
				});
				$('[name="edit-power"]').on('click', function() {
					briskRole = $role_table.bootstrapTable('getData')[$(this).data('index')];
					that.edit_power_modal()
				});
			});
			
			/*提交修改权限请求*/
			$("#edit-role-resource-btn").on("click", function(){that.edit_role_resource();});
			
			/*生成随机码*/
			$("#btn-random").on('click',function(){
				$("input[name='uPassword']").val($.password(8));
				$("input[name='uPassword']").blur();
			})
			
			/*表达校验*/
			$("#form-append-role").validationEngine('attach', {
				onValidationComplete : function(form, status) {
				that.append_role(status);
			}});
		},
		loading_tree : function() {
			$('#resource-tree-div').jstree({
				"plugins" : [ "checkbox" ],
				'core' : {
					'data' : {
						"url" : basePath + "system/resource_tree.moo",
						"dataType" : "json"
					},
					"check_callback" : true
				}
			});
		},
		renderer_tree : function(resources){
			$("#resource-tree-div").jstree("uncheck_all");
			$.each(resources,function(j,item){
				$("#resource-tree-div").jstree("check_node", "#"+item);
			});
			$("#edit-power-modal-div").modal("show");
		},
		edit_role_modal : function() {
			// console.log($(el).data('index'));
			// toastr.success($(el).data('index'));
			$("#edit-role-modal-div").modal("show");
		},
		edit_power_modal : function() {
			var that = this;
			$.ajax({
				type : "GET",
				url : basePath + "system/have_resource.moo?roleId="+briskRole.roleId ,
				success : function(data,XMLHttpRequest) {
					//渲染资源树
					that.renderer_tree(data);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					if(XMLHttpRequest.status === 403){
						toastr.warning('你没有权限','权限不足');
					}else{
						toastr.error('系统发现未知错误 请联系管理员','系统错误');
					}
				}
			});
		},
		edit_role_resource : function(){
			var nodes = $("#resource-tree-div").jstree("get_checked", true),ids=[];
			$.each(nodes,function(j,item){
				ids.push(item.id);
			});
			var csrf=$("#edit-power-modal-div [name='_csrf']").val();
			$.ajax({
				url : basePath + "system/edit_roles_resource.moo",
				type : "post",
				data : {'resourcesIds' : ids.join("-"), 'roleId' : briskRole.roleId,'_csrf':csrf},
				dataType : "json",
				success : function(data) {
					if (200 === data.status) {
						toastr.success(data.msg,'系统提示');
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
			
		},
		append_role : function(status){
			var that = this;
			if(status){
				$.ajax({
					type : "POST",
					data : $("#form-append-role").serialize(),
					url : basePath + "system/append_role.moo" ,
					success : function(data,XMLHttpRequest) {
						console.log(data.dataSet);
						if (200 === data.status) {
							toastr.success(data.msg,'系统提示');
							$('#append-role-modal-div').modal('hide');
							$role_table.bootstrapTable('selectPage', 1);
							briskRole = data.dataSet;
							that.edit_power_modal()
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
		operate_formatter : function(value, row, index) {
			var html = [];
			
			html.push(sprintf("<button data-index='%s' name='edit-power' type='button' class='btn btn-primary btn-xs btn-outline'><i class='fa fa-folder'></i> 权限</button>  ",index));
			//html.push(sprintf("<button data-index='%s' name='edit-role' type='button' class='btn btn-primary btn-xs btn-outline'><i class='fa fa-pencil'></i> 编辑 </button>",index));
			
			return html.join("");
		}
	}
}();

$(function() {
	SystemRole.init();
});
