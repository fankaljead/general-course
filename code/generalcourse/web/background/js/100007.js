function roleManagement(moduleId) {
     var main = $("#content-main");

    //初始化content-main
    main.empty();

    var h1 = $("<h1>角色管理</h1>");
    h1.appendTo(main);

    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addRoleManageTable({})
    createRoleForm();
}

/**
 * 添加表格 table
 * @param columnData
 */
function addRoleManageTable(columnData) {
    // $('#table').empty();
    $('#table').bootstrapTable({
        url: '/getRoles',
        columns: [{
            field: 'roleId',
            title: '角色id'
        }, {
            field: 'roleName',
            title: '角色名称'
        }, {
            field: 'createTime',
            title: '创建时间'
        },{
            field: 'description',
            title: '描述'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var a = "<span  data-toggle=\"modal\" onclick='showRoleDetail("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t查看\n" +
                        "</span>";
                    var b = "<span  data-toggle=\"modal\" onclick='readyToEditRole("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t编辑\n" +
                        "</span>";
                    var c = '<span  class = "toWhite" \
                        onclick = "readyToDeleteRole('+row.roleId+')"> 删除</span>';
                    return a+b+c;
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

//查看角色信息
function showRoleDetail(row) {
    console.log("check:", row)
    // var myModalLabelTitle = document.getElementById('myModalLabelTitle"');
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("查看角色");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 角色名称
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceId');
    label_for_show_name.innerText = "角色名称";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('disabled', 'true');
    show_for_show_name.setAttribute('id', 'message_show_name_id');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    show_for_show_name.value = row.roleName;
    form.appendChild(div_for_show_name);

    // 创建时间
    var div_for_show_createTime = document.createElement("div");
    div_for_show_createTime.setAttribute('class', 'form-group');
    var label_for_show_createTime = document.createElement("label");
    label_for_show_createTime.setAttribute('for', 'resourceId');
    label_for_show_createTime.innerText = "角色创建时间";
    var show_for_show_createTime = document.createElement("input");
    show_for_show_createTime.setAttribute('type', 'text');
    show_for_show_createTime.setAttribute('disabled', 'true');
    show_for_show_createTime.setAttribute('class', 'form-control');
    show_for_show_createTime.setAttribute('id', 'message_show_createTime_id');
    div_for_show_createTime.appendChild(label_for_show_createTime);
    div_for_show_createTime.appendChild(show_for_show_createTime);
    show_for_show_createTime.value = row.createTime;
    form.appendChild(div_for_show_createTime);

    // 描述
    var div_for_show_description = document.createElement("div");
    div_for_show_description.setAttribute('class', 'form-group');
    var label_for_show_description = document.createElement("label");
    label_for_show_description.setAttribute('for', 'resourceDescription');
    label_for_show_description.innerText = "角色描述";
    var show_for_show_description = document.createElement("input");
    show_for_show_description.setAttribute('type', 'text');
    show_for_show_description.setAttribute('disabled', 'true');
    show_for_show_description.setAttribute('class', 'form-control');
    show_for_show_description.setAttribute('id', 'message_show_description');
    div_for_show_description.appendChild(label_for_show_description);
    div_for_show_description.appendChild(show_for_show_description);
    show_for_show_description.value = row.description;
    form.appendChild(div_for_show_description);

    // 模组
    var div_for_show_ownModuleIds = document.createElement("div");
    div_for_show_ownModuleIds.setAttribute('class', 'form-group');
    var label_for_show_ownModuleIds = document.createElement("label");
    label_for_show_ownModuleIds.setAttribute('for', 'resourceId');
    label_for_show_ownModuleIds.innerText = "具有权限的模组";
    div_for_show_ownModuleIds.appendChild(label_for_show_ownModuleIds);

    $.ajax({
        url: "/getPermissionByRoleId?roleId="+row.roleId,
        method: "post",
        async: false,
        success: function(data) {
            data = JSON.parse(data);

            var div = document.createElement("div");
            for (var i = 0; i < data.length; i++) {
                var label = document.createElement("label");
                label.append(data[i].moduleName);
                div.appendChild(label);
                div.appendChild(document.createElement("br"));
            }

            div_for_show_ownModuleIds.appendChild(div);
        }
    })
    form.appendChild(div_for_show_ownModuleIds);

    modal_body.appendChild(form);
}

//修改角色信息
function readyToEditRole(row) {
    console.log("role row:", row)
    var form = document.createElement("form");

    $('#myModalLabelTitle').text("编辑角色信息");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 角色id
    var div_for_show_id = document.createElement("div");
    div_for_show_id.setAttribute('class', 'form-group');
    var label_for_show_id = document.createElement("label");
    label_for_show_id.setAttribute('for', 'resourceId');
    label_for_show_id.innerText = "角色id";
    var show_for_show_id = document.createElement("input");
    show_for_show_id.setAttribute('type', 'text');
    show_for_show_id.setAttribute('class', 'form-control');
    show_for_show_id.setAttribute('id', 'message_show_id');
    div_for_show_id.appendChild(label_for_show_id);
    div_for_show_id.appendChild(show_for_show_id);
    form.appendChild(div_for_show_id);
    show_for_show_id.value = row.roleId;

    // 角色名字
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceName');
    label_for_show_name.innerText = "角色名称";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('id', 'message_show_name');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    form.appendChild(div_for_show_name);
    // $('#message_show_name').val(row.employeeName+"");
    show_for_show_name.value = row.roleName;

    // 描述
    var div_for_show_description = document.createElement("div");
    div_for_show_description.setAttribute('class', 'form-group');
    var label_for_show_description = document.createElement("label");
    label_for_show_description.setAttribute('for', 'resourceDescription');
    label_for_show_description.innerText = "角色描述";
    var show_for_show_description = document.createElement("input");
    show_for_show_description.setAttribute('type', 'text');
    show_for_show_description.setAttribute('class', 'form-control');
    show_for_show_description.setAttribute('id', 'message_show_description');
    div_for_show_description.appendChild(label_for_show_description);
    div_for_show_description.appendChild(show_for_show_description);
    form.appendChild(div_for_show_description);
    show_for_show_description.value = row.description;

    // 模组
    var div_for_show_ownModuleIds = document.createElement("div");
    div_for_show_ownModuleIds.setAttribute('class', 'form-group');
    var label_for_show_ownModuleIds = document.createElement("label");
    label_for_show_ownModuleIds.setAttribute('for', 'resourceId');
    label_for_show_ownModuleIds.innerText = "模组选择";
    div_for_show_ownModuleIds.appendChild(label_for_show_ownModuleIds);
    $.ajax({
        url: "/getModule",
        method: "post",
        async: false,
        success: function(data) {
            data = JSON.parse(data);

            var div_checkbox = document.createElement("div");
            div_checkbox.setAttribute("id","div_checkbox_id");
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].subModules.length; j++) {
                    var input = document.createElement("input");
                    input.setAttribute("type", "checkbox");
                    input.setAttribute('name', 'roleAddCheck');
                    input.setAttribute("value",""+data[i].subModules[j].subModuleId);
                    div_checkbox.appendChild(input);
                    div_checkbox.append(data[i].subModules[j].subModuleName);
                    div_checkbox.appendChild(document.createElement("br"));
                }
            }

            div_for_show_ownModuleIds.appendChild(div_checkbox);
        }
    })
    form.appendChild(div_for_show_ownModuleIds);

    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        var obj = document.getElementsByName("roleAddCheck");
        var check_val = [];
        for(k in obj){
            if(obj[k].checked)
                check_val.push(obj[k].value);
        }
        var m = '['+check_val.toString()+']';

        $.ajax({
            url: '/updateRole?roleId='+$('#message_show_id').val()
            +"&roleName="+$('#message_show_name').val()
            +"&description="+$('#message_show_description').val()
            +"&ownModuleIds="+m,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    alert("修改成功！");
                    var opt = {
                        url: '/getRoles',
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

function readyToDeleteRole(row) {
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
        var ids = [];
        var m = '['+row+']';

        $.ajax({

            url: '/deleteRoles?roleIds='+m,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    var opt = {
                        url: '/getRoles',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                }
            }
        })
        swal.close()
    });
}

function createRoleForm(column) {
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
        addRole(ev, column)
    })

    form_info.appendChild(form);
}

/**
 * 新增角色
 * @param ev
 */
function addRole(ev, column) {
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("新增角色");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 角色名字
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceId');
    label_for_show_name.innerText = "角色名字";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('id', 'message_show_name_id');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    form.appendChild(div_for_show_name);

    // 角色描述
    var div_for_show_description = document.createElement("div");
    div_for_show_description.setAttribute('class', 'form-group');
    var label_for_show_description = document.createElement("label");
    label_for_show_description.setAttribute('for', 'resourceDescription');
    label_for_show_description.innerText = "角色描述";
    var show_for_show_description = document.createElement("input");
    show_for_show_description.setAttribute('type', 'text');
    show_for_show_description.setAttribute('class', 'form-control');
    show_for_show_description.setAttribute('id', 'message_show_description_id');
    div_for_show_description.appendChild(label_for_show_description);
    div_for_show_description.appendChild(show_for_show_description);
    form.appendChild(div_for_show_description);

    // 模组
    var div_for_show_ownModuleIds = document.createElement("div");
    div_for_show_ownModuleIds.setAttribute('class', 'form-group');
    var label_for_show_ownModuleIds = document.createElement("label");
    label_for_show_ownModuleIds.setAttribute('for', 'resourceId');
    label_for_show_ownModuleIds.innerText = "模组选择";
    div_for_show_ownModuleIds.appendChild(label_for_show_ownModuleIds);
    $.ajax({
        url: "/getModule",
        method: "post",
        async: false,
        success: function(data) {
            data = JSON.parse(data);

            var div_checkbox = document.createElement("div");
            div_checkbox.setAttribute("id","div_checkbox_id");
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].subModules.length; j++) {
                    var input = document.createElement("input");
                    input.setAttribute("type", "checkbox");
                    input.setAttribute('name', 'roleAddCheck');
                    input.setAttribute("value",""+data[i].subModules[j].subModuleId);
                    div_checkbox.appendChild(input);
                    div_checkbox.append(data[i].subModules[j].subModuleName);
                    div_checkbox.appendChild(document.createElement("br"));
                }
            }

            div_for_show_ownModuleIds.appendChild(div_checkbox);
        }
    })
    form.appendChild(div_for_show_ownModuleIds);

    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        var obj = document.getElementsByName("roleAddCheck");
        var check_val = [];
        for(k in obj){
            if(obj[k].checked)
                check_val.push(obj[k].value);
        }
        var m = '['+check_val.toString()+']'
        //创建时间
        var createTime = CurrentTime();
        $.ajax({
            url: '/addRole?roleName='+$('#message_show_name_id').val() +
            "&createTime="+createTime
            +"&description="+$('#message_show_description_id').val()
            +"&ownModuleIds="+m,
            method: 'post',
            success: function (data) {
                if (data != 0) {
                    // alert("新增成功")
                    sweetAlert(
                        '新增成功'
                    );
                    var opt = {
                        url: '/getRoles',
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