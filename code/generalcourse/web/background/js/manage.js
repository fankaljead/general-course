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

// 页面dom完成加载后执行
$(document).ready(function () {
   $.ajax({
       url: "/getRoleByAccount",
       method: "post",
       async: false, // 同步
       success: function (data) {
           data = JSON.parse(data);
           console.log("data:", data);
           document.getElementsByClassName("role")[0].innerHTML = "欢迎您，" + data.roleName;
       }
   })

    $.ajax({
        url: "/getModule",
        method: "post",
        async: false,
        success: function (data) {
            data = JSON.parse(data);
            console.table("module:", data)

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement("div");
                var p = document.createElement("p");
                var ul = document.createElement("ul");
                ul.setAttribute('class', 'nav1');

                p.innerText = data[i].moduleName;


                for (var j = 0; j < data[i].subModules.length; j++) {
                    var li = document.createElement("li");
                    li.innerText = data[i].subModules[j].subModuleName;

                    ul.appendChild(li);
                }

                var module_div = document.getElementById('module_div');
                div.appendChild(p);
                div.appendChild(ul);

                p.addEventListener('click', function (ev) {

                    $(this).parent().siblings().children("ul").css({"display":"none"});
                    $(this).siblings().css({"display":"block"});
                })
                module_div.appendChild(div);
            }
        }
    })
});
