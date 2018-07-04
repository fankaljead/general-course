/**
 * @create: 2018年07月02日
 * @time: 11:09
 * @project_name: generalcourse
 * @author 周翔辉
 * @email: 728678732@qq.com
 * @description: 留言管理
 **/

function messageModuleClick(moduleId) {
    console.log("moduleId:", moduleId);

    $('#content-main').empty();
    var head = document.createElement("h1");
    head.innerText = "文章管理";
    document.getElementById('content-main').appendChild(head);
    $('#content-main').append("<div id=\"left-column\"></div>\n" +
        "            <div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "<div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");



    addTable({});// 添加表格
    // createModal()
    createMessageForm(); // 添加搜索表单
    addPagination(moduleId);// 添加分页
    addForm(moduleId)// 添加左侧导航
}


/**
 * 添加筛选
 * @param moduleId
 */
function addForm(moduleId) {

    $.ajax({
        url: "/getColumns?level=0",
        method: "post",
        async: false,
        success: function (data) {
            data = JSON.parse(data);
            console.table("module:", data)

            var columnDiv = document.createElement("div");
            columnDiv.setAttribute('width', '20%');

            for (var i = 0; i < data.length; i++) {
                var div = document.createElement("div");
                div.setAttribute('class', "nav-nav");
                var p = document.createElement("p");
                var ul = document.createElement("ul");
                // ul.setAttribute('class', 'nav nav-pills nav-stacked');

                p.innerText = data[i].columnName;

                p.setAttribute('onclick', 'selectColumnClick('+ data[i].columnId +')');



                $.ajax({
                    url: '/getColumns?level=1&columnId='+data[i].columnId,
                    method: 'post',
                    async: false,
                    success: function (columnData) {
                        columnData = JSON.parse(columnData);
                        for (var j = 0; j < columnData.length; j++) {
                            var li = document.createElement("li");
                            // var a = document.getElementById("a");
                            // a.innerText = columnData[j].columnName;
                            li.innerText = columnData[j].columnName;

                            // var subModule = data[i].subModules[j];
                            // 给每个模块添加点击事件，传染模块的id和事件ev

                            li.setAttribute('onclick', 'selectColumnClick('+ data[j].columnId +')');
                            // li.appendChild(a);
                            ul.appendChild(li);


                        }
                    }
                })



                div.appendChild(p);
                div.appendChild(ul);

                p.addEventListener('click', function (ev) {

                    $(this).parent().siblings().children("ul").css({"display":"none"});

                    var ul = $(this).siblings("ul");
                    if (ul.css("display") == "none") {
                        ul.css({"display":"block"});
                    }
                    else {
                        ul.css({"display":"none"});
                    }
                })
                columnDiv.appendChild(div);
            }
            var left_column = document.getElementById('left-column');
            $('#left-column').empty();
            left_column.appendChild(columnDiv);
            left_column.style = "width: 20%;\n" +
                "    float: left;";
        }
    })

}

/**
 * 处理点击栏目事件
 * Ajax 搜索
 * @param columnData
 */
function columnClick(columnData) {

}

/**
 * 添加表格 table
 * @param columnData
 */
function addTable(columnData) {
    // $('#table').empty();
    $('#table').bootstrapTable({
        url: '/getResources?columnId='+columnData.columnId,
        columns: [{
            field: 'resourceId',
            title: '序号'
        }, {
            field: 'title',
            title: '标题'
        }, {
            field: 'createTime',
            title: '创建时间'
        },{
            field: 'columnName',
            title: '栏目名称'
        },{
            field: 'whetherTop',
            title: '是否置顶'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    // var a = '<span  class = "toWhite" onclick = "showDetail('+row.resourceId+')">查看</span>  ';
                    var a = "<span  data-toggle=\"modal\" onclick='showDetail("+row.resourceId+")' data-target=\"#myModal\">\n" +
                        "\t查看\n" +
                        "</span>";
                    var b = "<span  data-toggle=\"modal\" onclick='readyToEdit("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t编辑\n" +
                        "</span>";
                    var c = '<span  class = "toWhite" \
                        onclick = "readyToDelete('+row.resourceId+','+JSON.stringify(columnData)+')"> 删除</span>';
                    return a+b+c;
                }
            }
        ],
        // pagination: true,
        // pageSize: 7,
        // pageNumber: 1,
        // sidePagination: 'server',
        // //得到查询的参数
        // queryParams : function (params) {
        //     //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        //     var temp = {
        //         pageSize: params.limit,                         //页面大小
        //         pageIndex: (params.offset / params.limit) + 1,   //页码
        //
        //     };
        //     return temp;
        // },
        // search: true,
        onCheck : function(row) {
            hmdId.push(row.id);

        },
        onUncheck : function(row) {
            for (var i = 0; i < hmdId.length; i++) {
                if (hmdId[i] == row.id) {
                    hmdId.splice(i, 1);
                }
            }
        },
        onCheckAll:function(rows){
            $("#exampleTableEvents>tbody>tr").addClass("selected");
            for (var j = 0; j < rows.length; j++) {
                hmdId.push(rows[j].id);
            }
        },
        onUncheckAll:function(rows){
            $("#exampleTableEvents>tbody>tr").removeClass("selected");
            for (var j = 0; j < rows.length; j++) {
                for (var i = 0; i < hmdId.length; i++) {
                    if (hmdId[i] == rows[j].id) {
                        hmdId.splice(i, 1);
                    }
                }
            }
        }

    });
}

