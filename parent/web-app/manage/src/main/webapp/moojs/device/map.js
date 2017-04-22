var Map = function() {
	'use strict';

	var bodyHeight=$("body").height();
	var map,busline;
	var markers=[];
	return {
		init : function() {
			/*初始化*/
			var that=this;
			/*时间插件*/
			$('#data_1').datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			map = new BMap.Map("container", {minZoom:12,maxZoom:12,enableMapClick:false});
			map.setMapStyle({style:'grayscale'});
			map.centerAndZoom(new BMap.Point(116.404, 39.915), 12); 
			map.enableScrollWheelZoom(); 
			
			busline = new BMap.BusLineSearch(map,{
				renderOptions:{map:map},
				onGetBusListComplete: function(result){
				   if(result) {
					  var fstLine = result.getBusListItem(0);//获取第一个公交列表显示到map上
					  busline.getBusLine(fstLine);
				   }
				}
			});
			busline.setGetBusLineCompleteCallback(function(){
				setTimeout(function(){
					that.painting_markers();
				},500);
			})
			
			/*搜索*/
			$("#btn-search-marker").on('click',function(){
				 var $btn = $(this).button('loading');
				 that.search_markers();
				 that.search_vagueLine();
				 that.cr_c();
			})
			$("#btn-loading-line").on('click',function(){
				that.addMarker();
				/*var lineNmae = $('input[name="lineName"]').val();
				console.log(lineNmae);
				that.load_line(lineNmae);*/
			})
			
		},
		search_markers : function(){
			var that =this;
			$.ajax({
				type : "GET",
				data : $("#form-marker-seach").serialize(),
				url : basePath + "lbs/getMarkers.moo" ,
				success : function(data,XMLHttpRequest) {
					$("#btn-search-marker").button('reset');
					if (200 === data.status) {
						markers=data.dataSet;
						that.painting_markers();
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
		search_vagueLine : function(){
			$.ajax({
				type : "GET",
				data : $("#form-marker-seach").serialize(),
				url : basePath + "lbs/getVagueLine.moo" ,
				success : function(data,XMLHttpRequest) {
					//$("#btn-search-marker").button('reset');
					if (200 === data.status) {
						var html=[];
						var $tbody = $("#control").find("tbody");
						$tbody.empty();
						
						$.each(data.dataSet,function(j,item){
							html.push('<tr> \
											<td class="project-title">\
												<a >'+item.lineName+'</a>\
											</td> \
		                               </tr>');
						})
						$tbody.html(html.join(""));
						console.log(data);
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
		load_line : function(lineName){
			busline.getBusList(lineName);
		},
        addMarker : function (lng,lat) {
            var bounds = map.getBounds();
            var sw = bounds.getSouthWest();
            var ne = bounds.getNorthEast();
			var point = new BMap.Point(sw.lng + 0.041 * (Math.random() * 0.7), ne.lat - 0.033 * (Math.random() * 0.7));
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);
        },
		painting_markers : function(){
			if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
				var points = [];  // 添加海量点数据
				$.each(markers,function(j,item){
					points.push(new BMap.Point(item.loc[1], item.loc[0]));
				})
				var options = {
					size: BMAP_POINT_SIZE_SMALL,
					shape: BMAP_POINT_SHAPE_STAR,
					color: '#000000'
				}
				var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
				map.addOverlay(pointCollection);  // 添加Overlay
			} else {
				alert('请在chrome、safari、IE8+以上浏览器查看本示例');
			}
		},
		cr_c : function(){
			var that = this;
			// 定义一个控件类,即function
			function ZoomControl(){
			  // 默认停靠位置和偏移量
			  this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
			  this.defaultOffset = new BMap.Size(0, 10);
			}

			// 通过JavaScript的prototype属性继承于BMap.Control
			ZoomControl.prototype = new BMap.Control();

			// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
			// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
			ZoomControl.prototype.initialize = function(map){
			  // 创建一个DOM元素
			  var div = $("#control")[0];
			  // 绑定事件,点击一次放大两级
			  div.style.cursor = "pointer";
			  $("#control").find('a').on('click',function(){
				 that.load_line($(this).data("txt"));
			  });
			  
			 /* div.onclick = function(e){
				map.setZoom(map.getZoom() + 2);
			  }*/
			  // 添加DOM元素到地图中
			  map.getContainer().appendChild(div);
			  // 将DOM元素返回
			  return div;
			}
			// 创建控件
			var myZoomCtrl = new ZoomControl();
			// 添加到地图当中
			map.addControl(myZoomCtrl);
		}
	}
}();
$(function(){
	Map.init();
});
