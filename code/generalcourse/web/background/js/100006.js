/**
 * @create: 2018年07月03日
 * @time: 20:36
 * @project_name: generalcourse
 * @author 周翔辉
 * @email: 728678732@qq.com
 * @description: 人员管理
 **/

/**
 * 人员模块管理
 * @param moduleId
 */
function employeeModuleClick(moduleId) {
    console.log("moduleId:", moduleId);

    $('#content-main').empty();
    var head = document.createElement("h1");
    head.innerText = "人员管理";
    document.getElementById('content-main').appendChild(head);
    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addEmployManageTable({})
    createEmployeeForm();
    addPaginationEmployee();
}

/**
 * 添加表格 table
 * @param columnData
 */
function addEmployManageTable(columnData) {
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
            field: 'roleName',
            title: '角色名称',
            undefinedText: "未分配角色"
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='readyToEditEmployee("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t编辑\n" +
                        "</span>";
                    var c = '<span  class = "toWhite" \
                        onclick = "readyToDeleteEmployee('+row.account+')"> 删除</span>';
                    return b+c;
                }
            }
        ],

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


function readyToEditEmployee(row) {
    console.log("employee row:", row)
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("编辑人员信息");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 名字
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceId');
    label_for_show_name.innerText = "名字";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('id', 'message_show_name');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    form.appendChild(div_for_show_name);
    // $('#message_show_name').val(row.employeeName+"");
    show_for_show_name.value = row.employeeName;

    // 性别
    var div_for_show_sex = document.createElement("div");
    div_for_show_sex.setAttribute('class', 'form-group');
    var label_for_show_sex = document.createElement("label");
    label_for_show_sex.setAttribute('for', 'resourceId');
    label_for_show_sex.innerText = "性别";
    div_for_show_sex.appendChild(label_for_show_sex);
    var  select_sex = document.createElement("select");
    select_sex.setAttribute('id', "add_sex_select_id")
    select_sex.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;"
    var unKnown = document.createElement("option");
    unKnown.setAttribute('value', '0');
    unKnown.innerText = "秘密";
    var male = document.createElement("option");
    male.setAttribute('value', '1');
    male.innerText = "男性";
    var female = document.createElement("option");
    female.setAttribute('value', '2');
    female.innerText = "女性";
    select_sex.appendChild(unKnown);
    select_sex.appendChild(male);
    select_sex.appendChild(female)
    div_for_show_sex.appendChild(document.createElement("br"));
    div_for_show_sex.appendChild(select_sex);
    if(row.sex == 0) {
        unKnown.setAttribute('selected', 'selected');
    } else if ((row.sex == 1)) {
        male.setAttribute('selected', 'selected');
    } else {
        female.setAttribute('selected', 'selected');
    }


    form.appendChild(div_for_show_sex);
    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        $.ajax({
            url: '/updateEmployee?employeeName='+$('#message_show_name').val()
                +"&employeeId="+row.account
            +"&sex="+$('#add_sex_select_id option:selected').val(),
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

function readyToDeleteEmployee(row) {
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

        $.ajax({
            url: '/deleteByEmployeeId?employeeId='+row,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    var opt = {
                        url: '/getEmployees',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                }
            }
        })
        swal.close()
    });
}

function createEmployeeForm(column) {
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
        addEmployee(ev, column)
    })

    form_info.appendChild(form);
}

/**
 * 新增人员
 * @param ev
 */
