function moduleManage(moduleId) {
    var main = $("#content-main");

    //初始化content-main
    main.empty();

    var h1 = $("<h1>模块管理</h1>");
    h1.appendTo(main);

    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addSubModuleManageTable({});
}

function addSubModuleManageTable(columnData) {
    $('#table').bootstrapTable({
        url: '/getAllSubModules',
        columns: [{
            field: 'moduleId',
            title: '模块id'
        }, {
            field: 'moduleName',
            title: '模块名称'
        }, {
            field: 'parentModuleId',
            title: '父模块id'
        }, {
            field: 'status',
            title: '是否被禁用'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='readyToEditModule("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t编辑\n" +
                        "</span>";
                    return b;
                }
            }
        ],
        pagination: true,
        pageSize: 5,
        pageNumber:1,

        search: true,
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

function readyToEditModule(row) {
    console.log("role row:", row)
    var form = document.createElement("form");

    $('#myModalLabelTitle').text("编辑模块信息");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 模块id
    var div_for_show_id = document.createElement("div");
    div_for_show_id.setAttribute('class', 'form-group');
    var label_for_show_id = document.createElement("label");
    label_for_show_id.setAttribute('for', 'resourceId');
    label_for_show_id.innerText = "模块id";
    var show_for_show_id = document.createElement("input");
    show_for_show_id.setAttribute('type', 'text');
    show_for_show_id.setAttribute('disabled', 'true');
    show_for_show_id.setAttribute('class', 'form-control');
    show_for_show_id.setAttribute('id', 'message_show_id');
    div_for_show_id.appendChild(label_for_show_id);
    div_for_show_id.appendChild(show_for_show_id);
    form.appendChild(div_for_show_id);
    show_for_show_id.value = row.moduleId;

    // 模块名称
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceName');
    label_for_show_name.innerText = "模块名称";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('id', 'message_show_name');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    form.appendChild(div_for_show_name);
    // $('#message_show_name').val(row.employeeName+"");
    show_for_show_name.value = row.moduleName;

    // 父模块
    var div_for_show_parentModuleId= document.createElement("div");
    div_for_show_parentModuleId.setAttribute('class', 'form-group');
    var label_for_show_parentModuleId= document.createElement("label");
    label_for_show_parentModuleId.innerText = "父模块名称";
    var  select_parentModuleId = document.createElement("select");
    select_parentModuleId.setAttribute('id', "add_parentModuleId_select_id")
    select_parentModuleId.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    $.ajax({
        url: '/getModule',
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var option_parentModuleId = document.createElement("option");
                option_parentModuleId.setAttribute('value', data[i].moduleId);
                option_parentModuleId.innerText = data[i].moduleName;
                select_parentModuleId.appendChild(option_parentModuleId);
            }
        }
    })
    div_for_show_parentModuleId.appendChild(label_for_show_parentModuleId);
    div_for_show_parentModuleId.appendChild(document.createElement("br"));
    div_for_show_parentModuleId.appendChild(select_parentModuleId);
    form.appendChild(div_for_show_parentModuleId);

    // 是否被禁用
    var div_for_show_status = document.createElement("div");
    div_for_show_status.setAttribute('class', 'form-group');
    var label_for_show_status = document.createElement("label");
    label_for_show_status.setAttribute('for', 'resourceId');
    label_for_show_status.innerText = "是否被禁用";
    div_for_show_status.appendChild(label_for_show_status);
    var  select_status = document.createElement("select");
    select_status.setAttribute('id', "add_status_select_id")
    select_status.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;"
    var disable = document.createElement("option");
    disable.setAttribute('value', '0');
    disable.innerText = "禁用";
    var enabled = document.createElement("option");
    enabled.setAttribute('value', '1');
    enabled.innerText = "不禁用";
    select_status.appendChild(disable);
    select_status.appendChild(enabled);
    div_for_show_status.appendChild(document.createElement("br"));
    div_for_show_status.appendChild(select_status);
    if(row.status == 0) {
        disable.setAttribute('selected', 'selected');
    } else if ((row.status == 1)) {
        enabled.setAttribute('selected', 'selected');
    }
    form.appendChild(div_for_show_status);

    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        $.ajax({
            url: '/updateModule?moduleId='+$('#message_show_id').val()
            +"&moduleName="+$("#message_show_name").val()
            +"&status="+$('#add_status_select_id option:selected').val()
            +"&parentModuleId="+$('#add_parentModuleId_select_id option:selected').val(),
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    sweetAlert("修改成功！");
                    var opt = {
                        url: '/getAllSubModules',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {
                    sweetAlert("修改失败");
                }
            }
        })
    }
}

