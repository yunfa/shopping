CREATE TABLE u_permission (
  id int NOT NULL identity(1,1),
  url varchar(256) DEFAULT NULL,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (id)
);
SET IDENTITY_INSERT u_permission ON;
insert  into u_permission(id,url,name) values (4,'/permission/index.shtml','权限列表'),(6,'/permission/addPermission.shtml','权限添加'),(7,'/permission/deletePermissionById.shtml','权限删除'),(8,'/member/list.shtml','用户列表'),(9,'/member/online.shtml','在线用户'),(10,'/member/changeSessionStatus.shtml','用户Session踢出'),(11,'/member/forbidUserById.shtml','用户激活&禁止'),(12,'/member/deleteUserById.shtml','用户删除'),(13,'/permission/addPermission2Role.shtml','权限分配'),(14,'/role/clearRoleByUserIds.shtml','用户角色分配清空'),(15,'/role/addRole2User.shtml','角色分配保存'),(16,'/role/deleteRoleById.shtml','角色列表删除'),(17,'/role/addRole.shtml','角色列表添加'),(18,'/role/index.shtml','角色列表'),(19,'/permission/allocation.shtml','权限分配'),(20,'/role/allocation.shtml','角色分配');

CREATE TABLE u_role (
  id int NOT NULL identity(1,1),
  name varchar(32) DEFAULT NULL,
  type varchar(10) DEFAULT NULL,
  PRIMARY KEY (id)
);
SET IDENTITY_INSERT u_role ON;
insert  into u_role(id,name,type) values (1,'系统管理员','888888'),(3,'权限角色','100003'),(4,'用户中心','100002');

CREATE TABLE u_role_permission (
  rid int DEFAULT NULL,
  pid int DEFAULT NULL
);
insert  into u_role_permission(rid,pid) values (4,8),(4,9),(4,10),(4,11),(4,12),(3,4),(3,6),(3,7),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(1,4),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20);

CREATE TABLE u_user (
  id int NOT NULL identity(1,1),
  nickname varchar(20) DEFAULT NULL,
  email varchar(128) DEFAULT NULL,
  pswd varchar(32) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  last_login_time datetime DEFAULT NULL,
  status int DEFAULT '1',
  PRIMARY KEY (id)
);
insert  into u_user(id,nickname,email,pswd,create_time,last_login_time,status) values (1,'管理员','admin','57dd03ed397eabaeaa395eb740b770fd','2016-06-16 11:15:33','2017-02-04 08:55:11',1),(11,'soso','8446666@qq.com','d57ffbe486910dd5b26d0167d034f9ad','2016-05-26 20:50:54','2016-06-16 11:24:35',1),(12,'8446666','8446666','4afdc875a67a55528c224ce088be2ab8','2016-05-27 22:34:19','2016-06-15 17:03:16',1);

CREATE TABLE u_user_role (
  uid int DEFAULT NULL,
  rid int DEFAULT NULL
);
insert  into u_user_role(uid,rid) values (12,4),(11,3),(11,4),(1,1);