function createModal() {
    var form_info = document.getElementById('form-info');
    var button = document.createElement("button");
    button.setAttribute('class', 'btn btn-primary btn-lg');
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#myModal');
    button.innerText = "button";
    form_info.appendChild(button)
}

function createMessageForm(column) {
    var form_info = document.getElementById('form-info');
    $('#form-info').empty();
    var form = document.createElement("form");
    form.setAttribute('role', 'form');

    // data-toggle=\"modal\" onclick='showDetail("+row.resourceId+")' data-target=\"#myModal\">
    // 新增按钮
    var div_for_input_add = document.createElement("div");
    div_for_input_add.setAttribute('class', 'col-lg-1');
    var input_for_input_add = document.createElement("input");
    input_for_input_add.setAttribute('data-toggle', 'modal');
    input_for_input_add.setAttribute('data-target', '#myModal');
    input_for_input_add.setAttribute('type', 'button');
    input_for_input_add.setAttribute('value', '新增');
    input_for_input_add.setAttribute('class', 'form-control');
    input_for_input_add.setAttribute('id', 'message_input_add_id');
    div_for_input_add.style = "backgroud: #2AABD2;"
    div_for_input_add.appendChild(input_for_input_add);
    form.appendChild(div_for_input_add);
    input_for_input_add.addEventListener('click', function (ev) {
        addResource(ev, column)
    })

    // 输入标题
    var div_for_input_title = document.createElement("div");
    div_for_input_title.setAttribute('class', 'col-lg-3');
    var label_for_input_title = document.createElement("label");
    label_for_input_title.setAttribute('for', 'name');
    label_for_input_title.innerText = "文章标题";
    var input_for_input_title = document.createElement("input");
    input_for_input_title.setAttribute('type', 'text');
    input_for_input_title.setAttribute('class', 'form-control');
    input_for_input_title.setAttribute('placeholder', '请输入标题');
    input_for_input_title.setAttribute('id', 'message_input_title_id');
    div_for_input_title.appendChild(label_for_input_title);
    div_for_input_title.appendChild(input_for_input_title);
    form.appendChild(div_for_input_title);
    // form_info.appendChild(form);

    // 输入开始时间
    var div_for_input_starTime = document.createElement("div");
    div_for_input_starTime.setAttribute('class', 'col-lg-3');
    var label_for_input_starTime = document.createElement("label");
    label_for_input_starTime.setAttribute('for', 'name');
    label_for_input_starTime.innerText = "创建时间从";
    var input_for_input_starTime = document.createElement("input");
    input_for_input_starTime.setAttribute('type', 'date');
    input_for_input_starTime.setAttribute('class', 'form-control');
    input_for_input_starTime.setAttribute('placeholder', '请输入标题');
    input_for_input_starTime.setAttribute('id', 'message_input_starTime_id');
    div_for_input_starTime.appendChild(label_for_input_starTime);
    div_for_input_starTime.appendChild(input_for_input_starTime);
    form.appendChild(div_for_input_starTime);
    // form_info.appendChild(form);

    // 输入结束时间
    var div_for_input_endTime = document.createElement("div");
    div_for_input_endTime.setAttribute('class', 'col-lg-3');
    var label_for_input_endTime = document.createElement("label");
    label_for_input_endTime.setAttribute('for', 'name');
    label_for_input_endTime.innerText = "创建时间到";
    var input_for_input_endTime = document.createElement("input");
    input_for_input_endTime.setAttribute('type', 'date');
    input_for_input_endTime.setAttribute('class', 'form-control');
    input_for_input_endTime.setAttribute('placeholder', '请输入标题');
    input_for_input_endTime.setAttribute('id', 'message_input_endTime_id');
    div_for_input_endTime.appendChild(label_for_input_endTime);
    div_for_input_endTime.appendChild(input_for_input_endTime);
    form.appendChild(div_for_input_endTime);
    // form_info.appendChild(form);

    // 搜索按钮
    var div_for_input_submit = document.createElement("div");
    div_for_input_submit.setAttribute('class', 'col-lg-1');
    var input_for_input_submit = document.createElement("input");
    input_for_input_submit.setAttribute('type', 'button');
    input_for_input_submit.setAttribute('value', '搜索');
    input_for_input_submit.setAttribute('class', 'form-control');
    input_for_input_submit.setAttribute('id', 'message_input_submit_id');
    div_for_input_submit.style = "padding-top: 25px;"
    div_for_input_submit.appendChild(input_for_input_submit);
    input_for_input_submit.addEventListener('click', function (ev) {
        var title = $('#message_input_title_id').val();
        var startTime = $('#message_input_starTime_id').val()==""?"":$('#message_input_starTime_id').val()+" 00:00:00";
        var endTime = $('#message_input_endTime_id').val()==""?"":$('#message_input_endTime_id').val() + " 23:59:59";
        if (startTime != "" && endTime == "") {
            endTime = CurrentTime().slice(0, 10) + " 23:59:59"
            // startTime += " 00:00:00";
        }
        if (startTime == "" && endTime != "") {
            startTime = CurrentTime().slice(0, 10) + " 00:00:00"
            // startTime += " 00:00:00";
        }
        console.log("start time:", startTime)
        console.log("end time:", endTime)
        var opt = {
            url: "/getResources?title="+title+"&startTime="+startTime+"&endTime="+endTime,
            silent: true,
        };
        $('#table').bootstrapTable('refresh',opt);
    })
    form.appendChild(div_for_input_submit);

    form_info.appendChild(form);
}

