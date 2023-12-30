/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 30/12/2023 19:31:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (122, 'oauthserver.yml', 'dev', 'server:\n  # 服务器的HTTP端口，默认为80\n  port: 48082\n\nspring:\n  application:\n    name: sangluoOauth\n  http:\n    encoding:\n      charset: UTF-8\n      enabled: true\n      force: true\n  jackson:\n    time-zone: GMT+8\n    date-format: yyyy-MM-dd HH:mm:ss\n    default-property-inclusion: non_null\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: satoken\n  # token有效期，单位秒，-1代表永不过期\n  timeout: 2592000\n  # token临时有效期 (指定时间内无操作就视为token过期)，单位秒\n  activity-timeout: -1\n  # 是否允许同一账号并发登录 (为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为false时每次登录新建一个token)\n  is-share: true\n  # token风格\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: false\n  # 是否从cookie中读取token\n  is-read-cookie: false\n  # 是否从head中读取token\n  jwt-secret-key: sangluojwtaaa88888', '53b1f27618e101fa385eea9200bd84fb', '2023-11-17 08:59:09', '2023-11-17 08:59:09', NULL, '192.168.3.11', 'sangluo', 'sangluo-dev', '', NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (123, 'gateway.yml', 'dev', 'server:\n  port: 48080\n  servlet:\n    session:\n      cookie:\n        # 需要更换存放sessionId的cookie名字，否则认证服务和客户端的sessionId会相互覆盖\n        name: JSESSIONID-2\nlogging:\n  level:\n    cn.itcast: debug\n  pattern:\n    dateformat: MM-dd HH:mm:ss:SSS\nspring:\n  application:\n    name: gateway\n  redis:\n    host: localhost\n    password: \n    port: 6379\n    lettuce:\n      pool:\n        max-active: 8 #连接池最大连接数（使用负值表示没有限制）\n        max-idle: 8 #连接池中的最大空闲连接\n        min-idle: 0 #连接池中的最小空闲连接\n        max-wait: -1 #连接池最大阻塞等待时间（使用负值表示没有限制）\n    timeout: 30000 #连接超时时间（毫秒）\n  cloud:\n    nacos:\n      server-addr: localhost:8848 # nacos地址\n    gateway:\n      routes:\n      # 路由标示，必须唯一\n        - id: sangluoBase\n          uri: http://127.0.0.1:48081 # 路由的目标地址\n          predicates: # 路由断言，判断请求是否符合规则# 路径断言，判断路径是否是以/api开头，如果是则符合\n            - Path=/userInfo/**,/role/**,/company/**\n        - id: sangluoOauth\n          uri: http://127.0.0.1:48082\n          predicates:\n            - Path=/oauth/**\n      default-filters:\n        - AddRequestHeader=Truth,wwwwwwwwwwwwww! #添加请求头示例\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: satoken\n  # token有效期，单位秒，-1代表永不过期\n  timeout: 2592000\n  # token临时有效期 (指定时间内无操作就视为token过期)，单位秒\n  activity-timeout: -1\n  # 是否允许同一账号并发登录 (为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为false时每次登录新建一个token)\n  is-share: false\n  # token风格\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: false\n  # 是否从cookie中读取token\n  is-read-cookie: false\n  # 是否从head中读取token\n  is-read-head: true\n  jwt-secret-key: sangluojwtaaa88888\n  \n', 'f4b86a2221777105a754aa0d0e683c2a', '2023-11-17 08:59:26', '2023-12-15 07:01:02', NULL, '192.168.3.11', 'sangluo', 'sangluo-dev', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (124, 'baseserver-dev.yml', 'dev', 'server:\n  port: 48081\n\nspring:\n  application:\n    name: baseserver\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/sangluo?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n  jackson:\n    default-property-inclusion: non_null\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.sangluo.baseserver\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapper-locations: mapper/*.xml\n    config-location: classpath:/mybatis/mybatis-config.xml', '4ba5d00232f80572f539a24a0cc3ab8c', '2023-11-17 09:06:41', '2023-12-14 10:06:26', NULL, '192.168.3.11', 'sangluo', 'sangluo-dev', '', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `datum_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '增加租户字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_beta' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `tag_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_tag' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id` ASC, `tag_name` ASC, `tag_type` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_tag_relation' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create` ASC) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified` ASC) USING BTREE,
  INDEX `idx_did`(`data_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '多租户改造' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (123, 141, 'gateway.yml', 'dev', 'sangluo', 'server:\n  port: 80\n  servlet:\n    session:\n      cookie:\n        # 需要更换存放sessionId的cookie名字，否则认证服务和客户端的sessionId会相互覆盖\n        name: JSESSIONID-2\nlogging:\n  level:\n    cn.itcast: debug\n  pattern:\n    dateformat: MM-dd HH:mm:ss:SSS\nspring:\n  application:\n    name: gateway\n  redis:\n    host: localhost\n    password: \n    port: 6379\n    lettuce:\n      pool:\n        max-active: 8 #连接池最大连接数（使用负值表示没有限制）\n        max-idle: 8 #连接池中的最大空闲连接\n        min-idle: 0 #连接池中的最小空闲连接\n        max-wait: -1 #连接池最大阻塞等待时间（使用负值表示没有限制）\n    timeout: 30000 #连接超时时间（毫秒）\n  cloud:\n    nacos:\n      server-addr: localhost:8848 # nacos地址\n    gateway:\n      routes:\n      # 路由标示，必须唯一\n        - id: sangluoBase\n          uri: http://127.0.0.1:48080 # 路由的目标地址\n          predicates: # 路由断言，判断请求是否符合规则# 路径断言，判断路径是否是以/api开头，如果是则符合\n            - Path=/userInfo/**,/role/**,/company/**\n        - id: sangluoOauth\n          uri: http://127.0.0.1:48082\n          predicates:\n            - Path=/oauth/**\n      default-filters:\n        - AddRequestHeader=Truth,wwwwwwwwwwwwww! #添加请求头示例\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: satoken\n  # token有效期，单位秒，-1代表永不过期\n  timeout: 2592000\n  # token临时有效期 (指定时间内无操作就视为token过期)，单位秒\n  activity-timeout: -1\n  # 是否允许同一账号并发登录 (为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为false时每次登录新建一个token)\n  is-share: false\n  # token风格\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: false\n  # 是否从cookie中读取token\n  is-read-cookie: false\n  # 是否从head中读取token\n  is-read-head: true\n  jwt-secret-key: sangluojwtaaa88888\n  \n', 'f6b1714c7b4c033a931c46d6855dc653', '2023-12-14 18:06:13', '2023-12-14 10:06:13', NULL, '192.168.3.11', 'U', 'sangluo-dev', '');
INSERT INTO `his_config_info` VALUES (124, 142, 'baseserver-dev.yml', 'dev', 'sangluo', 'server:\n  port: 48080\n\nspring:\n  application:\n    name: baseserver\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/sangluo?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n  jackson:\n    default-property-inclusion: non_null\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.sangluo.baseserver\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapper-locations: mapper/*.xml\n    config-location: classpath:/mybatis/mybatis-config.xml', 'd28b0c6c32308434cc1facb6957f64d0', '2023-12-14 18:06:25', '2023-12-14 10:06:26', NULL, '192.168.3.11', 'U', 'sangluo-dev', '');
INSERT INTO `his_config_info` VALUES (123, 143, 'gateway.yml', 'dev', 'sangluo', 'server:\n  port: 48080\n  servlet:\n    session:\n      cookie:\n        # 需要更换存放sessionId的cookie名字，否则认证服务和客户端的sessionId会相互覆盖\n        name: JSESSIONID-2\nlogging:\n  level:\n    cn.itcast: debug\n  pattern:\n    dateformat: MM-dd HH:mm:ss:SSS\nspring:\n  application:\n    name: gateway\n  redis:\n    host: localhost\n    password: \n    port: 6379\n    lettuce:\n      pool:\n        max-active: 8 #连接池最大连接数（使用负值表示没有限制）\n        max-idle: 8 #连接池中的最大空闲连接\n        min-idle: 0 #连接池中的最小空闲连接\n        max-wait: -1 #连接池最大阻塞等待时间（使用负值表示没有限制）\n    timeout: 30000 #连接超时时间（毫秒）\n  cloud:\n    nacos:\n      server-addr: localhost:8848 # nacos地址\n    gateway:\n      routes:\n      # 路由标示，必须唯一\n        - id: sangluoBase\n          uri: http://127.0.0.1:48080 # 路由的目标地址\n          predicates: # 路由断言，判断请求是否符合规则# 路径断言，判断路径是否是以/api开头，如果是则符合\n            - Path=/userInfo/**,/role/**,/company/**\n        - id: sangluoOauth\n          uri: http://127.0.0.1:48082\n          predicates:\n            - Path=/oauth/**\n      default-filters:\n        - AddRequestHeader=Truth,wwwwwwwwwwwwww! #添加请求头示例\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: satoken\n  # token有效期，单位秒，-1代表永不过期\n  timeout: 2592000\n  # token临时有效期 (指定时间内无操作就视为token过期)，单位秒\n  activity-timeout: -1\n  # 是否允许同一账号并发登录 (为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为false时每次登录新建一个token)\n  is-share: false\n  # token风格\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: false\n  # 是否从cookie中读取token\n  is-read-cookie: false\n  # 是否从head中读取token\n  is-read-head: true\n  jwt-secret-key: sangluojwtaaa88888\n  \n', 'b111a02b3bba37f417134ad085ff3a63', '2023-12-15 14:57:19', '2023-12-15 06:57:19', NULL, '192.168.3.11', 'U', 'sangluo-dev', '');
INSERT INTO `his_config_info` VALUES (123, 144, 'gateway.yml', 'dev', 'sangluo', 'server:\n  port: 5713\n  servlet:\n    session:\n      cookie:\n        # 需要更换存放sessionId的cookie名字，否则认证服务和客户端的sessionId会相互覆盖\n        name: JSESSIONID-2\nlogging:\n  level:\n    cn.itcast: debug\n  pattern:\n    dateformat: MM-dd HH:mm:ss:SSS\nspring:\n  application:\n    name: gateway\n  redis:\n    host: localhost\n    password: \n    port: 6379\n    lettuce:\n      pool:\n        max-active: 8 #连接池最大连接数（使用负值表示没有限制）\n        max-idle: 8 #连接池中的最大空闲连接\n        min-idle: 0 #连接池中的最小空闲连接\n        max-wait: -1 #连接池最大阻塞等待时间（使用负值表示没有限制）\n    timeout: 30000 #连接超时时间（毫秒）\n  cloud:\n    nacos:\n      server-addr: localhost:8848 # nacos地址\n    gateway:\n      routes:\n      # 路由标示，必须唯一\n        - id: sangluoBase\n          uri: http://127.0.0.1:48080 # 路由的目标地址\n          predicates: # 路由断言，判断请求是否符合规则# 路径断言，判断路径是否是以/api开头，如果是则符合\n            - Path=/userInfo/**,/role/**,/company/**\n        - id: sangluoOauth\n          uri: http://127.0.0.1:48082\n          predicates:\n            - Path=/oauth/**\n      default-filters:\n        - AddRequestHeader=Truth,wwwwwwwwwwwwww! #添加请求头示例\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: satoken\n  # token有效期，单位秒，-1代表永不过期\n  timeout: 2592000\n  # token临时有效期 (指定时间内无操作就视为token过期)，单位秒\n  activity-timeout: -1\n  # 是否允许同一账号并发登录 (为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为false时每次登录新建一个token)\n  is-share: false\n  # token风格\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: false\n  # 是否从cookie中读取token\n  is-read-cookie: false\n  # 是否从head中读取token\n  is-read-head: true\n  jwt-secret-key: sangluojwtaaa88888\n  \n', '65d1162e56d0c7bf7b071391a72a9d4e', '2023-12-15 15:01:01', '2023-12-15 07:01:02', NULL, '192.168.3.11', 'U', 'sangluo-dev', '');
INSERT INTO `his_config_info` VALUES (1, 145, 'application', 'DEFAULT_GROUP', '', 'spring:\n  autoconfigure:\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\n  mvc:\n    pathmatch:\n      matching-strategy: ant_path_matcher\n\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n', 'aaa73b809cfd4d0058893aa13da57806', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (2, 146, 'ruoyi-gateway', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: ruoyi-auth\n          uri: lb://ruoyi-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - ValidateCodeFilter\n            - StripPrefix=1\n        # 代码生成\n        - id: ruoyi-gen\n          uri: lb://ruoyi-gen\n          predicates:\n            - Path=/code/**\n          filters:\n            - StripPrefix=1\n        # 定时任务\n        - id: ruoyi-job\n          uri: lb://ruoyi-job\n          predicates:\n            - Path=/schedule/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: ruoyi-system\n          uri: lb://ruoyi-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 文件服务\n        - id: ruoyi-file\n          uri: lb://ruoyi-file\n          predicates:\n            - Path=/file/**\n          filters:\n            - StripPrefix=1\n\n# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    enabled: true\n    type: math\n  # 防止XSS攻击\n  xss:\n    enabled: true\n    excludeUrls:\n      - /system/notice\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/logout\n      - /auth/login\n      - /auth/register\n      - /*/v2/api-docs\n      - /csrf\n', '57cec5abd0e0a6b77d853750344a9dc0', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (3, 147, 'ruoyi-auth', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n', '8bd9dada9a94822feeab40de55efced6', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (4, 148, 'ruoyi-monitor', 'DEFAULT_GROUP', '', '# spring\nspring:\n  security:\n    user:\n      name: ruoyi\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: 系统服务状态监控\n', '1b5291cf8b2c3991aa5f43e86a933c8d', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (5, 149, 'ruoyi-system', 'DEFAULT_GROUP', '', '# spring配置\nspring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://localhost:3306/ryblog?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai\n            username: root\n            password: root\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By ruoyi\n  licenseUrl: http://127.0.0.1:48084', 'b59cffa883d602df8eae27615d1b3d5a', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (6, 150, 'ruoyi-gen', 'DEFAULT_GROUP', '', '# spring配置\nspring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/ryblog?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.gen.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 代码生成接口文档\n  license: Powered By ruoyi\n  licenseUrl: http://127.0.0.1:48084\n\n# 代码生成\ngen:\n  # 作者\n  author: ruoyi\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.ruoyi.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n', 'decc595580b2703a25f26ccdc4a951a5', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (7, 151, 'ruoyi-job', 'DEFAULT_GROUP', '', '# spring配置\nspring:\n  redis:\n    host: localhost\n    port: 6379\n    password: \n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/ryblog?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By ruoyi\n  licenseUrl: http://127.0.0.1:48084\n', '527a3b9c05837e6a7da55139e8feefa3', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (8, 152, 'ruoyi-file', 'DEFAULT_GROUP', '', '# 本地文件上传    \nfile:\n    domain: http://127.0.0.1:9300\n    path: F:/website/FileUploadPath/ryFile\n    prefix: /statics\n\n# FastDFS配置\nfdfs:\n  domain: http://8.129.231.12\n  soTimeout: 3000\n  connectTimeout: 2000\n  trackerList: 8.129.231.12:22122\n\n# Minio配置\nminio:\n  url: http://8.129.231.12:9000\n  accessKey: minioadmin\n  secretKey: minioadmin\n  bucketName: test', '67faaa5f39588f0a1e619f97ec4a5549', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');
INSERT INTO `his_config_info` VALUES (9, 153, 'sentinel-ruoyi-gateway', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"resource\": \"ruoyi-auth\",\r\n        \"count\": 500,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-system\",\r\n        \"count\": 1000,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-gen\",\r\n        \"count\": 200,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-job\",\r\n        \"count\": 300,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    }\r\n]', '9f3a3069261598f74220bc47958ec252', '2023-12-30 19:29:29', '2023-12-30 11:29:29', NULL, '192.168.3.11', 'D', 'ry-dev', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role` ASC, `resource` ASC, `action` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username` ASC, `role` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '租户容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp` ASC, `tenant_id` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'tenant_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', 'ry-dev', 'ry-dev', 'ry-dev', 'nacos', 1683284521556, 1683284521556);
INSERT INTO `tenant_info` VALUES (2, '1', 'sangluo-dev', 'sangluo-dev', 'sangluo-dev', 'nacos', 1683364606997, 1683364606997);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
