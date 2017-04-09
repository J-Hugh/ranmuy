var myChart = echarts.init(document.getElementById('main'));

$.ajax({
	type : "get",
	url : basePath + "lbs/lbsChartdate.moo?par[buyers]=GAODE",
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