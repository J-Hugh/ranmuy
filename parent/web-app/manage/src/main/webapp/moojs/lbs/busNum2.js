var BusNum = function() {
	'use strict';

	var bodyHeight=$("body").height();
	var $numTable;
	//var buyer_city = {'GAODE':[{'':},{}]}
	return {
		init : function() {
			/*初始化*/
			var that=this;
			$numTable = $('#num-table').bootstrapTable({"height":bodyHeight-55-5,
				"onLoadSuccess":function(){$("#btn-search-num").button('reset');}});
			
			/*时间插件*/
			$('#data_1').datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			$('#data_5 .input-daterange').datepicker({
				todayBtn : "linked",
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true
            });
			/*搜索*/
			$("#btn-search-num").on('click',function(){
				 var $btn = $(this).button('loading');
				 $numTable.bootstrapTable('refresh',{query: {offset: 0,limit:15}});
			})
			$("#btn-chart").on('click',function(){
				var month = $('#select-month').val();
				var year = $('#select-year').val();
				var buyer=$('#select-buyer').val();
				$('#select-buyer-chart').val(buyer);
				var now = new Date();
				now.setMonth(month-1);
				now.setFullYear(year);
				$('#input-start').val(now.firstByMonth().format("yyyy-MM-dd"));
				$('#input-end').val(now.lastByMonth().format("yyyy-MM-dd"));
				
				//$('#data_5 .input-daterange').datepicker('setStartDate',now.firstByMonth());
				//$dateRange.datepicker({setStartDate : new Date()});
				
				that.loadingEchar();
				var $nextPage = $('#pt-main').children( 'div.pt-page' ).eq(1);
				var $currPage = $('#pt-main').children( 'div.pt-page' ).eq(0);
				switchPage($currPage,$nextPage);
			})
			$("#btn-back").on('click',function(){
				var $nextPage = $('#pt-main').children( 'div.pt-page' ).eq(0);
				var $currPage = $('#pt-main').children( 'div.pt-page' ).eq(1);
				switchPage($currPage,$nextPage);
			})
			$("#btn-search-chart").on('click',function(){
				that.loadingEchar();
			})
		},
		loadingEchar : function (){
			var myChart = echarts.init(document.getElementById('main'));
			var formdata =  $('#form-num-chart-seach').serialize();
			$.ajax({
				type : "get",
				url : basePath + "lbs/bus_num_chart.moo?"+formdata,
				success : function(data,XMLHttpRequest) {
					console.log(data);
					if (200 === data.status) {
						var start = $('#input-start').val();
						var end = $('#input-end').val();
						var option = {
							    backgroundColor: "#344b58",
							    "title": {
							    	"text": "【"+start+"】至【"+end+"】车辆数统计",
							        x: "5%",
							        y: 5,
							        textStyle: {
							            color: '#fff',
							            fontSize: '22'
							        },
							        subtextStyle: {
							            color: '#90979c',
							            fontSize: '16',
							        },
							    },
							    "tooltip": {
							        "trigger": "axis",
							        "axisPointer": {
							            "type": "shadow",
							            textStyle: {
							                color: "#fff"
							            }
							        },
							    },
							    "grid": {
							        "borderWidth": 0,
							        "top": 90,
							        "bottom": 55,
							        textStyle: {
							            color: "#fff"
							        }
							    },
							    "legend": {
							        x: '6%',
							        top: '9%',
							        textStyle: {
							            color: '#90979c',
							        },
							        "data": data.dataSet.legendData
							    },
							    "calculable": true,
							    "xAxis": [{
							        "type": "category",
							        "axisLine": {
							            lineStyle: {
							                color: '#90979c'
							            }
							        },
							        "splitLine": {
							            "show": false
							        },
							        "axisTick": {
							            "show": false
							        },
							        "splitArea": {
							            "show": false
							        },
							        "axisLabel": {
							            "interval": 0,
							            "rotate": 25
							        },
							        "data": data.dataSet.xData,
							    }],
							    "yAxis": [{
							        "type": "value",
							        "splitLine": {
							            "show": false
							        },
							        "axisLine": {
							            lineStyle: {
							                color: '#90979c'
							            }
							        },
							        "axisTick": {
							            "show": false
							        },
							        "axisLabel": {
							            "interval": 0,
							        },
							        "splitArea": {
							            "show": false
							        },
							    }],
							   
							    "series": data.dataSet.series
							}
							myChart.setOption(option);
					}
				}
			});
			
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
	BusNum.init();
});
