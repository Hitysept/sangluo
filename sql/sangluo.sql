/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : sangluo

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 30/12/2023 19:31:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `company_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公司id',
  `company_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司名称',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '0有效  1失效',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新人',
  `update_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('464939024591753216', 'sangluo', 0, 'sangluo', '1696494370500', 'sangluo', '1696494370499');

-- ----------------------------
-- Table structure for distributed_transactional
-- ----------------------------
DROP TABLE IF EXISTS `distributed_transactional`;
CREATE TABLE `distributed_transactional`  (
  `transactional_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '事务id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '事务内容，JSONAray格式',
  `operate_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '操作人',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate_number` int NULL DEFAULT 0 COMMENT '操作次数',
  PRIMARY KEY (`transactional_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '分布式事务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of distributed_transactional
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单id',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '菜单最上级是父节点：1',
  `menu_type` int NULL DEFAULT 1 COMMENT '1菜单   2按钮',
  `menu_code` int NULL DEFAULT 1 COMMENT '按钮编号',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '后端对应的接口',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '前端路由',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '前端组件路径',
  `menu_level` int NULL DEFAULT 1 COMMENT '菜单层级',
  `menu_level_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单层级编号',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('479496818410000384', '增加菜单', '485071769900814336', 2, 1, '/role/saveMenu', '/system/menu/saveMenu', 'roleMenu/saveMenu', 3, '3', 0);
INSERT INTO `sys_menu` VALUES ('480641006627328000', '系统', '1', 1, 1, '', '/system', '', 3, '3', 0);
INSERT INTO `sys_menu` VALUES ('484556944607154176', '权限管理', '480641006627328000', 1, 3, '/role/getRoleList', '/system/roleIndex', 'roleMenu/roleIndex', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('485071769900814336', '菜单管理', '480641006627328000', 1, 3, '', '/system/menu', 'roleMenu/menusIndex', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('485705077273792512', '权限设置', '484556944607154176', 2, 3, '/role/getRoleMenusMap', '/system/roleIndex/setRole', 'roleMenu/setRole', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('488197972547473408', '首页', '1', 1, NULL, '', '/index', '', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('488263805374173184', '新增角色', '484556944607154176', 2, NULL, '/role/saveRole', '/system/roleIndex/addRole', 'roleMenu/saveRole', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('488536750784909312', '保存权限', '484556944607154176', 2, NULL, '/role/saveRoleMenu', '/system/roleIndex/saveRole', '', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('489705938312368128', '编辑菜单', '485071769900814336', 2, NULL, '/role/editRole', '/system/roleIndex/editMenu', 'roleMenu/saveMenu', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES ('490720208118484992', '系统设置', '480641006627328000', 1, NULL, '', '/system/systemSet', '', 3, NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限id',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限名称',
  `company_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '工厂id',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '0有效  1失效',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新人',
  `update_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('465385883642761216', 'admin', '464939024591753216', 0, 'sangluo', '1696600910000', 'sangluo', '1696600909998');
INSERT INTO `sys_role` VALUES ('465402514062315520', 'users', '464939024591753217', 0, 'sangluo', '1696604875001', 'sangluo', '1696604875000');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '角色id',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '菜单id',
  `button_code` int NULL DEFAULT 1 COMMENT '按钮编号',
  `status` int NULL DEFAULT 1 COMMENT '是否有权 1为有权 0为无权',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '479496818410000384', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '480641006627328000', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '484556944607154176', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '485071769900814336', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '485705077273792512', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '488197972547473408', 1, 0);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '488263805374173184', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '488536750784909312', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '489705938312368128', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465385883642761216', '490720208118484992', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '479496818410000384', 1, 0);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '480641006627328000', 1, 0);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '484556944607154176', 1, 0);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '485071769900814336', 1, 0);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '485705077273792512', 1, 0);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '488197972547473408', 1, 1);
INSERT INTO `sys_role_menu` VALUES ('465402514062315520', '488263805374173184', 1, 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户id',
  `head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户头像',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '账号',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '昵称',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT 'token',
  `token_expires` datetime NULL DEFAULT NULL COMMENT 'token过期时间',
  `id_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '身份证号',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '密码',
  `company_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '工厂id',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '0有效  1失效',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新人',
  `update_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('422663729235234816', NULL, 'user422663729235234817', '小桑落', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI0MjI2NjM3MjkyMzUyMzQ4MTYiLCJyblN0ciI6IjVHTGxYbGJyNnBrUnA3c1F5aG9TRXk2dDdMeFJtQWFBIn0.jNiMgnQOh8I_vLlgI0f2VlEZYu8bRDd3jBsJD_6UocE', NULL, '', '', 'hitysept@foxmail.com', 'Eg5MA6yVWOqDQXkL9+QT7g==', '464939024591753216', 0, '', '1686415154650', '', '1703077864750');
INSERT INTO `user_info` VALUES ('422705830337581056', NULL, 'user422705830337581057', '小桑落2', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI0MjI3MDU4MzAzMzc1ODEwNTYiLCJyblN0ciI6IlpESEpZWFYwWU5NT1ZOakVqMFFFaFd0Y0FMTmR4MElzIn0.HD0DninRDvfApeNgicsc-LGNKIlUVfUIxFPKMV-8elM', NULL, '', '', 'test@foxmail.com', 'Eg5MA6yVWOqDQXkL9+QT7g==', '', 0, '', '1686425192337', '', '1686425289396');

SET FOREIGN_KEY_CHECKS = 1;
