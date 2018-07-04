/**
 * @create: 2018年07月02日
 * @time: 11:19
 * @project_name: generalcourse
 * @author 周翔辉
 * @email: 728678732@qq.com
 * @description: 模块管理处理
 **/

/**
 * 模块点击事件
 * @param moduleId 模块id
 * @param ev 事件
 */
function moduleClick(module) {
    console.log("moduleId:", module)
    switch (module.subModuleId) {
        case 100000:
            messageClick(100000);
            break;
        case 100001:
            messageModuleClick(100001);
            break;
<<<<<<< HEAD
        case 100003:
            columnClick(100003);
=======
        case 100004:
            moduleManage(100004);
            break;
        case 100005:
            roleDistribute(100005);
>>>>>>> b494b3a44e5fe9a302ad508aee8110e37aa9f836
            break;
        case 100006:
            employeeModuleClick(100006);
            break;
<<<<<<< HEAD
=======
        case 100007:
            roleManagement(100007);
            break;
>>>>>>> b494b3a44e5fe9a302ad508aee8110e37aa9f836
    }
}

