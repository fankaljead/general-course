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
function examineClick(moduleId) {
    console.log("moduleId:", moduleId);

    $('#content-main').empty();
    var head = document.createElement("h1");
    head.innerText = "审核管理";
    document.getElementById('content-main').appendChild(head);
    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addExamineTable({})
}

/**
 * 添加表格 table
 * @param columnData
 */
function addExamineTable(columnData) {
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
            field: 'status',
            title: '审核状态'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='examine("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t审核\n" +
                        "</span>";

                    return b;
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


function examine(row) {
    console.log("employee row:", row)
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("修改资源");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 修改
    var div_for_show_examineColumn = document.createElement("div");
    div_for_show_examineColumn.setAttribute('class', 'form-group');
    var label_for_show_examineColumn = document.createElement("label");
    label_for_show_examineColumn.setAttribute('for', 'resourceId');
    label_for_show_examineColumn.innerText = "资源标题";
    var show_for_show_examineColumn = document.createElement("input");
    show_for_show_examineColumn.setAttribute('type', 'text');
    show_for_show_examineColumn.setAttribute('disabled', 'true');
    show_for_show_examineColumn.setAttribute('class', 'form-control');
    // show_for_show_title.setAttribute('disabled', 'true');
    show_for_show_examineColumn.setAttribute('id', 'column_show_columnName_id');
    div_for_show_examineColumn.appendChild(label_for_show_examineColumn);
    div_for_show_examineColumn.appendChild(show_for_show_examineColumn);
    form.appendChild(div_for_show_examineColumn);
    show_for_show_examineColumn.value = row.title;

    //父栏目
    var div_for_show_examine= document.createElement("div");
    div_for_show_examine.setAttribute('class', 'form-group');
    var label_for_show_examine= document.createElement("label");
    label_for_show_examine.innerText = "审核状态";
    var  select_column = document.createElement("select");
    select_column.setAttribute('id', "add_column_parentName_id")
    select_column.style = "width: 568px;\n" +
        "    height: 38px;    border: 1px solid #ccc;\n" +
        "    border-radius: 4px;";
    var option_column_un = document.createElement("option");
    option_column_un.setAttribute('value', row.status);
    option_column_un.innerText = "未审核";

    var option_column_pass = document.createElement("option");
    option_column_pass.setAttribute('value', 1);
    option_column_pass.innerText = "审核通过";

    var option_column_unpass = document.createElement("option");
    option_column_unpass.setAttribute('value', 2);
    option_column_unpass.innerText = "审核不通过";

    select_column.appendChild(option_column_un);
    select_column.appendChild(option_column_pass);
    select_column.appendChild(option_column_unpass);

    $('#add_column_parentName_id option:selected').val(row.status)
    // if (row.status == 0) {
    //     option_column_un.innerText = data[i].columnName;
    // }
    // $.ajax({
    //     url: '/getColumns?level=0',
    //     method: 'post',
    //     success: function (data) {
    //         data = JSON.parse(data);
    //         for (var i = 0; i < data.length; i++) {
    //             var option_column = document.createElement("option");
    //             option_column.setAttribute('value', data[i].columnId);
    //             option_column.innerText = data[i].columnName;
    //             if(data[i].parentName == row.parentName) {
    //                 option_column.setAttribute('selected', 'selected');
    //             }
    //             select_column.appendChild(option_column);
    //         }
    //     }
    // })
    div_for_show_examine.appendChild(label_for_show_examine);
    div_for_show_examine.appendChild(document.createElement("br"));
    div_for_show_examine.appendChild(select_column);
    form.appendChild(div_for_show_examine);


    modal_body.appendChild(form);

    // 确认修改
    var modal_submit_id = document.getElementById('modal_submit_id');

    modal_submit_id.onclick = function (ev1) {
        $.ajax({
            url: '/updateResource?type=1'
            +"&status="+$('#add_column_parentName_id option:selected').val()+"&resourceId="+row.resourceId,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    sweetAlert("修改成功！");
                    var opt = {
                        url: '/getResources',
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
