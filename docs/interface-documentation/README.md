# 接口文档 #

## 后台登录 ##

1. /login 登录方法
  **前台参数:**
    - account
    - password

    **后台返回:**
    - 1 登录成功
    - 0 密码错误
    - -1 没有这个账号

    **后台设置session:**
    - account 记录用户登录的账号

    **具体内容：**
    通过account去数据库里查找相应的password，并且通过这个account寻找到其对应的角色roleId，再通过roleId获取相应的权限

## 后台管理 ##

 1. getModule 获取模块 
    **前台参数:**
    - 无

    **后台返回**：一个JSON数组，包含父模块基本信息和子模块基本信息
     
    ~~~
    [
        moduleId:  0,
        moduleName: "",
        status: 0,
        submodules:[
            {
                subModuleId: 0,
                subModuleName: "",
                status: 0,
                parentModuleId: 0,
            }
            ...
        ]
        ...
    ]    
    ~~~

    **后台实现**：通过获取session中的account账号找到对应的roleId，然后通过roleId获取所对应的权限对应的板块


