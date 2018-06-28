# 接口文档 #

## 前台页面 ##

1. getColunms 获取栏目信息

    **前台参数**
    - level: （0表示父栏目，1表示子栏目，一共两层目录）
    - columnId: 栏目id（可选）

    **后台返回 JSON数组**
    - columnId: 栏目id
    - columnName: 栏目名称
    - level: 栏目级别
    - parentId: 父栏目id

    **使用**
    - 进入首页直接请level为0的栏目
    - 点击某个栏目传level为1和点击栏目的id

2. getResources 获取资源 包括文章和文件等资源(**后台管理也可以使用**)

    **前台参数**
    - columnId: 栏目id
    - title: 资源标题
    - startTime: 创建时间的范围开始
    - endTime: 创建时间的范围结束
    - pageIndex: 当前页
    - pageSize: 每页接收的大小

    **后台返回JSON数组**
    - resourceId: 资源id
    - title: 资源标题
    - createTime: 创建时间
    - whetherTop: 是否置顶
    - status: 资源状态
    - columnId: 栏目id
    - columnName: 栏目名称

1. getResourceContent 获取资源详情（既可以获取文章也可以获取文件）(**后台管理也可以使用**)

    **前台参数**
    - resourceId: 资源id

    **后台返回JSON数组**
    - content: 资源内容
    - path: 资源路径

2. search 全站搜索

    **前台参数**
    - keyWords: 关键字

    **后台返回JSON数组**
    - resourceId: 资源id
    - title: 资源标题
    - createTime: 创建时间
    - whetherTop: 是否置顶
    - employeeId: 作者
    - status: 文章状态
    - path: 资源路径

3. addMessage 新增留言

    **前台参数**
    - content: 留言内容
    - createTime:  创建时间
    - moduleId: 板块id

    **后台返回int**
    - result: 留言成功1，留言失败0

4. getMessages 获取留言(**后台管理也可以使用**)

    **前台参数**
    - page: 当前页码
    - pageIndex: 每页多少个
    - status 默认为1 (1为已经回复，0为未回复，2为全选，可选)

    **后台返回JSON数组**
    - 当前台参数status为 1 时
        - messageId: 留言id
        - content: 留言内容
        - createTime: 留言时间
        - reply: 回复内容
        - replyTime: 回复时间
        - moduleId: 板块id

    - 当前台参数status为 0 时
        - messageId: 留言id
        - content: 留言内容
        - createTime: 留言时间
        - moduleId: 板块id

    - 当前台参数status为 2 时
        - messageId: 留言id
        - content: 留言内容
        - createTime: 留言时间
        - reply: 回复内容
        - replyTime: 回复时间
        - moduleId: 板块id
        - status: 留言状态

## 后台登录 ##

1. login 登录方法

    **前台参数:**
    - account
    - password

    **后台返回int**
    - 1 登录成功
    - 0 密码错误
    - -1 没有这个账号

    **后台设置session:**
    - account 记录用户登录的账号

    **具体内容：**
    通过account去数据库里查找相应的password，并且通过这个account寻找到其对应的角色roleId，再通过roleId获取相应的权限

## 后台管理 ##


### 后台管理整体页面 ###

 1. getModule 获取模块
 
    **前台参数:**
    - 无

    **后台返回一个JSON数组，包含父模块基本信息和子模块基本信息**
     
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



### 网站内容管理 ###

#### 留言管理 ####

1. deleteMessages 删除留言

    **前台参数**
    - messageIds: 需要删除留言的id

    **后台返回int**
    - result: 成功1，失败0

1. replyMessage 回复留言

    **前台参数**

#### 文章管理和审核文章 ###

1. addResource 添加资源

    **前台参数**
    - title: 资源名称
    - employeeId: 上传资源的人的id
    - columnId: 栏目id
    - content: 资源内容
    - createTime: 创建时间
    - whetherTop: 是否置顶

    **后台返回int**
    - result: 成功1，失败0

2. updateResource 更新资源

    **前台参数**
    - type: 

        **0 修改**
        - resourceId: 资源id
        - title: 资源名称
        - employeeId: 更改人id
        - columnId: 栏目id
        - content: 内容
        - whetherTop: 是否置顶(0:不置顶 1:置顶)

        **1 审核**
        - status: 文章状态(0:未审核 1: 审核通过 2:审核不通过)
        - resourceId: 资源id

        **2 删除**
        - resourceIds: 所选要删除资源的id

    **后台返回int**
    - result: 成功1，失败0

