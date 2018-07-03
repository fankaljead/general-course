function roleManagement(moduleId) {
     var main = $("#content-main");

    //初始化content-main
    main.empty();

    var h1 = $("<h1>角色管理</h1>");
    h1.appendTo(main);

    var div_for_input_add = document.createElement("div");
    div_for_input_add.setAttribute('class', 'col-lg-1');
    var input_for_input_add = document.createElement("input");
    input_for_input_add.setAttribute('data-toggle', 'modal');
    input_for_input_add.setAttribute('data-target', '#myModal');
    input_for_input_add.setAttribute('type', 'button');
    input_for_input_add.setAttribute('value', '新增');
    input_for_input_add.setAttribute('class', 'form-control');
    input_for_input_add.setAttribute('id', 'message_input_add_id');
    div_for_input_add.style = "padding-top: 25px;backgroud: #2AABD2;"
    div_for_input_add.appendChild(input_for_input_add);
    div_for_input_add.appendTo(main);
}