/**
 * 查看
 * @param rowId
 */
function showDetail(rowId) {
    console.log("check:", rowId)
    // var myModalLabelTitle = document.getElementById('myModalLabelTitle"');
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("查看文章");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 标题
    var div_for_show_title = document.createElement("div");
    div_for_show_title.setAttribute('class', 'form-group');
    var label_for_show_title = document.createElement("label");
    label_for_show_title.setAttribute('for', 'resourceId');
    label_for_show_title.innerText = "文章标题";
    var show_for_show_title = document.createElement("input");
    show_for_show_title.setAttribute('type', 'text');
    show_for_show_title.setAttribute('class', 'form-control');
    show_for_show_title.setAttribute('disabled', 'true');
    show_for_show_title.setAttribute('id', 'message_show_title_id');
    div_for_show_title.appendChild(label_for_show_title);
    div_for_show_title.appendChild(show_for_show_title);
    form.appendChild(div_for_show_title);

    // 创建时间
    var div_for_show_createTime = document.createElement("div");
    div_for_show_createTime.setAttribute('class', 'form-group');
    var label_for_show_createTime = document.createElement("label");
    label_for_show_createTime.setAttribute('for', 'resourceId');
    label_for_show_createTime.innerText = "文章创建时间";
    var show_for_show_createTime = document.createElement("input");
    show_for_show_createTime.setAttribute('type', 'text');
    show_for_show_createTime.setAttribute('disabled', 'true');
    show_for_show_createTime.setAttribute('class', 'form-control');
    show_for_show_createTime.setAttribute('id', 'message_show_createTime_id');
    div_for_show_createTime.appendChild(label_for_show_createTime);
    div_for_show_createTime.appendChild(show_for_show_createTime);
    form.appendChild(div_for_show_createTime);


    // 是否置顶
    var div_for_show_whetherTop = document.createElement("div");
    div_for_show_whetherTop.setAttribute('class', 'form-group');
    var label_for_show_whetherTop = document.createElement("label");
    label_for_show_whetherTop.setAttribute('for', 'resourceId');
    label_for_show_whetherTop.innerText = "是否置顶";
    var show_for_show_whetherTop = document.createElement("input");
    show_for_show_whetherTop.setAttribute('type', 'text');
    show_for_show_whetherTop.setAttribute('disabled', 'true');
    show_for_show_whetherTop.setAttribute('class', 'form-control');
    show_for_show_whetherTop.setAttribute('id', 'message_show_whetherTop_id');
    div_for_show_whetherTop.appendChild(label_for_show_whetherTop);
    div_for_show_whetherTop.appendChild(show_for_show_whetherTop);
    form.appendChild(div_for_show_whetherTop);
    // form_info.appendChild(form);


    // 资源状态
    var div_for_show_status = document.createElement("div");
    div_for_show_status.setAttribute('class', 'form-group');
    var label_for_show_status= document.createElement("label");
    label_for_show_status.setAttribute('for', 'resourceId');
    label_for_show_status.innerText = "资源状态";
    var show_for_show_status= document.createElement("input");
    show_for_show_status.setAttribute('type', 'text');
    show_for_show_status.setAttribute('disabled', 'true');
    show_for_show_status.setAttribute('class', 'form-control');
    show_for_show_status.setAttribute('id', 'message_show_status_id');
    div_for_show_status.appendChild(label_for_show_status);
    div_for_show_status.appendChild(show_for_show_status);
    form.appendChild(div_for_show_status);

    // 栏目名称
    var div_for_show_columnName= document.createElement("div");
    div_for_show_columnName.setAttribute('class', 'form-group');
    var label_for_show_columnName= document.createElement("label");
    label_for_show_columnName.innerText = "栏目名称";
    var show_for_show_columnName= document.createElement("input");
    show_for_show_columnName.setAttribute('type', 'text');
    show_for_show_columnName.setAttribute('disabled', 'true');
    show_for_show_columnName.setAttribute('class', 'form-control');
    show_for_show_columnName.setAttribute('id', 'message_show_columnName_id');
    div_for_show_columnName.appendChild(label_for_show_columnName);
    div_for_show_columnName.appendChild(show_for_show_columnName);
    form.appendChild(div_for_show_columnName);

    // 内容
    var div_for_show_content= document.createElement("div");
    div_for_show_content.setAttribute('class', 'form-group');
    var label_for_show_content= document.createElement("label");
    label_for_show_content.innerText = "内容";
    var show_for_show_content= document.createElement("textarea");
    // show_for_show_content.setAttribute('type', 'textAres');
    show_for_show_content.setAttribute('disabled', 'true');
    show_for_show_content.setAttribute('class', 'form-control');
    show_for_show_content.style = "height: 140px";
    show_for_show_content.setAttribute('id', 'message_show_content_id');
    div_for_show_content.appendChild(label_for_show_content);
    div_for_show_content.appendChild(show_for_show_content);
    form.appendChild(div_for_show_content);

    modal_body.appendChild(form);

    $.ajax({
        url: "/getResourceContent?resourceId=" + rowId,
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            console.log("content:", data)
            $('#message_show_content_id').val(data.content);
            $('#message_show_title_id').val(data.title);
            $('#message_show_createTime_id').val(data.createTime);
            $('#message_show_whetherTop_id').val(data.whetherTop == 0 ? "不置顶" : "置顶");
            $('#message_show_columnName_id').val(data.columnName);

            if (data.status == 0) {
                $('#message_show_status_id').val("未审核");
            } else if (data.status == 1) {
                $('#message_show_status_id').val("审核通过");
            } else {
                $('#message_show_status_id').val("审核未通过");
            }
        }

    })
}

