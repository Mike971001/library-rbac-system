# 图书馆 RBAC管理系统

## 一、系统描述

> 1、**角色**
>
> - 管理员：拥有对图书馆系统的所有管理权限，包括管理图书、管理用户、生成报告等。
> - 图书管理员：负责图书的借阅、归还和管理库存等操作。
> - 读者：用于基本的权限，可以搜索图书、借阅图书和归还图书。
>
> 2、**权限**
>
> - 管理员权限
>   - 添加、编辑、删除图书信息
>   - 管理用户账户：创建、编辑、删除用户账户
>   - 生成报告：例如节约统计、库存报告等
> - 图书管理员权限
>   - 借阅图书
>   - 归还图书
>   - 查看图书库存
> - 读者权限
>   - 搜索图书
>   - 借阅图书
>   - 归还图书
>
> 3、**用户**
>
> - 系统管理员
> - 图书管理员
> - 读者
>
> 4、**访问策略控制**
>
> - 只有管理员可以访问管理员控制台，进行图书和用户的管理。
> - 图书管理员可以访问借阅、归还和库存管理等功能。
> - 读者可以搜索图书、借阅图书和归还图书。
>
> 5、**实施RBAC系统**：
>
> - 使用RBAC框架或库，根据上述设计实施RBAC系统。
>
> 6、**管理和维护**：
>
> - 管理员可以随时添加、编辑或删除用户和角色，以及修改权限。
> - 系统管理员负责监督系统的运行，并确保RBAC系统的有效性和安全性。
>
> 7、**监控和审计**：
>
> - 系统管理员可以监控用户对系统的访问和操作，并进行审计以确保系统安全性。



## 二、数据库设计

