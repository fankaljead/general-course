# 数据库设计
## 数据库表
1. 栏目(Column)
- id				栏目ID
- name      		栏目名称
- level				栏目的级别
- parent			上级栏目的ID

2. 文章(article)
- id				文章的ID
- title     		文章的标题
- author			文章的作者
- columnId			栏目id
- content			文章的内容
- createTime		创建日期
- whetherTop		是否置顶(0:不置顶 1:置顶)
- status			文章状态(0:未审核 1: 审核通过 2:审核不通过)

3. 角色(role)
- id				角色ID
- name				角色名称						
- createTime		创建时间
- permissonId		角色拥有的权限


4. 权限(permission) 仅用于记录角色表和模块的连接
- id				权限ID
- roleId			角色ID
- moduleId			模块的ID
- createTime		创建时间
- description		描述模块


5. 父模块(module)
- id				模块ID
- name				模块名称
- status			模块状态(0:禁用 1:启用)

6. 子模块(sonModule)
- id				模块ID
- name				模块名称
- status			模块状态(0:禁用 1:启用)
- parentModuleID	父类模块ID


7. 人员(employee)
- id				员工ID
- name				员工姓名
- sex				员工性别
- account 			员工账号
- passWord			员工密码
- roleID			角色ID


8. 留言(message)
- id				留言ID
- content			留言内容
- createTime		留言时间
- reply				回复内容
- employeeId		回复人员ID
- replyTime			回复时间			
- status			回复状态(0:未回复 1:已回复)
- moduleId			留言模块的ID


9. 教育资源(file)
- id				教育资源ID
- articleId			与文章管理相关联
- path   			存放的路径