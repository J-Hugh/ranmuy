var myChart = echarts.init(document.getElementById('main'));
		var colors = ['#5793f3', '#d14a61', '#675bba'];
		
		$.ajax({
			type : "get",
			url : basePath + "supervisor/changsha1.moo" ,
			success : function(data,XMLHttpRequest) {
				if (200 === data.status) {
					option = {
						    color: colors,

						    tooltip: {
						        trigger: 'axis'
						    },
						    grid: {
						        right: '10%',
						        left: '5%'
						    },
						    toolbox: {
						        feature: {
						            saveAsImage: {show: true}
						        }
						    },
						    legend: {
						        data:['在线率','上线数','日志数']
						    },
						    xAxis: [
						        {
						            type: 'category',
						            axisTick: {
						                alignWithLabel: true
						            },
						            data: data.dataSet.date
						        }
						    ],
						    yAxis: [
						        {
						            type: 'value',
						            name: '在线率',
						            min: 0,
						            max: 1,
						            position: 'right',
						            axisLine: {
						                lineStyle: {
						                    color: colors[0]
						                }
						            },
						            axisLabel: {
						                formatter: '{value} '
						            }
						        },
						        {
						            type: 'value',
						            name: '上线数',
						            min: 0,
						            max: 100000,
						            position: 'right',
						            offset: 80,
						            axisLine: {
						                lineStyle: {
						                    color: colors[1]
						                }
						            },
						            axisLabel: {
						                formatter: '{value}'
						            }
						        },
						        {
						            type: 'value',
						            name: '日志数 （万）',
						            min: 0,
						            max: 100000,
						            position: 'left',
						            axisLine: {
						                lineStyle: {
						                    color: colors[2]
						                }
						            },
						            axisLabel: {
						                formatter: '{value}'
						            }
						        }
						    ],
						    series: [
						        {
						            name:'在线率',
						            type:'bar',
						            data: data.dataSet.onlineRate
						        },
						        {
						            name:'上线数',
						            type:'bar',
						            yAxisIndex: 1,
						            data: data.dataSet.authNum
						        },
						        {
						            name:'日志数',
						            type:'line',
						            yAxisIndex: 2,
						            data:data.dataSet.netlogNum
						        }
						    ]
						};

				        // 使用刚指定的配置项和数据显示图表。
				        myChart.setOption(option); 
					
					
					
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
		