> **1、角色表(library_role)**
>
> | 列名        | 数据类型     | 说明                                |
> | ----------- | ------------ | ----------------------------------- |
> | role_id     | INT          | 角色唯一标识                        |
> | role_name   | VARCHAR(50)  | 角色名称                            |
> | role_code   | VARCHAR(50)  | 角色编码                            |
> | role_desc   | VARCHAR(255) | 角色描述                            |
> | role_status | VARCHAR(5)   | 角色状态，0正常（默认） 1停用 2删除 |
> | create_time | datetime     | 创建时间                            |
> | update_time | datetime     | 修改时间                            |
>
> 数据库表设计
>
> ```sql
> drop table if exists library_role;
> 
> create table if not exists library_role(
>     role_id int primary key auto_increment comment '角色唯一标识',
>     role_name varchar(50) comment '角色名称',
>     role_code varchar(50) comment '角色编码',
>     role_desc varchar(50) comment '角色描述',
>     role_status varchar(5) default '0' comment '角色状态 0 正常(默认状态) 1 停用 2删除',
>     create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
>     update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
> )comment '图书馆角色表';
> ```
>
> 
>
> **2、权限表(library_permission)**
>
> | 列名            | 数据类型     | 说明        |
> | --------------- | ------------ | ----------- |
> | permission_id   | INT          | 权限ID 主键 |
> | permission_name | VARCHAR(50)  | 权限名称    |
> | permission_code | VARCHAR(70)  | 权限字符    |
> | permission_desc | VARCHAR(255) | 权限描述    |
> | create_time     | datetime     | 创建时间    |
> | update_time     | datetime     | 修改时间    |
>
> ```sql
> drop table if exists library_permission;
> 
> create table if not exists library_permission(
>     permission_id int primary key auto_increment comment '主键唯一标识',
>     permission_name varchar(50) comment '权限名称',
>     permission_code varchar(50) comment '权限编码',
>     permission_desc varchar(50) comment '权限描述',
>     create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
>     update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
> )comment '图书馆权限表';
> ```
>
> 
>
> **3、用户表(library-user)**
>
> | 列名        | 数据类型     | 描述                               |
> | ----------- | ------------ | ---------------------------------- |
> | user_id     | INT          | 用户ID，主键                       |
> | username    | VARCHAR(50)  | 用户名                             |
> | password    | VARCHAR(255) | 密码(加密存储)                     |
> | salt        | VARCHAR(255) | 密码盐                             |
> | mobile      | VARCHAR(20)  | 手机号                             |
> | nickname    | VARCHAR(30)  | 昵称                               |
> | email       | VARCHAR(255) | 邮箱                               |
> | birthday    | datetime     | 生日                               |
> | gender      | VARCHAR(25)  | 性别(默认无可奉告)                 |
> | user_desc   | VARCHAR(255) | 用户自述                           |
> | user_status | VARCHAR(5)   | 用户状态 0 正常(默认) 1 停用 2删除 |
> | create_time | datetime     | 创建时间                           |
> | update_time | datetime     | 更新时间                           |
>
> ```sql
> drop table if exists library_user;
> 
> create table if not exists library_user(
>     user_id int primary key auto_increment comment '用户唯一标识',
>     username varchar(50) comment '用户名',
>     password varchar(255) comment '密码(加密处理)',
>     salt varchar(255) comment '密码盐',
>     nickname varchar(30) comment '昵称',
>     email varchar(255) comment '邮箱',
>     gender varchar(25) default '无可奉告' comment '性别',
>     user_desc varchar(255) default '用户自述',
>     user_status varchar(5) default '0' comment '用户状态 0 正常(默认) 1 停用 2 删除',
>     create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
>     update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
> )comment '用户表';
> ```
>
> 
>
> **4、角色权限分配表（library_role_permission）**
>
> | 列名               | 数据类型 | 说明                 |
> | ------------------ | -------- | -------------------- |
> | role_permission_id | INT      | 角色权限分配ID，主键 |
> | role_id            | INT      | 关联角色表           |
> | permission_id      | INT      | 关联用户表           |
>
> ```sql
> drop table if exists library_role_permission;
> 
> create table if not exists library_role_permission(
>     role_permission_id int primary key auto_increment comment '角色权限分配唯一标识',
>     role_id int comment '角色id',
>     permission_id int comment '权限id'
> )comment '图书馆角色权限表';
> ```
>
> 
>
> **5、用户角色分配表（library_user_role）**
>
> | 列名         | 数据类型 | 说明                 |
> | ------------ | -------- | -------------------- |
> | user_role_id | INT      | 用户角色分配ID，主键 |
> | user_id      | INT      | 用户ID               |
> | role_id      | INT      | 角色ID               |
>
> ```sql
> drop table if exists library_user_role;
> 
> create table if not exists library_user_role(
>     user_role_id int primary key auto_increment comment '用户角色分配唯一标识',
>     user_id int comment '用户id',
>     role_id int comment '角色id'
> )comment '图书馆角色权限表';
> ```
>
> 



## 三、功能实现

> **1、配置SpringBoot项目**
>
> - 创建一个新的SpringBoot项目，并添加依赖项目：Spring Security、Spring Web、MyBatis、MySQL驱动和Druid连接池。
>
> **2、配置Spring Security和Jwt认证**
>
> - 配置Spring Security来定义安全策略和过滤器链，并启用基于JWT的认证。
> - 创建JWT工具类，用于生成和验证JWT token。
>
> **3、配置数据库和MyBatis：**
>
> - 创建数据库，并设计相应的表格（角色表、权限表、用户表、角色权限分配表、用户角色分配表）。
> - 使用MyBatis配置数据访问层，编写Mapper接口和Mapper XML文件。
>
> **4、编写实体类**
>
> - 创建角色、权限、用户等实体类，以便与数据库表对应。
>
> **5、编写服务层**
>
> - 创建服务层接口和实现类，实现相应的业务逻辑，例如用户认证、角色分配等。
>
> **6、编写控制器**
>
> - 创建RestFul API控制器，处理HTTP请求，并调用服务层方法。
>
> **7、配置Druid连接池**
>
> - 配置Druid数据源，用于连接MySQL数据库。
>
> **8、配置Jwt过滤器**
>
> - 创建Jwt过滤器，用于拦截Http请求并验证JwtToken
>
> **9、测试**
>
> - 编写单元测试和集成测试，并保证所有功能正常运行。

## 四、具体代码功能实现




