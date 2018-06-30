//动态显示时间
$(document).ready(function () {
    function refreshTime() {
        var time = new Date().toLocaleString(); //当前时间
        $(".time").css({"background-color":"red"});
        $(".time").append(time);
    }

    //setInterval("refreshTime()",100);
})