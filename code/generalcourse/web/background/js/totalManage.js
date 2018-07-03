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
        case 100001:
            messageModuleClick(100001);
            break;
        case 100006:
            employeeModuleClick(100006);
    }
}