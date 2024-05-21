-- 数据初始化sql文件
use `library_rbac_system`;

-- 添加测试数据 第一版使用 SpringSecurity自带的密码加密工具
-- 加密账号 admin, 密码：admin
insert into library_rbac_system.library_user (user_id, username, password, salt, nickname, email, gender, user_desc,
                                              user_status, create_time, update_time)
values (1, 'admin', '$2a$10$I5NVBUyI0IVdlkFffaJiHOEEaRWvGumBja35BGB/wh7drOKOAqNJi', null, null, 'admin@123.com', '无可奉告',
        '用户自述', '2', '2024-05-20 17:34:49', '2024-05-20 19:18:14');

-- 加盐加密的版本暂时没有更新

insert into library_role(role_name,role_code,role_desc) values('管理员','ADMIN','最高管理员');

select * from library_role;