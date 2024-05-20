-- 图书馆rbac系统数据库初始化文件

-- 如果存在 library_rbac_system 数据库先删除
drop database if exists library_rbac_system;
-- 创建数据库
create database if not exists library_rbac_system;
-- 使用数据库
use library_rbac_system;

-- 创建角色表
drop table if exists library_role;

create table if not exists library_role
(
    role_id     int primary key auto_increment comment '角色唯一标识',
    role_name   varchar(50) comment '角色名称',
    role_code   varchar(50) comment '角色编码',
    role_desc   varchar(50) comment '角色描述',
    role_status varchar(5) default '0' comment '角色状态 0 正常(默认状态) 1 停用 2删除',
    create_time datetime   DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) comment '图书馆角色表';

-- 创建权限表
drop table if exists library_permission;

create table if not exists library_permission
(
    permission_id   int primary key auto_increment comment '主键唯一标识',
    permission_name varchar(50) comment '权限名称',
    permission_code varchar(50) comment '权限编码',
    permission_desc varchar(50) comment '权限描述',
    create_time     datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_time     datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) comment '图书馆权限表';

-- 创建用户表
drop table if exists library_user;

create table if not exists library_user
(
    user_id     int primary key auto_increment comment '用户唯一标识',
    username    varchar(50) comment '用户名',
    password    varchar(255) comment '密码(加密处理)',
    salt        varchar(255) comment '密码盐',
    nickname    varchar(30) comment '昵称',
    email       varchar(255) comment '邮箱',
    gender      varchar(25)  default '无可奉告' comment '性别',
    user_desc   varchar(255) comment '用户自述',
    user_status varchar(5)   default '0' comment '用户状态 0 正常(默认) 1 停用 2 删除',
    create_time datetime     DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) comment '用户表';

-- alter table library_user change user_desc user_desc varchar(255) comment '用户自述';
show create table library_user;

-- 角色权限表
drop table if exists library_role_permission;

create table if not exists library_role_permission
(
    role_permission_id int primary key auto_increment comment '角色权限分配唯一标识',
    role_id            int comment '角色id',
    permission_id      int comment '权限id'
) comment '图书馆角色权限表';

-- 用户角色表
drop table if exists library_user_role;

create table if not exists library_user_role
(
    user_role_id int primary key auto_increment comment '用户角色分配唯一标识',
    user_id      int comment '用户id',
    role_id      int comment '角色id'
) comment '图书馆角色权限表';