function readyToDelete(rowId, columnData) {
    swal({
        title: "您确定要删除吗？",
        text: "您确定要删除这条数据？",
        type: "warning",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: 'slide-from-top',
        confirmButtonText: "是的，我要删除",
        confirmButtonColor: "#ec6c62"
    }, function () {
        $.ajax({
            url: '/deleteResources',
            method: 'post',
            data: {
                resourceIds:
                    "["+rowId+"]",
                // answerId: $.cookie('answerId'),
            },
            dataType: "json",
            success: function (data) {
                console.log("delete:", data)
                if (data == 1) {
                    var opt = {
                        url: '/getResources?columnId='+columnData.columnId,
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                }
            }
        })
        swal.close()
    });
}


/**
 * 新增资源
 * @param ev
 */
function addResource(ev, column) {
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("新增文章");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 标题
    var div_for_show_title = document.createElement("div");
    div_for_show_title.setAttribute('class', 'form-group');
    var label_for_show_title = document.createElement("label");
    label_for_show_title.setAttribute('for', 'resourceId');
    label_for_show_title.innerText = "文章标题";
    var show_for_show_title = document.createElement("input");
    show_for_show_title.setAttribute('type', 'text');
    show_for_show_title.setAttribute('class', 'form-control');
    show_for_show_title.setAttribute('id', 'message_show_title_id');
    div_for_show_title.appendChild(label_for_show_title);
    div_for_show_title.appendChild(show_for_show_title);
    form.appendChild(div_for_show_title);

    // 栏目名称
    var div_for_show_columnName= document.createElement("div");
    div_for_show_columnName.setAttribute('class', 'form-group');
    var label_for_show_columnName= document.createElement("label");
    label_for_show_columnName.innerText = "栏目名称";
    var  select_column = document.createElement("select");
    select_column.setAttribute('id', "add_column_select_id")
    select_column.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    $.ajax({
        url: '/getColumns',
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var option_column = document.createElement("option");
                option_column.setAttribute('value', data[i].columnId);
                option_column.innerText = data[i].columnName;
                select_column.appendChild(option_column);
            }
        }
    })
    div_for_show_columnName.appendChild(label_for_show_columnName);
    div_for_show_columnName.appendChild(document.createElement("br"));
    div_for_show_columnName.appendChild(select_column);
    // div_for_show_columnName.appendChild(show_for_show_columnName);
    form.appendChild(div_for_show_columnName);

    // 是否置顶
    var div_for_show_whetherTop = document.createElement("div");
    div_for_show_whetherTop.setAttribute('class', 'form-group');
    var label_for_show_whetherTop = document.createElement("label");
    label_for_show_whetherTop.setAttribute('for', 'resourceId');
    label_for_show_whetherTop.innerText = "是否置顶";
    div_for_show_whetherTop.appendChild(label_for_show_whetherTop);
    var  select_top = document.createElement("select");
    select_top.setAttribute('id', "add_top_select_id")
    select_top.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;"
    var top = document.createElement("option");
    top.setAttribute('value', '0');
    top.innerText = "不置顶";
    var notTop = document.createElement("option");
    notTop.setAttribute('value', '1');
    notTop.innerText = "置顶";
    select_top.appendChild(top);
    select_top.appendChild(notTop);
    div_for_show_whetherTop.appendChild(document.createElement("br"));
    div_for_show_whetherTop.appendChild(select_top);


    form.appendChild(div_for_show_whetherTop);
    // form_info.appendChild(form);

    // var show_for_show_columnName= document.createElement("input");
    // show_for_show_columnName.setAttribute('type', 'text');
    // show_for_show_columnName.setAttribute('class', 'form-control');
    // show_for_show_columnName.setAttribute('id', 'message_show_columnName_id');


    // 内容
    var div_for_show_content= document.createElement("div");
    div_for_show_content.setAttribute('class', 'form-group');
    var label_for_show_content= document.createElement("label");
    label_for_show_content.innerText = "内容";
    var show_for_show_content= document.createElement("textarea");
    // show_for_show_content.setAttribute('type', 'textAres');
    show_for_show_content.setAttribute('class', 'form-control');
    show_for_show_content.setAttribute('id', 'message_show_content_id');
    show_for_show_content.style = "height: 200px;"
    div_for_show_content.appendChild(label_for_show_content);
    div_for_show_content.appendChild(show_for_show_content);
    form.appendChild(div_for_show_content);

    modal_body.appendChild(form);

    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        console.log("栏目：", $('#add_column_select_id option:selected').val())
        var createTime = CurrentTime();
        $.ajax({
            url: '/addResource?title='+$('#message_show_title_id').val()+"&createTime="+createTime+
            "&columnId="+$('#add_column_select_id option:selected').val()+ "&content="+$('#message_show_content_id').val()
            +"&whetherTop="+$('#add_top_select_id option:selected').val(),
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    alert("新增成功！");
                    var opt = {
                        url: '/getResources',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {
                    alert("新增失败");
                }
            }
        })
    }
}

