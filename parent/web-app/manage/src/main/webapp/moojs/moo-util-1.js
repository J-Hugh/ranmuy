
//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.

function dateTime_formatter(value,row,index){
	var onshelfTimeJs = new Date(value);
	return onshelfTimeJs.format("yyyy年MM月dd日 hh时mm分");
}
function toFixed_formatter(value,row,index){
	return value.toFixed(2);
}

function str2Obj(str) {
	str = str.replace(/&/g, "','");
	str = str.replace(/=/g, "':'");
	str = "({'" + str + "'})";
	obj = eval(str);
	return obj;
}

//将表单元素转换为json对象
function formSerialize($from){
	var obj = str2Obj($from.serialize());
	var newObj = {};
	for(var k in obj){
		//.serialize()自动调用了encodeURIComponent方法将数据编码了
		// 所以要对数据进行解码，否则有可能乱码
		var val = decodeURIComponent(obj[k],true);
		var key = decodeURIComponent(k);
		newObj[key] = val;
	}
	return newObj;
}

Date.prototype.format = function(fmt){ 
//author: neo
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };

  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o){
	  if(new RegExp("("+ k +")").test(fmt)){
		  
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	  }
  }
 return fmt;   
}
//开始时间
Date.prototype.first = function(){ 
	this.setHours(0,0,0,0);
	return this;
}
//最后的时间
Date.prototype.last = function(){ 
	this.setHours(23,59,59,999);
	return this;
}
//获取星期
Date.prototype.week = function(){ 
	var weekArray = new Array("日", "一", "二", "三", "四", "五", "六");
	var week = weekArray[this.getDay()];
	return week;
}
//本月第一天
Date.prototype.firstByMonth = function(){
	this.setDate(1); 
	return this;
}
//本月最后一天
Date.prototype.lastByMonth = function(){
	this.setDate(1); 
	this.setMonth(this.getMonth()+1);
	this.setDate(0);
	return this;
}

/*重置表单*/
var reset = function(form){
	form[0].reset();
	form.find('input').removeClass('failure');
	form.find('select').removeClass('failure');
	form.validationEngine('hideAll');
}

var sprintf = function (str) {
	var args = arguments,
		flag = true,
		i = 1;

	str = str.replace(/%s/g, function () {
		var arg = args[i++];

		if (typeof arg === 'undefined') {
			flag = false;
			return '';
		}
		return arg;
	});
	return flag ? str : '';
};

//随机生成字符串
$.extend({ 
	  password: function (length, special) {
	    var iteration = 0;
	    var password = "";
	    var randomNumber;
	    if(special == undefined){
	        var special = false;
	    }
	    while(iteration < length){
	        randomNumber = (Math.floor((Math.random() * 100)) % 94) + 33;
	        if(!special){
	            if ((randomNumber >=33) && (randomNumber <=47)) { continue; }
	            if ((randomNumber >=58) && (randomNumber <=64)) { continue; }
	            if ((randomNumber >=91) && (randomNumber <=96)) { continue; }
	            if ((randomNumber >=123) && (randomNumber <=126)) { continue; }
	        }
	        iteration++;
	        password += String.fromCharCode(randomNumber);
	    }
	    return password;
	  }
	});

function switchPage($outpage,$inpage){
	$outpage.addClass('pt-page-current');
	var isAnimating=false,
		endCurrPage = false,
		endNextPage = false,
		animEndEventNames = {
			'WebkitAnimation' : 'webkitAnimationEnd',
			'OAnimation' : 'oAnimationEnd',
			'msAnimation' : 'MSAnimationEnd',
			'animation' : 'animationend'
		},
		animEndEventName = animEndEventNames[ Modernizr.prefixed( 'animation' ) ]
	$outpage.addClass( 'pt-page-rotateCarouselLeftOut pt-page-ontop' ).on( animEndEventName, function() {
		$outpage.off( animEndEventName );
		endCurrPage = true;
		if( endNextPage ) {
			onEndAnimation( $outpage, $inpage );
		}
		endNextPage = true;
		if( endCurrPage ) {
			onEndAnimation( $outpage, $inpage );
		}
	} );
	$inpage.addClass( 'pt-page-rotateCarouselLeftIn' ).on( animEndEventName, function() {
		$inpage.off( animEndEventName );
		endNextPage = true;
		if( endCurrPage ) {
			onEndAnimation( $outpage, $inpage );
		}
	} );
	
	function onEndAnimation( $outpage, $inpage ) {
		endCurrPage = false;
		endNextPage = false;
		resetPage( $outpage, $inpage );
		isAnimating = false;
	}

	function resetPage( $outpage, $inpage ) {
		$outpage.attr( 'class', 'pt-page wrapper wrapper-content' );
		$inpage.attr( 'class',  'pt-page wrapper wrapper-content pt-page-current' );
	}
}

// Collapse ibox function
$('.collapse-link').click( function() {
    var ibox = $(this).closest('div.ibox');
    var button = $(this).find('i');
    var content = ibox.find('div.ibox-content');
    content.slideToggle(200);
    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
    ibox.toggleClass('').toggleClass('border-bottom');
    setTimeout(function () {
        ibox.resize();
        ibox.find('[id^=map-]').resize();
    }, 50);
});