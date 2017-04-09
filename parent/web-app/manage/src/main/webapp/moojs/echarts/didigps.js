
var globaldate = '';

$(function(){
	$('#loading-example-btn').on('click',function(){
		 var $btn = $(this).button('loading');
		 var mac = $('#apMac').val();
		 var date = $('#data_1').val();
		 if(mac != ''){
			 macEchar(mac,date);
		 }else{
			 allMac();
		 }
	});
	
	$('#mac-tbody').on('click','tr',function(){
		macEchar($(this).find("td:first").text(),globaldate);
	});
	
})

function macEchar(mac,date){
	
	var myChart = echarts.init(document.getElementById('mac-echart'));
	$.ajax({
		type : "get",
		url : basePath + "lbs/macChartdate.moo?par[buyers]=DIDI&par[apmac]="+mac +"&par[date]="+date,
		success : function(data,XMLHttpRequest) {
			if (200 === data.status) {
				option = {
					title : {
						subtext : ''
					},
					grid : {
						bottom : 80
					},
					legend : {
						data:data.dataSet.citys
					},
					tooltip : {
						trigger : 'axis',
						axisPointer : {
							animation : false
						}
					},
					toolbox : {
						feature : {
							dataZoom : {
								yAxisIndex : 'none'
							},
							restore : {},
							saveAsImage : {}
						}
					},
					xAxis : [ {
						type : 'category',
						boundaryGap : false,
						axisLine : {
							onZero : false
						},
						data : data.dataSet.xAxis.map(function(str) {
							return str.replace(' ', '\n')
						})
					} ],
					yAxis : [ {
						name : '数据量',
						type : 'value'
					} ],
					series: data.dataSet.series
				};

				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			} else {
				toastr.warning(data.msg, '系统提示');
			}
			$("#loading-example-btn").button('reset');
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

function allMac(){
	var date = $('#data_1').val();
	var mac = $('#apMac').val();
	var tbody = $('#mac-tbody');
	tbody.empty();
	$.ajax({
		type : "get",
		url : basePath + "lbs/getallmac.moo?par[buyers]=DIDI&par[date]="+date,
		success : function(data,XMLHttpRequest) {
			var macs = data.dataSet;
			for(var key in macs){
				//console.log(key +":" + macs[key]);
				tbody.append('<tr>\
						<td>'+key+'</td>\
						<td class="client-status"><span\
							class="label label-danger">'+macs[key]+'</span></td>\
						</tr>')
				
			}
			$("#loading-example-btn").button('reset');
			globaldate = date;
		}
	});
}

function loading(){
	var myChart = echarts.init(document.getElementById('main'));
	$.ajax({
		type : "get",
		url : basePath + "lbs/lbsChartdate.moo?par[buyers]=DIDI",
		success : function(data,XMLHttpRequest) {
			if (200 === data.status) {
				option = {
					title : {
						subtext : ''
					},
					grid : {
						bottom : 80
					},
					legend : {
						data:data.dataSet.citys
					},
					toolbox : {
						feature : {
							dataZoom : {
								yAxisIndex : 'none'
							},
							restore : {},
							saveAsImage : {}
						}
					},
					tooltip : {
						trigger : 'axis',
						axisPointer : {
							animation : false
						}
					},
					dataZoom : [ {
						show : true,
						realtime : true,
						start : 0,
						end : 100
					}, {
						type : 'inside',
						realtime : true,
						start : 0,
						end : 100
					} ],
					xAxis : [ {
						type : 'category',
						boundaryGap : false,
						axisLine : {
							onZero : false
						},
						data : data.dataSet.xAxis.map(function(str) {
							return str.replace(' ', '\n')
						})
					} ],
					yAxis : [ {
						name : '数据量',
						type : 'value'
					} ],
					series: data.dataSet.series
				};

				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			} else {
				toastr.warning(data.msg, '系统提示');
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