function addArticle(ev1) {
    console.log("栏目：", $('#add_column_select_id option:selected').val())
    var createTime = CurrentTime();
    $.ajax({
        url: '/addResource?title='+$('#message_show_title_id').val()+"&createTime="+createTime+
        "&columnId="+$('#add_column_select_id option:selected').val()+ "&content="+$('#message_show_content_id').val()
        +"&whetherTop="+$('#add_top_select_id option:selected').val(),
        method: 'post',
        success: function (data) {
            if (data == 1) {
                alert("新增成功！");
                var opt = {
                    url: '/getResources',
                    silent: true,
                };
                $('#table').bootstrapTable('refresh',opt);
            } else {
                alert("新增失败");
            }
        }
    })
}

function readyToEdit(rowId) {
    console.log("check:", rowId)
    // var myModalLabelTitle = document.getElementById('myModalLabelTitle"');
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("编辑文章");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 标题
    var div_for_show_title = document.createElement("div");
    div_for_show_title.setAttribute('class', 'form-group');
    var label_for_show_title = document.createElement("label");
    label_for_show_title.setAttribute('for', 'resourceId');
    label_for_show_title.innerText = "文章标题";
    var show_for_show_title = document.createElement("input");
    show_for_show_title.setAttribute('type', 'text');
    show_for_show_title.setAttribute('class', 'form-control');
    // show_for_show_title.setAttribute('disabled', 'true');
    show_for_show_title.setAttribute('id', 'message_show_title_id');
    div_for_show_title.appendChild(label_for_show_title);
    div_for_show_title.appendChild(show_for_show_title);
    form.appendChild(div_for_show_title);


    // 是否置顶
    var div_for_show_whetherTop = document.createElement("div");
    div_for_show_whetherTop.setAttribute('class', 'form-group');
    var label_for_show_whetherTop = document.createElement("label");
    label_for_show_whetherTop.setAttribute('for', 'resourceId');
    label_for_show_whetherTop.innerText = "是否置顶";
    div_for_show_whetherTop.appendChild(label_for_show_whetherTop);
    var  select_top = document.createElement("select");
    select_top.setAttribute('id', "add_top_select_id")
    select_top.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;"
    var top = document.createElement("option");
    top.setAttribute('value', '0');
    top.innerText = "不置顶";
    var notTop = document.createElement("option");
    notTop.setAttribute('value', '1');
    notTop.innerText = "置顶";
    select_top.appendChild(top);
    select_top.appendChild(notTop);
    div_for_show_whetherTop.appendChild(document.createElement("br"));
    div_for_show_whetherTop.appendChild(select_top);
    form.appendChild(div_for_show_whetherTop);


    // 资源状态
    var div_for_show_status = document.createElement("div");
    div_for_show_status.setAttribute('class', 'form-group');
    var label_for_show_status= document.createElement("label");
    label_for_show_status.setAttribute('for', 'resourceId');
    label_for_show_status.innerText = "资源状态";
    var show_for_show_status= document.createElement("input");
    show_for_show_status.setAttribute('type', 'text');
    show_for_show_status.setAttribute('disabled', 'true');
    show_for_show_status.setAttribute('class', 'form-control');
    show_for_show_status.setAttribute('id', 'message_show_status_id');
    div_for_show_status.appendChild(label_for_show_status);
    div_for_show_status.appendChild(show_for_show_status);
    form.appendChild(div_for_show_status);

    // 栏目名称
    var div_for_show_columnName= document.createElement("div");
    div_for_show_columnName.setAttribute('class', 'form-group');
    var label_for_show_columnName= document.createElement("label");
    label_for_show_columnName.innerText = "栏目名称";
    var  select_column = document.createElement("select");
    select_column.setAttribute('id', "add_column_select_id")
    select_column.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    $.ajax({
        url: '/getColumns',
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var option_column = document.createElement("option");
                option_column.setAttribute('value', data[i].columnId);
                option_column.innerText = data[i].columnName;
                if(data[i].columnId == rowId.columnId) {
                    option_column.setAttribute('selected', 'selected');
                }
                select_column.appendChild(option_column);
            }
        }
    })
    div_for_show_columnName.appendChild(label_for_show_columnName);
    div_for_show_columnName.appendChild(document.createElement("br"));
    div_for_show_columnName.appendChild(select_column);
    form.appendChild(div_for_show_columnName);

    // 内容
    var div_for_show_content= document.createElement("div");
    div_for_show_content.setAttribute('class', 'form-group');
    var label_for_show_content= document.createElement("label");
    label_for_show_content.innerText = "内容";
    var show_for_show_content= document.createElement("textarea");
    // show_for_show_content.setAttribute('type', 'textAres');
    // show_for_show_content.setAttribute('disabled', 'true');
    show_for_show_content.setAttribute('class', 'form-control');
    show_for_show_content.style = "height: 150px";
    show_for_show_content.setAttribute('id', 'message_show_content_id');
    div_for_show_content.appendChild(label_for_show_content);
    div_for_show_content.appendChild(show_for_show_content);
    form.appendChild(div_for_show_content);

    modal_body.appendChild(form);

    $.ajax({
        url: "/getResourceContent?resourceId=" + rowId.resourceId,
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            console.log("content:", data)
            $('#message_show_content_id').val(data.content);
            $('#message_show_title_id').val(data.title);
            $('#message_show_whetherTop_id').val(data.whetherTop == 0 ? "不置顶" : "置顶");
            $('#message_show_columnName_id').val(data.columnName);

            if (data.status == 0) {
                $('#message_show_status_id').val("未审核");
            } else if (data.status == 1) {
                $('#message_show_status_id').val("审核通过");
            } else {
                $('#message_show_status_id').val("审核未通过");
            }
        }

    })

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        console.log("栏目：", $('#add_column_select_id option:selected').val())
        var createTime = CurrentTime();
        $.ajax({
            url: '/updateResource?title='+$('#message_show_title_id').val()+"&createTime="+createTime+
            "&columnId="+$('#add_column_select_id option:selected').val()+ "&content="+$('#message_show_content_id').val()
            +"&whetherTop="+$('#add_top_select_id option:selected').val()+"&resourceId="+rowId.resourceId,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    alert("修改成功！");
                    var opt = {
                        url: '/getResources',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {
                    alert("修改失败");
                }
            }
        })
    }
}


