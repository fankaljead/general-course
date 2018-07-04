function roleDistribute(moduleId) {
    var main = $("#content-main");

    //初始化content-main
    main.empty();

    var h1 = $("<h1>角色分配</h1>");
    h1.appendTo(main);

    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addEmployTable({})
}

/**
 * 添加表格 table
 * @param columnData
 */
function addEmployTable(columnData) {
    // $('#table').empty();
    $('#table').bootstrapTable({
        url: '/getEmployees',
        columns: [{
            field: 'account',
            title: '账号'
        }, {
            field: 'employeeName',
            title: '姓名'
        }, {
            field: 'sex',
            title: '性别'
        },{
            field: 'roleId',
            title: '角色编号'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='readyToRoleDistribute("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
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


function readyToRoleDistribute(row) {
    console.log("role row:", row)
    var form = document.createElement("form");

    $('#myModalLabelTitle').text("添加角色");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 员工帐号
    var div_for_show_account = document.createElement("div");
    div_for_show_account.setAttribute('class', 'form-group');
    var label_for_show_account = document.createElement("label");
    label_for_show_account.setAttribute('for', 'resourceId');
    label_for_show_account.innerText = "帐号";
    var show_for_show_account = document.createElement("input");
    show_for_show_account.setAttribute('type', 'text');
    show_for_show_account.setAttribute('disabled', 'true');
    show_for_show_account.setAttribute('class', 'form-control');
    show_for_show_account.setAttribute('id', 'message_show_id');
    div_for_show_account.appendChild(label_for_show_account);
    div_for_show_account.appendChild(show_for_show_account);
    form.appendChild(div_for_show_account);
    show_for_show_account.value = row.account;

    // 员工名字
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceName');
    label_for_show_name.innerText = "姓名";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('disabled', 'true');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('id', 'message_show_name');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    form.appendChild(div_for_show_name);
    // $('#message_show_name').val(row.employeeName+"");
    show_for_show_name.value = row.employeeName;

    // 角色选择
    var div_for_show_role= document.createElement("div");
    div_for_show_role.setAttribute('class', 'form-group');
    var label_for_show_role= document.createElement("label");
    label_for_show_role.innerText = "父模块名称";
    var  select_role = document.createElement("select");
    select_role.setAttribute('id', "add_role_select_id")
    select_role.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    $.ajax({
        url: '/getRoles',
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var option_role = document.createElement("option");
                option_role.setAttribute('value', data[i].roleId);
                option_role.innerText = data[i].roleName;
                select_role.appendChild(option_role);
            }
        }
    })
    div_for_show_role.appendChild(label_for_show_role);
    div_for_show_role.appendChild(document.createElement("br"));
    div_for_show_role.appendChild(select_role);
    form.appendChild(div_for_show_role);

    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        $.ajax({
            url: '/assignRole?employeeId='+$('#message_show_id').val()
            +"&roleId="+$('#add_role_select_id option:selected').val(),
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    sweetAlert("修改成功！");
                    var opt = {
                        url: '/getEmployees',
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