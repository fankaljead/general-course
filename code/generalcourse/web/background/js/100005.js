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

    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        $.ajax({
            url: '/updateRole?roleId='+$('#message_show_id').val()
            +"&roleName="+row.account
            +"&description="+$('#add_sex_select_id option:selected').val()
            +"&ownModuleIds=",
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    alert("修改成功！");
                    var opt = {
                        url: '/getEmployees',
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