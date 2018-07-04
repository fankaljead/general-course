
$(document).ready(function () {
    $.ajax({
        url: "/getColumns",
        method: "post",
        async: false, // 同步

        success:function (data) {
            data = JSON.parse(data);
            console.table("module:", data);

            var topnav = document.getElementById('topnav');
            for (var i = 0; i < data.length; i++) {
                var a = document.createElement("span");

                // a.setAttribute('href', "" + data[i].columnName + ".html");
                a.setAttribute("onclick","turnPage("+ data[i].columnId +")");
                a.innerText = data[i].columnName;
                topnav.appendChild(a);
            }
        }
    })
})

function turnPage(columnId){
    $.ajax({
        url: "/getColumns?level=1&columnId=" + columnId,
        method: "post",
        async: false, // 同步

        success:function (data) {
            data = JSON.parse(data);
            console.log("columnId" + columnId);
            console.log(data);
            var conLeft = document.getElementById('con_left_list');
            var consule= document.getElementById("");
            con_left_list.innerHTML = "";
            for (var i = 0; i < data.length; i++) {
                var li = document.createElement("li");
                var a1 = document.createElement("a");
                var content = document.createElement("span");

                // a.setAttribute('href', "" + data[i].columnName + ".html");
                content.setAttribute("onclick","turnResources("+ data[i].columnId +")");
                content.innerText = data[i].columnName;
                conLeft.appendChild(li);
                li.appendChild(a1);
                a1.appendChild(content);

            }
            // for (var i = 0; i < data.length; i++) {
            //     content += "<li><a><spvt("+ data[i].columnId +")\">"+ data[i].columnName +"</span></a></li>";
            // }
            // console.log(content);
            // conLeft.innerHTML = content;
        }
    })




    $.ajax({
        url:"/getResources?columnId="+columnId,
        method:"post",
        async:false,

        success:function (data) {
            data = JSON.parse(data);
            console.log("columnId" + columnId);
            console.log(data);
            var conBox = document.getElementById('news_list');
            conBox.innerHTML = "";
            var content = "";
            for (var i = 0; i < data.length; i++) {

                content += " <li><span>"+data[i].createTime+"</span><a onclick='resourceClick('"+data[i].resourceId+")' href=\"#\">"+data[i].title+"</a></li>";
            }
            console.log("dsfasdf: ",content);
            conBox.innerHTML = content;

        }

    })




}

function turnResources(columnId){
    $.ajax({
        url: "/getResources?level=1&columnId=" + columnId,
        method: "post",
        async: false, // 同步

        success:function (data) {
            data = JSON.parse(data);
            console.log("columnId" + columnId);
            console.log(data);
            var conBox = document.getElementById('news_list');
            conBox.innerHTML = "";
            var content = "";
            for (var i = 0; i < data.length; i++) {

                content += " <li><span>"+data[i].createTime+"</span><a onclick='resourceClick('"+data[i].resourceId+")' href=\"#\">"+data[i].title+"</a></li>";
            }
            console.log("dsfasdf: ",content);
            conBox.innerHTML = content;

        }

    })
}

 function resourceClick(resourceId){
     $.ajax({
         url: "/getResourceContent?resourceId=" + resourceId,
         method: "post",
         async: false, // 同步

         success:function (data) {
             data = JSON.parse(data);
             console.log("resourceId" + resourceId);
             console.log(data);
             var conBox = document.getElementById('news_list');
             conBox.innerHTML = "";
             var content = "";
             content += "<li><span>"+data.createTime+"</span><p>"+data.content+" </p></li>"
             console.log("dsfasdf: ",content);
             conBox.innerHTML = content;

         }

     })
}


