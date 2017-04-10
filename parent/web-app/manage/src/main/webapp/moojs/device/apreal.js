var ApReal = function() {
	'use strict';

	var bodyHeight=$("body").height();
	var $numTable;
	//var buyer_city = {'GAODE':[{'':},{}]}
	return {
		init : function() {
			/*初始化*/
			var that=this;
			$numTable = $('#num-table').bootstrapTable({"height":bodyHeight-55-8,"onLoadSuccess":function(){
					$("#btn-search-num").button('reset');
					$("[data-toggle=popover]").popover();
					$('[name="details-ap"]').on('click', function() {
						that.ap_details($numTable.bootstrapTable('getData')[$(this).data('index')]);
					});
				}
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
			$("#btn-search-num").on('click',function(){
				 var $btn = $(this).button('loading');
				 $numTable.bootstrapTable('refresh',{query: {offset: 0,limit:15}});
			})
			$("#btn-back").on('click',function(){
				var $nextPage = $('#pt-main').children( 'div.pt-page' ).eq(0);
				var $currPage = $('#pt-main').children( 'div.pt-page' ).eq(1);
				switchPage($currPage,$nextPage);
			})
		},
		ap_details : function(row){
			//this.load_radar_echart();
			$('#form-num-chart-seach [name="par[apmac]"]').val(row.apMac);
			var $nextPage = $('#pt-main').children( 'div.pt-page' ).eq(1);
			var $currPage = $('#pt-main').children( 'div.pt-page' ).eq(0);
			switchPage($currPage,$nextPage);
			this.load_online_echart(row.apMac);
			this.load_index_echart(row.apMac);
		},
		load_online_echart : function(apmac){
			$.ajax({
				type : "get",
				url : basePath + "device_monitor/recently7DayOnline.moo?par[apmac]="+apmac,
				success : function(sData, XMLHttpRequest) {
					var hours = ['01', '02', '03', '04', '05', '06', '07','08', '09', '10','11','12','13', '14', '15', '16', '17', '18','19', '20', '21', '22', '23', '24'];
					var days = sData.xAxis;
					
					var data = sData.pane;
					data = data.map(function (item) {
					    return [item[0], item[1], item[2] || '-'];
					});
					console.log(data);
					var option = {
						title : {
							text:'近七日在线情况',
							x: '5%',
							textStyle: {
					            fontSize: 8
					        },
					        onclick: function(){
					        	alert();
					        }
						},
					    tooltip: {
					        position: 'top'
					    },
					    animation: true,
					    grid: {
					        height: '75%',
					        y: '12%',
					        x: '5%',
					        right : '1%'
					    },
					    xAxis: {
					        type: 'category',
					        data: hours,
					        splitArea: {
					            show: true
					        }
					    },
					    yAxis: {
					        type: 'category',
					        data: days,
					        splitArea: {
					            show: true
					        }
					    },
					    visualMap: {
					        min: 0,
					        max: 30,
					        calculable: false,
					        show: false,
					        color: ['#196127','#7bc96f']
					    },
					    series: [{
					        name: 'Punch Card',
					        type: 'heatmap',
					        data: data,
					        label: {
					            normal: {
					                show: true
					            }
					        },
					        itemStyle: {
					            emphasis: {
					                shadowBlur: 10,
					                shadowColor: 'rgba(0, 0, 0, 0.5)'
					            }
					        }
					    }]
					};
					var myChart = echarts.init(document.getElementById('day-echart'));
					myChart.setOption(option);
				}
			});
		},
		load_index_echart : function(apmac){
			
			$.ajax({
				type : "get",
				url : basePath + "device_monitor/recently20Day.moo?par[apmac]="+apmac,
				success : function(data, XMLHttpRequest) {
					var option = {
						    baseOption: {
						    	toolbox: {
						            feature: {
						                dataView: {}
						            }
						        },
						    	title : {
									text:'近20日数据上报情况',
									x: '5%',
									textStyle: {
							            fontSize: 8
							        },
							        onclick: function(){
							        	alert();
							        }
								},
						        tooltip: {
						        },
						        legend: {
						            x: 'center',
						            data: ['GPS', '联网', '心跳']
						        },
						        calculable : true,
						        grid: {
						        	height: '75%',
							        y: '12%',
							        x: '5%',
							        right : '1%',
						            tooltip: {
						                trigger: 'axis',
						                axisPointer: {
						                    type: 'shadow',
						                    label: {
						                        show: true,
						                        formatter: function (params) {
						                            return params.value.replace('\n', '');
						                        }
						                    }
						                }
						            }
						        },
						        xAxis: [
						            {
						                'type':'category',
						                'axisLabel':{'interval':0},
						                'data':data.xAxis,
						                splitLine: {show: false}
						            }
						        ],
						        yAxis: [
						            {
						                type: 'value'
						            }
						        ],
						        series: [
						            {name: 'GPS', type: 'bar',data: data.a},
						            {name: '联网', type: 'bar',data: data.b},
						            {name: '心跳', type: 'bar',data: data.c}
						        ]
						    }
						};
						var myChart = echarts.init(document.getElementById('line-echart'));
						myChart.setOption(option);
				}
			});
		},
		load_radar_echart : function(){
			var option = {
				    tooltip: {},
				    grid: {
			        	height: '55%',
			        	
				        y: '12%',
				        x: '5%',
				        right : '1%'
				    },
				    radar: {
				        // shape: 'circle',
				        indicator: [
				           { name: '销售（sales）', max: 6500},
				           { name: '管理（Administration）', max: 16000},
				           { name: '信息技术（Information Techology）', max: 30000},
				           { name: '客服（Customer Support）', max: 38000},
				           { name: '研发（Development）', max: 52000},
				           { name: '市场（Marketing）', max: 25000}
				        ]
				    },
				    series: [{
				        name: '预算 vs 开销（Budget vs spending）',
				        type: 'radar',
				        data : [
				            {
				                value : [4300, 10000, 28000, 35000, 50000, 19000],
				                name : '预算分配（Allocated Budget）'
				            },
				             {
				                value : [5000, 14000, 28000, 31000, 42000, 21000],
				                name : '实际开销（Actual Spending）'
				            }
				        ]
				    }]
				};
			var myChart = echarts.init(document.getElementById('radar-echart'));
			myChart.setOption(option);
		},
		operate_formatter : function(value,row,index){
			var html=[];
			html.push(sprintf("<a name='details-ap' data-index='%s' >详情</a>",index));
			return html.join("");
		},
		apmac_formatter : function(value,row,index){
			var html=[];
			if(row.error < 1){
				html.push(sprintf("<i class='fa fa-dot-circle-o' data-index='%s' style='font-size:15;color: #1ab394;'></i> %s",index,value));
			}else{
				html.push(sprintf("<i class='fa fa-exclamation-triangle' data-index='%s' data-container='body' data-toggle='popover' data-trigger='hover' data-placement='right' data-content='这里是提示内容' style='font-size:15;color: red;'></i> %s",index,value));
			}
			return html.join("");
		},
		seachFlowParams : function (params) {
			var cdtions = formSerialize($("#form-num-seach"));
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
	ApReal.init();
});
