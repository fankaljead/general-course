//动态显示时间
function refreshTime() {
    var time = new Date().toLocaleString(); //当前时间
    $(".time").css({"background-color":"red"});
    $(".time").empty();
    $(".time").append(time);
}


$(function () {
    setInterval("refreshTime()",100);
})

//点击侧边栏菜单显示子菜单
$(function showMenu() {
    $(".nav-nav p").click(function () {
        $(this).parent().siblings().children("ul").css({"display":"none"});
        $(this).siblings().css({"display":"block"});
    })
})
