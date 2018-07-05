/**
 * @create: 2018年07月03日
 * @time: 20:36
 * @project_name: generalcourse
 * @author 江礼军
 * @email: 2669981991@qq.com
 * @description: 留言管理
 **/

/**
 * 留言模块管理
 * @param moduleId
 */
function messageClick(moduleId) {
    console.log("moduleId:", moduleId);

    $('#content-main').empty();
    var head = document.createElement("h1");
    head.innerText = "留言管理";
    document.getElementById('content-main').appendChild(head);
    $('#content-main').append("<div id=\"form-info\" class=\"row\"></div>\n" +
        "            <!-- 展示具体表格 -->\n" +
        "            <div class=\"content-main-table\" id=\"content-main-table\">\n" +
        "                <table id=\"table\"></table>\n" +
        "            </div>");

    addManageTable({})
    addPaginationMessage();
}

/**
 * 添加表格 table
 * @param columnData
 */
function addManageTable(columnData) {
    // $('#table').empty();
    $('#table').bootstrapTable({
        url: '/getMessages',
        columns: [{
            field: 'messageId',
            title: '留言id'
        }, {
            field: 'content',
            title: '留言内容'
        }, {
            field: 'createTime',
            title: '留言时间'
        },{
            field: 'reply',
            title: '回复内容'
        },{
            field: 'replyTime',
            title: '回复时间'
        },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    console.log("row", row)
                    var b = "<span  data-toggle=\"modal\" onclick='replyMessage("+JSON.stringify(row)+")' data-target=\"#myModal\">\n" +
                        "\t回复\n" +
                        "</span>";
                    var c = '<span  class = "toWhite" \
                        onclick = "readyToDeleteMessage('+row.messageId+')"> 删除</span>';
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


function replyMessage(row) {
    console.log("employee row:", row)
    var form = document.createElement("form");
    // $('#myModalLabel').empty();
    $('#myModalLabelTitle').text("回复留言");

    var modal_body = document.getElementById('modal-body');
    $('#modal-body').empty();

    // 留言
    var div_for_show_content= document.createElement("div");
    div_for_show_content.setAttribute('class', 'form-group');
    var label_for_show_content= document.createElement("label");
    label_for_show_content.innerText = "留言内容";
    var show_for_show_content= document.createElement("textarea");
    // show_for_show_content.setAttribute('type', 'textAres');
    show_for_show_content.setAttribute('class', 'form-control');
    show_for_show_content.setAttribute('id', 'message_show_content_id');
    show_for_show_content.setAttribute('disabled', 'true');
    show_for_show_content.style = "height: 200px;"
    div_for_show_content.appendChild(label_for_show_content);
    div_for_show_content.appendChild(show_for_show_content);
    form.appendChild(div_for_show_content);
    // $('#message_show_name').val(row.employeeName+"");
    show_for_show_content.value = row.content;

    // 回复留言
    var div_for_show_reply= document.createElement("div");
    div_for_show_reply.setAttribute('class', 'form-group');
    var label_for_show_reply= document.createElement("label");
    label_for_show_reply.innerText = "回复留言";
    var show_for_show_reply= document.createElement("textarea");
    // show_for_show_content.setAttribute('type', 'textAres');
    show_for_show_reply.setAttribute('class', 'form-control');
    show_for_show_reply.setAttribute('id', 'message_show_reply_id');
    show_for_show_reply.style = "height: 200px;"
    div_for_show_reply.appendChild(label_for_show_reply);
    div_for_show_reply.appendChild(show_for_show_reply);
    form.appendChild(div_for_show_reply);
    // $('#message_show_name').val(row.employeeName+"");

    form.appendChild(div_for_show_reply);
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
                    sweetAlert("回复成功！");
                    var opt = {
                        url: '/getMessages',
                        silent: true,
                    };
                    $('#table').bootstrapTable('refresh',opt);
                } else {
                    sweetAlert("回复失败");
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