function addPagination(moduleId) {
    var pagination = "<div class=\"row clearfix\">\n" +
        "\t\t<div class=\"col-md-12 column\">\n" +
        "\t\t\t<ul class=\"pagination\">\n" +
        "\t\t\t\t<li>\n" +
        "\t\t\t\t\t <a href=\"#\" onclick='resourcePrePage()'>上一页</a>\n" +
        "\t\t\t\t</li>\n" +
        "<li>\n" +
        "\t\t\t\t\t <a href=\"#\" id='article_page_index'>第1页</a>\n" +
        "\t\t\t\t</li>\n" +
        "\t\t\t\t<li>\n" +
        "\t\t\t\t\t <a href=\"#\" onclick='resourceNextPage()'>下一页</a>\n" +
        "\t\t\t\t</li>\n" +
        "\t\t\t</ul>\n" +
        "\t\t</div>\n" +
        "\t</div>";
    window.articlePageIndex = 1;
    window.articlePageSize = 7;
    var pagination_div = document.createElement("div");
    pagination_div.setAttribute('class', 'container');
    pagination_div.innerHTML = pagination;
    document.getElementById('content-main-table').appendChild(pagination_div);

}

/**
 * 上一页
 */
function resourcePrePage() {
    if (window.articlePageIndex > 0) {
        window.articlePageIndex--;
        document.getElementById('article_page_index').innerText = "第" + window.articlePageIndex + "页";
        var opt = {
            url: '/getResources?pageIndex='+window.articlePageIndex+"&pageSize="+window.articlePageSize,
            silent: true,
        };
        $('#table').bootstrapTable('refresh', opt);
    }
}


/**
 * 下一页
 */
function resourceNextPage() {
    if (window.articlePageIndex > 0) {
        window.articlePageIndex++;
        document.getElementById('article_page_index').innerText = "第" + window.articlePageIndex + "页";
        var opt = {
            url: '/getResources?pageIndex='+window.articlePageIndex+"&pageSize="+window.articlePageSize,
            silent: true,
        };
        $('#table').bootstrapTable('refresh', opt);
    }
}


/**
 * 根据栏目搜索
 * @param columnId
 */
function selectColumnClick(columnId) {
    var opt = {
        url: '/getResources?columnId='+columnId,
        silent: true,
    };
    console.log("table sedd:", columnId)
    $('#table').bootstrapTable('refresh', opt);
}

