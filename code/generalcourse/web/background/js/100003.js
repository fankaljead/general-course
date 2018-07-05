/**
 * @create: 2018年07月03日
 * @time: 20:36
 * @project_name: generalcourse
 * @author 江礼军
 * @email: 2669981991@qq.com
 * @description: 栏目管理
 **/

/**
 * 栏目模块管理
 * @param moduleId
 */
function columnClick(moduleId) {
    console.log("moduleId:", moduleId);

    $('#content-main').empty();
    var head = document.createElement("h1");
    head.innerText = "栏目管理";
    document.getElementById('content-main').appendChild(head);
    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addColumnTable({})
    newColumn();
}

/**
 * 添加表格 table
 * @param columnData
 */
function addColumnTable(columnData) {
    // $('#table').empty();
    $('#table').bootstrapTable({
        url: '/getSecondColumns?level=1',
        columns: [{
            field: 'columnId',
            title: '栏目id'
        }, {
            field: 'columnName',
            title: '栏目名称'
        }, {
            field: 'parentId',
            title: '父栏目id'
        },{
            field: 'parentName',
            title: '父栏目名称'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='changeColumn("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t修改\n" +
                        "</span>";
                    var c = '<span  class = "toWhite" \
                        onclick = "readyToDeleteColumnId('+row.columnId+')"> 删除</span>';

                    return b+c;
                }
            }
        ],

        search: true,
        pagination:true,
        pageNumber:1,
        pageSize:5,
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


function changeColumn(row) {
    console.log("employee row:", row)
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("修改栏目");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 修改
    var div_for_show_changeColumn = document.createElement("div");
    div_for_show_changeColumn.setAttribute('class', 'form-group');
    var label_for_show_changeColumn = document.createElement("label");
    label_for_show_changeColumn.setAttribute('for', 'resourceId');
    label_for_show_changeColumn.innerText = "栏目名称";
    var show_for_show_changeColumn = document.createElement("input");
    show_for_show_changeColumn.setAttribute('type', 'text');
    show_for_show_changeColumn.setAttribute('class', 'form-control');
    // show_for_show_title.setAttribute('disabled', 'true');
    show_for_show_changeColumn.setAttribute('id', 'column_show_columnName_id');
    div_for_show_changeColumn.appendChild(label_for_show_changeColumn);
    div_for_show_changeColumn.appendChild(show_for_show_changeColumn);
    form.appendChild(div_for_show_changeColumn);
    show_for_show_changeColumn.value = row.columnName;

    //父栏目
    var div_for_show_parentId= document.createElement("div");
    div_for_show_parentId.setAttribute('class', 'form-group');
    var label_for_show_parentId= document.createElement("label");
    label_for_show_parentId.innerText = "选择所属父栏目";
    var  select_column = document.createElement("select");
    select_column.setAttribute('id', "add_column_parentName_id")
    select_column.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    $.ajax({
        url: '/getColumns?level=0',
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var option_column = document.createElement("option");
                option_column.setAttribute('value', data[i].columnId);
                option_column.innerText = data[i].columnName;
                if(data[i].parentName == row.parentName) {
                    option_column.setAttribute('selected', 'selected');
                }
                select_column.appendChild(option_column);
            }
        }
    })
    div_for_show_parentId.appendChild(label_for_show_parentId);
    div_for_show_parentId.appendChild(document.createElement("br"));
    div_for_show_parentId.appendChild(select_column);
    form.appendChild(div_for_show_parentId);


    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        $.ajax({
            url: '/updateColumn?columnName='+$('#column_show_columnName_id').val()
            +"&parentId="+$('#add_column_parentName_id option:selected').val()+"&columnId="+row.columnId,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    sweetAlert("修改成功！");
                    var opt = {
                        url: '/getSecondColumns',
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


function readyToDeleteColumnId(row) {
    swal({
        title: "您确定要删除吗？",
        text: "您确定要删除这条数据？",
        type: "warning",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: 'slide-from-top',
        confirmButtonText: "是的，我要删除",
        confirmButtonColor: "#ec6c62"
    },function () {
        var ids = [];

        $.ajax({
            url: '/deleteByColumnId?columnId='+row,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    var opt = {
                        url: '/getSecondColumns',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                }
            }
        })
        swal.close()
    });
}



function newColumn(row) {

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
        addColumn(row)
    })

    form_info.appendChild(form);
}

/**
 * 新增栏目
 * @param ev
 */
function addColumn(row) {

    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("新增栏目");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    var div_for_show_addColumn = document.createElement("div");
    div_for_show_addColumn.setAttribute('class', 'form-group');
    var label_for_show_addColumn = document.createElement("label");
    label_for_show_addColumn.setAttribute('for', 'resourceId');
    label_for_show_addColumn.innerText = "栏目名称";
    var show_for_show_addColumn = document.createElement("input");
    show_for_show_addColumn.setAttribute('type', 'text');
    show_for_show_addColumn.setAttribute('class', 'form-control');
    // show_for_show_title.setAttribute('disabled', 'true');
    show_for_show_addColumn.setAttribute('id', 'column_show_columnName_id');
    div_for_show_addColumn.appendChild(label_for_show_addColumn);
    div_for_show_addColumn.appendChild(show_for_show_addColumn);
    form.appendChild(div_for_show_addColumn);

    //父栏目ID
    var div_for_show_newParentId= document.createElement("div");
    div_for_show_newParentId.setAttribute('class', 'form-group');
    var label_for_show_newParentId= document.createElement("label");
    label_for_show_newParentId.innerText = "选择所属父栏目";
    var  select_column = document.createElement("select");
    select_column.setAttribute('id', "add_column_new_parentName_id")
    select_column.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    $.ajax({
        url: '/getColumns?level=0',
        method: 'post',
        success: function (data) {
            data = JSON.parse(data);
            for (var j = 0; j < data.length; j++) {
                var option_column = document.createElement("option");
                option_column.setAttribute('value', data[j].columnId);
                option_column.innerText = data[j].columnName;
                // if(data[j].parentName == row.parentName) {
                //     option_column.setAttribute('selected', 'selected');
                // }
                select_column.appendChild(option_column);
            }
        }
    })
    div_for_show_newParentId.appendChild(label_for_show_newParentId);
    div_for_show_newParentId.appendChild(document.createElement("br"));
    div_for_show_newParentId.appendChild(select_column);
    form.appendChild(div_for_show_newParentId);


    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        var createTime = CurrentTime();
        $.ajax({
            url: '/addColumn?columnName='+$('#column_show_columnName_id').val() +"&parentId="+$('#add_column_new_parentName_id option:selected').val(),

            method: 'post',
            success: function (data) {
                if (data != 0) {
                    sweetAlert("新增成功")
                    var opt = {
                        url: '/getSecondColumns',
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