function addEmployee(ev, column) {
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("新增人员");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 名字
    var div_for_show_name = document.createElement("div");
    div_for_show_name.setAttribute('class', 'form-group');
    var label_for_show_name = document.createElement("label");
    label_for_show_name.setAttribute('for', 'resourceId');
    label_for_show_name.innerText = "名字";
    var show_for_show_name = document.createElement("input");
    show_for_show_name.setAttribute('type', 'text');
    show_for_show_name.setAttribute('class', 'form-control');
    show_for_show_name.setAttribute('id', 'message_show_name_id');
    div_for_show_name.appendChild(label_for_show_name);
    div_for_show_name.appendChild(show_for_show_name);
    form.appendChild(div_for_show_name);

    // 密码
    var div_for_show_password = document.createElement("div");
    div_for_show_password.setAttribute('class', 'form-group');
    var label_for_show_password = document.createElement("label");
    label_for_show_password.setAttribute('for', 'resourceId');
    label_for_show_password.innerText = "密码";
    var show_for_show_password = document.createElement("input");
    show_for_show_password.setAttribute('type', 'password');
    show_for_show_password.setAttribute('class', 'form-control');
    show_for_show_password.setAttribute('id', 'message_show_password_id');
    div_for_show_password.appendChild(label_for_show_password);
    div_for_show_password.appendChild(show_for_show_password);
    form.appendChild(div_for_show_password);


    // 性别
    var div_for_show_sex = document.createElement("div");
    div_for_show_sex.setAttribute('class', 'form-group');
    var label_for_show_sex = document.createElement("label");
    label_for_show_sex.setAttribute('for', 'resourceId');
    label_for_show_sex.innerText = "性别";
    div_for_show_sex.appendChild(label_for_show_sex);
    var  select_sex = document.createElement("select");
    select_sex.setAttribute('id', "add_sex_select_id")
    select_sex.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;"
    var unKnown = document.createElement("option");
    unKnown.setAttribute('value', '0');
    unKnown.innerText = "秘密";
    var male = document.createElement("option");
    male.setAttribute('value', '1');
    male.innerText = "男性";
    var female = document.createElement("option");
    female.setAttribute('value', '2');
    female.innerText = "女性";
    select_sex.appendChild(unKnown);
    select_sex.appendChild(male);
    select_sex.appendChild(female)

    div_for_show_sex.appendChild(document.createElement("br"));
    div_for_show_sex.appendChild(select_sex);
    form.appendChild(div_for_show_sex)

    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        var createTime = CurrentTime();
        $.ajax({
            url: '/addEmployee?employeeName='+$('#message_show_name_id').val() +
            "&password="+$.md5($('#message_show_password_id').val())
            +"&sex="+$('#select_sex option:selected').val(),
            method: 'post',
            success: function (data) {
                if (data != 0) {
                    sweetAlert("新增成功")
                    var opt = {
                        url: '/getEmployees',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {
                    sweetAlert("新增失败");
                }
            }
        })
    }
}

/**
 * 分页
 */
function addPaginationEmployee() {
    var pagination = "<div class=\"row clearfix\">\n" +
        "\t\t<div class=\"col-md-12 column\">\n" +
        "\t\t\t<ul class=\"pagination\">\n" +
        "\t\t\t\t<li>\n" +
        "\t\t\t\t\t <a href=\"#\" onclick='employeePrePage()'>上一页</a>\n" +
        "\t\t\t\t</li>\n" +
        "<li>\n" +
        "\t\t\t\t\t <a href=\"#\" id='article_page_index'>第1页</a>\n" +
        "\t\t\t\t</li>\n" +
        "\t\t\t\t<li>\n" +
        "\t\t\t\t\t <a href=\"#\" onclick='employeeNextPage()'>下一页</a>\n" +
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
function employeePrePage() {
    if (window.articlePageIndex > 0) {
        window.articlePageIndex--;
        document.getElementById('article_page_index').innerText = "第" + window.articlePageIndex + "页";
        var opt = {
            url: '/getEmployees?pageIndex='+window.articlePageIndex+"&pageSize="+window.articlePageSize,
            silent: true,
        };
        $('#table').bootstrapTable('refresh', opt);
    }
}


/**
 * 下一页
 */
function employeeNextPage() {
    if (window.articlePageIndex > 0) {
        window.articlePageIndex++;
        document.getElementById('article_page_index').innerText = "第" + window.articlePageIndex + "页";
        var opt = {
            url: '/getEmployees?pageIndex='+window.articlePageIndex+"&pageSize="+window.articlePageSize,
            silent: true,
        };
        $('#table').bootstrapTable('refresh', opt);
    }
}


