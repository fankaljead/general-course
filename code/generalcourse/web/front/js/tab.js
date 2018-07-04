

window.onload = function ()
{
func1();
func2();
func3();
func01();
func02();
func03();
func04();
}

function func1()
{
var oLi = document.getElementById("tab").getElementsByTagName("p");
	var oUl = document.getElementById("content").getElementsByTagName("ul");
	
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "current";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		}	
	}
}
function func2()
{
var oLis = document.getElementById("tabs").getElementsByTagName("p");
	var oUls = document.getElementById("contents").getElementsByTagName("ul");
	
	for(var ii = 0; ii < oLis.length; ii++)
	{
		oLis[ii].index = ii;
		oLis[ii].onmouseover = function ()
		{
			for(var nn = 0; nn < oLis.length; nn++) oLis[nn].className="";
			this.className = "currents";
			for(var nn = 0; nn < oUls.length; nn++) oUls[nn].style.display = "none";
			oUls[this.index].style.display = "block"
		}	
	}
}	
	function func3(){
		
		/*导航当前页高亮*/
var obj=null;
var As=document.getElementById('topnav').getElementsByTagName('a');
obj = As[0];
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
obj=As[i];}
obj.id='topnav_current'

		}
	
	
function func01()
{
var speed=30//速度数值越大速度越慢
var colee_left2=document.getElementById("colee_left2");
var colee_left1=document.getElementById("colee_left1");
var colee_left=document.getElementById("colee_left");
colee_left2.innerHTML=colee_left1.innerHTML
function Marquee3(){
if(colee_left2.offsetWidth-colee_left.scrollLeft<=0)//offsetWidth 是对象的可见宽度
colee_left.scrollLeft-=colee_left1.offsetWidth//scrollWidth 是对象的实际内容的宽，不包边线宽度
else{
colee_left.scrollLeft++
}
}
var MyMar3=setInterval(Marquee3,speed)
colee_left.onmouseover=function() {clearInterval(MyMar3)}
colee_left.onmouseout=function() {MyMar3=setInterval(Marquee3,speed)}
}
function func02()
{
	
	var speed=30//速度数值越大速度越慢
var demo2=document.getElementById("demo2");
var demo1=document.getElementById("demo1");
var demo=document.getElementById("demo");
demo2.innerHTML=demo1.innerHTML
function Marquee3(){
if(demo2.offsetWidth-demo.scrollLeft<=0)//offsetWidth 是对象的可见宽度
demo.scrollLeft-=demo1.offsetWidth//scrollWidth 是对象的实际内容的宽，不包边线宽度
else{
demo.scrollLeft++
}
}
var MyMar3=setInterval(Marquee3,speed)
demo.onmouseover=function() {clearInterval(MyMar3)}
demo.onmouseout=function() {MyMar3=setInterval(Marquee3,speed)}
	
}
function func03()
{
		var speed=30//速度数值越大速度越慢
var zhanshi2=document.getElementById("zhanshi2");
var zhanshi1=document.getElementById("zhanshi1");
var zhanshi=document.getElementById("zhanshi");
zhanshi2.innerHTML=zhanshi1.innerHTML
function Marquee3(){
if(zhanshi2.offsetWidth-zhanshi.scrollLeft<=0)//offsetWidth 是对象的可见宽度
zhanshi.scrollLeft-=zhanshi1.offsetWidth//scrollWidth 是对象的实际内容的宽，不包边线宽度
else{
zhanshi.scrollLeft++
}
}
var MyMar3=setInterval(Marquee3,speed)
zhanshi.onmouseover=function() {clearInterval(MyMar3)}
zhanshi.onmouseout=function() {MyMar3=setInterval(Marquee3,speed)}
}
function func04()
{
var speed=30//速度数值越大速度越慢
var rongyu2=document.getElementById("rongyu2");
var rongyu1=document.getElementById("rongyu1");
var rongyu=document.getElementById("rongyu");
rongyu2.innerHTML=rongyu1.innerHTML
function Marquee3(){
if(rongyu2.offsetWidth-rongyu.scrollLeft<=0)//offsetWidth 是对象的可见宽度
rongyu.scrollLeft-=rongyu1.offsetWidth//scrollWidth 是对象的实际内容的宽，不包边线宽度
else{
rongyu.scrollLeft++
}
}
var MyMar3=setInterval(Marquee3,speed)
rongyu.onmouseover=function() {clearInterval(MyMar3)}
rongyu.onmouseout=function() {MyMar3=setInterval(Marquee3,speed)}	
}	
	
	




	



