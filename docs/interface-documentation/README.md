# 接口文档 #

## 前台页面 ##

1. getColumns 获取栏目信息  已完成

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
	
1. getSecondColumns 已完成

	**前台参数**
	- 无
	
	**后台返回**
	- columnId: 栏目id,
	- columnName: 栏目名称,
	- parentId: 父栏目id,
	- parentName: 父栏目名称
	

2. getResources 获取资源 包括文章和文件等资源(**后台管理也可以使用**) 已完成

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
	- fileId: 文件id
	- fileName: 文件名
    - columnName: 栏目名称

1. getResourceContent 获取资源详情（既可以获取文章也可以获取文件）(**后台管理也可以使用**) 已完成

    **前台参数**
    - resourceId: 资源id

    **后台返回JSON字符串**
	- resourceId: 资源id
    - content: 资源内容
    - path: 资源路径
    - title: 资源标题
    - createTime: 创建时间
    - whetherTop: 是否置顶
    - status: 资源状态
    - columnId: 栏目id
    - columnName: 栏目名称
	- fileName: 文件名

2. search 全站搜索 已完成

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

3. addMessage 新增留言 已完成

    **前台参数**
    - content: 留言内容
    - createTime:  创建时间
    - moduleId: 板块id

    **后台返回int**
    - result: 留言成功1，留言失败0

4. getMessages 获取留言(**后台管理也可以使用**) 已完成

    **前台参数**
    - pageIndex: 当前页码
    - pageSize: 每页多少个
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

1. login 登录方法 已完成

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

 1. getModule 获取模块 已完成
 
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

1. getAllSubModules 获取所有子模块

	**前台参数:**
    - 无
	
	**后台返回一个JSON数组，包含父模块基本信息和子模块基本信息**
     
    ~~~
    [
		{
			moduleId: 模块id，
			moduleName: 模块名字，
			parentModuleId: 父模块id，
			status： 是否被禁用
		}
        ...
    ]    
    ~~~

### 网站内容管理 ###

#### 留言管理 ####

1. deleteMessages 删除留言 已完成

    **前台参数**
    - messageIds: 需要删除留言的id
	~~~
	格式为:
	
	messageIds:
		[
			100000,1000001,...
		]
	
	~~~

    **后台返回int**
    - result: 成功1，失败0

1. replyMessage 回复留言  已完成

    **前台参数**
	-- messageId: 留言id
	- reply: 回答内容
	- replyTime: 回答时间
	- status: 留言状态
	
	**后台返回int**
    - result: 成功1，失败0
	

#### 文章管理和审核文章 ###

1. addResource 添加资源 已完成

    **前台参数**
    - title: 资源名称
    - employeeId: 上传资源的人的id
    - columnId: 栏目id
    - content: 资源内容
    - createTime: 创建时间
    - whetherTop: 是否置顶

    **后台返回int**
    - result: 成功1，失败0

1. uploadFile 上传文件 已完成

    **前台参数**
    - fileName: 文件名
    - file: 文件资源

    **后台返回int**
    - result: 成功1，失败0
	
1. downloadFile 下载文件 已完成

    **前台参数**
    - fileId: 文件id

    **后台返回int**
    - 无

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

       

    **后台返回int**
    - result: 成功1，失败0

1. deleteResources 删除资源 已完成

	 **前台参数**
     - resourceIds: 所选要删除资源的id
	 
	 **后台返回int**
	 - result: 成功1，失败0
	
#### 栏目管理 ####

1. updateColumn 更新栏目 已完成

    **前台参数**
    - columnId: 栏目id
    - parentId: 父栏目id
    - columnName: 栏目名称

    **后台返回int**
    - result: 成功1，失败0


### 系统设置 ###

#### 模块管理 ###

1. updateModule 更新模块  已完成

    **前台参数**
    - moduleId: 模块id
    - moduleName: 模块名字
    - status: 是否被禁用(0:禁用 1:启用)
    - parentModuleId: 父模块id

    **后台返回int**
    - result: 成功1，失败0

#### 角色分配 ####

1. assignRole 分配角色(一个用户只能分配一个角色) 已完成

    **前台参数**
    - employeeId: 被分配角色人员id
    - roleId: 分配的角色id

    **后台返回int**
    - result: 成功1，失败0


#### 人员管理 ####

1. addEmployee 新增用户 已完成

    **前台参数**
    - employeeName: 用户名字
    - sex: 用户性别
    - password: 密码 **传递密码应当使用$.md5(password)方法**

    **后台返回int**
    - result: 成功返回账号，失败0

	
1. employeeEmployee 更新用户 已完成

    **前台参数**
    - employeeName: 用户名字
    - sex: 用户性别
    - employeeId: 用户id

    **后台返回int**
    - result: 成功返回账号，失败0
	
1. deleteByEmployeeId 更新用户 已完成

    **前台参数**
    - employeeId: 用户id

    **后台返回int**
    - result: 成功返回账号，失败0

2. deleteEmployees 删除用户 已完成

    **前台参数**
    - employeeIds: 被删除用户的id(一个数组)
	
	~~~
	employeeIds:
		[
			{
				employeeId: 0,
			},
			{
				employeeId: 1,
			}
			...
		]
	~~~
	
    **后台返回int**
    - result: 成功1，失败0

3. getEmployees 获取人员 已完成

    **前台参数**
    - roleId: (0 只有角色id为0的才是没有被分配角色的， 可选)
    - pageIndex: 当前页 (可选)
    - pageSize: 页面大小 (可选)
    - condition: 查询条件 (可选)	

    **后台返回JSON数组**
    - roleId: 角色id
    - employeeName: 人员名称
    - sex: 人员性别
    - account: 人员的账号

#### 角色管理 ####

1. addRole 新增角色  已完成

    **前台参数**
    - roleName: 角色名字
    - createTime: 创建时间
    - description: 角色描述
    - ownModuleIds: 角色拥有的模块id(一个数组)
		~~~
		[100000, 100001 ,...]
		~~~

    **后台返回int**
    - result: 成功1，失败0

2. getRoles 获取角色 已完成

    **前台参数**
    - 无

    **后台返回JSON数组**
    - roleId: 角色id
    - roleName: 角色名称
    - createTime: 角色被创建时间
    - description: 角色的描述

1. updateRole 更新角色 已完成

    **前台参数**
    - roleId: 角色id
    - roleName: 角色名字
    - description: 角色描述
    - ownModuleIds: 角色拥有的模块id(一个数组)
		~~~
		[100000, 100001 ,...]
		~~~

    **后台返回int**
    - result: 成功1，失败0

1. getPermissionByRoleId 获取角色权限
	
	**前台参数**
	- roleId: 角色id
	
	**后台返回JSON数组**
	~~~
		[
			{
				moduleId: 板块id(subModule表)一个对应权限,
				moduleName: 模块名称,
				permissionId: 权限id,
				roleId: 角色id,
				roleName: 角色名称 
			} ...
		]
	~~~
	
	
1. deleteRoles 删除角色 已完成

    **前台参数**
    - roleIds: 被删除角色的id
		~~~
		[100000, 100001 ,...]
		~~~

    **后台返回int**
    - result: 成功1，失败0

2. getRoleByAccount 获取登录的单个角色信息 已完成

    **前台参数**
    - 无

    **后台返回JSON**
    - roleId: 角色id
    - roleName: 角色名称
    - createTime: 角色被创建时间
    - description: 角色的描述

#### 首页信息设置 ####

暂未开放


## 注意 ##

### 上传的时间格式都为 （2018-06-29 19:23:42） 这个类型 ###
### 执行lib/js/CurrentTime.js 中的 Current()方法即可 ###