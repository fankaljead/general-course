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
    addPaginationColumn();
}

/**
 * 添加表格 table
 * @param columnData
 */
function addColumnTable(columnData) {
    // $('#table').empty();
    $('#table').bootstrapTable({
        url: '/getColumns?level=1',
        columns: [{
            field: 'columnId',
            title: '栏目id'
        }, {
            field: 'columnName',
            title: '栏目名称'
        }, {
            field: 'level',
            title: '栏目级别'
        },{
            field: 'parentId',
            title: '父栏目'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='changeColumn("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t修改\n" +
                        "</span>";
                    var c = '<span  class = "toWhite" \
                        onclick = "readyToDeleteColumn('+row.columnId+')"> 删除</span>';
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
    show_for_show_changeColumn.setAttribute('id', 'message_show_title_id');
    div_for_show_changeColumn.appendChild(label_for_show_changeColumn);
    div_for_show_changeColumn.appendChild(show_for_show_changeColumn);
    form.appendChild(div_for_show_changeColumn);
    show_for_show_changeColumn.value = row.columnName;

    //父栏目ID
    var div_for_show_parentId= document.createElement("div");
    div_for_show_parentId.setAttribute('class', 'form-group');
    var label_for_show_parentId= document.createElement("label");
    label_for_show_parentId.innerText = "父栏目Id";
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
                option_column.innerText = data[i].parentId;
                if(data[i].columnId == rowId.columnId) {
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
            url: '/replyMessage?reply='+$('#message_show_reply_id').val()
            +"&replyTime="+CurrentTime()
            +"&status=1&messageId="+row.messageId,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    alert("回复成功！");
                    var opt = {
                        url: '/getMessages',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {
                    alert("回复失败");
                }
            }
        })
    }
}

function readyToDeleteMessage(row) {
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
        var ids = '['+row+']';

        console.log("delete id", row)
        $.ajax({
            url: '/deleteMessages?messageIds='+ids,
            method: 'post',
            success: function (data) {
                if (data == 1) {
                    var opt = {
                        url: '/getMessages',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {

                }
            }
        })
        swal.close()
    });
}


/**
 * 分页
 */
function addPaginationMessage() {
    var pagination = "<div class=\"row clearfix\">\n" +
        "\t\t<div class=\"col-md-12 column\">\n" +
        "\t\t\t<ul class=\"pagination\">\n" +
        "\t\t\t\t<li>\n" +
        "\t\t\t\t\t <a href=\"#\" onclick='messagePrePage()'>上一页</a>\n" +
        "\t\t\t\t</li>\n" +
        "<li>\n" +
        "\t\t\t\t\t <a href=\"#\" id='article_page_index'>第1页</a>\n" +
        "\t\t\t\t</li>\n" +
        "\t\t\t\t<li>\n" +
        "\t\t\t\t\t <a href=\"#\" onclick='messageNextPage()'>下一页</a>\n" +
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
function messagePrePage() {
    if (window.articlePageIndex > 0) {
        window.articlePageIndex--;
        document.getElementById('article_page_index').innerText = "第" + window.articlePageIndex + "页";
        var opt = {
            url: '/getMessages?pageIndex='+window.articlePageIndex+"&pageSize="+window.articlePageSize,
            silent: true,
        };
        $('#table').bootstrapTable('refresh', opt);
    }
}


/**
 * 下一页
 */
function messageNextPage() {
    if (window.articlePageIndex > 0) {
        window.articlePageIndex++;
        document.getElementById('article_page_index').innerText = "第" + window.articlePageIndex + "页";
        var opt = {
            url: '/getMessages?pageIndex='+window.articlePageIndex+"&pageSize="+window.articlePageSize,
            silent: true,
        };
        $('#table').bootstrapTable('refresh', opt);
    }
}

