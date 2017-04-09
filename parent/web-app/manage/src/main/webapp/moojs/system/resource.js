var Resource = function() {
	'use strict';

	
	var bodyHeight = $("body").height();
	var $functionTable = $('#function-table').bootstrapTable({"height" : bodyHeight - 261- 58 -44 - 17});
	return {
		init : function() {
			var that=this;
			
			that.load_menuTree();
			//修改菜单信息
			$("#edit-menu-btn").on('click',function(){that.edit_menu();});
			$("#del-menu-btn").on('click',function(){that.del_menu();});
			
			//注册新资源
			$("#btn-reg").on('click',function(){that.reg_menu();})
			//注册新菜单表单校验
			$("#append-menu-form").validationEngine('attach', {
				onValidationComplete : function(form, status) {
					if(status){
						that.append_menu();
					}
				}
			});
			
		},
		load_menuTree : function(treeData){
			var that=this;
			that.echo_menu_root(treeData);
			
			if(treeData == undefined){
				$.ajax({
					type : "GET",
					url : basePath+"system/menu_tree.moo",
					success : function(data) {
						that.load_menuTree(data);
					}
				});
			}else{
				console.log(treeData);
				var $form=$("#edit-menu-form");
				$('#menu-tree-div').treeview({
			        color: "#888",
			        expandIcon: 'glyphicon glyphicon-chevron-right',
			        collapseIcon: 'glyphicon glyphicon-chevron-down',
			        nodeIcon: "glyphicon",
			        selectedBackColor: "#1ab394",
			        color: "#333",
			        data: treeData,
			        onNodeSelected: function (event, node) {
			        	$form.find("[name='resourceName']").val(node.text);
			        	$form.find("[name='resources']").val(node.url);
			        	$form.find("[name='resourceId']").val(node.resourcesId);
			        	$functionTable.bootstrapTable('refresh',{url : basePath + "system/functions.moo?resourceId="+node.resourcesId});
			        }
			    });
			}
		},
		echo_menu_root : function(treeData){
			if(treeData == undefined) return;
			var html=[];
			html.push("<option value='0'>设置为最顶级菜单</option>");
			$.each(treeData,function(j,item){
				html.push(sprintf("<option value='%s'>%s</option>",item.resourcesId,item.text));
			})
			$("#append-menu-form [name='pResourceId']").html(html.join(""));
		},
		append_menu : function(){
			var that = this;
			$.ajax({
				type : "POST",
				data : $("#append-menu-form").serialize(),
				url : basePath+"system/append_menu.moo",
				success : function(data) {
					if (200 === data.status) {
						toastr.success(data.msg,'系统提示');
						$('#append-menu-modal-div').modal('hide');
						that.load_menuTree();
					} else {
						toastr.warning(data.msg,'系统提示');
					}
				}
			});
		},
		edit_menu : function(){
			
		},
		del_menu : function(menuId){
			var that=this;
			var menuId = $("#edit-menu-form [name='resourceId']").val();
			swal({
				title : "系统确认",
				text : "您将删除选中的菜单 是否继续？",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "确认",
				closeOnConfirm : true
			}, function() {
				$.ajax({
					type : "GET",
					url : basePath+"system/del_menu.moo?menuId="+menuId,
					success : function(data) {
						if (200 === data.status) {
							toastr.success(data.msg,'系统提示');
							$('#append-menu-modal-div').modal('hide');
							that.load_menuTree();
						} else {
							toastr.warning(data.msg,'系统提示');
						}
					}
				});
			});
		},
		reg_menu : function(){
			$("#append-menu-form").submit();
		},
		state_formatter : function(value,row,index){
			return "<span class='label label-danger'>已停用</span>";
		}
	}
}();
