/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : intalink

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 03/04/2024 08:23:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ik_bp_data_column_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_data_column_basic`;
CREATE TABLE `ik_bp_data_column_basic`  (
  `data_column_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_column_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据项编码',
  `data_column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据项名称',
  `data_column_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据项描述',
  `data_table_id` int(11) NULL DEFAULT NULL COMMENT '数据表Id',
  `is_del` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`data_column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据列基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_data_column_basic
-- ----------------------------
INSERT INTO `ik_bp_data_column_basic` VALUES (1, 'COL_A', 'COL_A', 'COL_A', 1, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (2, 'COL_B', 'COL_B', 'COL_B', 2, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (3, 'COL_C1', 'COL_C1', 'COL_C1', 3, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (4, 'COL_D', 'COL_D', 'COL_D', 4, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (5, 'COL_E', 'COL_E', 'COL_E', 5, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (6, 'COL_F', 'COL_F', 'COL_F', 6, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (7, 'COL_G', 'COL_G', 'COL_G', 7, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (8, 'COL_H', 'COL_H', 'COL_H', 8, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (9, 'COL_I', 'COL_I', 'COL_I', 9, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (10, 'COL_G', 'COL_G', 'COL_G', 10, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (11, 'COL_K', 'COL_K', 'COL_K', 11, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (12, 'COL_L', 'COL_L', 'COL_L', 12, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (13, 'COL_M', 'COL_M', 'COL_M', 13, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (14, 'COL_N', 'COL_N', 'COL_N', 14, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (15, 'COL_O', 'COL_O', 'COL_O', 15, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (16, 'COL_P', 'COL_P', 'COL_P', 16, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (17, 'COL_Q', 'COL_Q', 'COL_Q', 17, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (18, 'COL_R', 'COL_R', 'COL_R', 18, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (19, 'COL_S', 'COL_S', 'COL_S', 19, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (20, 'COL_T', 'COL_T', 'COL_T', 20, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (21, 'COL_U', 'COL_U', 'COL_U', 21, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (22, 'COL_V', 'COL_V', 'COL_V', 22, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (23, 'COL_W', 'COL_W', 'COL_W', 23, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (24, 'COL_X', 'COL_X', 'COL_X', 24, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (25, 'COL_Y', 'COL_Y', 'COL_Y', 25, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (26, 'COL_Z', 'COL_Z', 'COL_Z', 26, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (27, 'col010101', 'col010101', 'col010101', 10, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (42, 'ITEM_1', '字段ITEM_1', '字段ITEM_1', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (43, 'COLUMN_5', '字段5', '字段5', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (44, 'C1', '字段C1', '字段C1', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (45, 'C6', '字段C6', '字段C6', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (46, 'C1', '字段C1', '字段C1', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (47, 'C2', '字段C2', '字段C2', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (48, 'C3', '字段C3', '字段C3', 67, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (49, 'CarID', 'CarID', 'CarID', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (50, 'Manufacturer', 'Manufacturer', 'Manufacturer', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (51, 'Model', 'Model', 'Model', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (52, 'ProductionDate', 'ProductionDate', 'ProductionDate', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (53, 'RecordID', 'RecordID', 'RecordID', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (54, 'CarID', 'CarID', 'CarID', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (55, 'SaleDate', 'SaleDate', 'SaleDate', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (56, 'SaleAmount', 'SaleAmount', 'SaleAmount', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (57, 'CustomerName', 'CustomerName', 'CustomerName', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (58, 'PartID', 'PartID', 'PartID', 71, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (59, 'PartName', 'PartName', 'PartName', 71, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (60, 'PartType', 'PartType', 'PartType', 71, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (61, 'UsageID', 'UsageID', 'UsageID', 72, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (62, 'CarID', 'CarID', 'CarID', 72, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (63, 'PartID', 'PartID', 'PartID', 72, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (64, 'UsageDate', 'UsageDate', 'UsageDate', 72, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (65, 'Column_1', 'Column_1', NULL, 92, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (66, 'Column_2', 'Column_2', NULL, 92, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (67, 'Column_3', 'Column_3', NULL, 92, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (68, 'Column_4', 'Column_4', NULL, 92, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (72, 'Color', 'Color', 'Color', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (73, 'Transmission', 'Transmission', 'Transmission', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (74, 'Salesperson', 'Salesperson', 'Salesperson', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (75, 'PaymentMethod', 'PaymentMethod', 'PaymentMethod', 69, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (76, 'NewDate', 'NewDate', 'NewDate', 71, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (77, 'SalesRecordID', 'SalesRecordID', 'SalesRecordID', 68, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (78, 'Column_1', 'Column_111223355', '', 93, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (79, 'Column_2', 'Column_2', NULL, 93, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (80, 'Column_3', 'Column_3', NULL, 93, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (81, 'Column_4', 'Column_4', '1', 93, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (85, 'Column_1', 'Column_1', NULL, 94, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (86, 'Column_2', 'Column_2', NULL, 94, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (87, 'Column_3', 'Column_3', NULL, 94, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (88, 'Column_4', 'Column_4', NULL, 94, 0);
INSERT INTO `ik_bp_data_column_basic` VALUES (94, 'test_column_01', '测试数据项01', '测试添加数据项01', 100, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (97, 'Column_1111111111', 'Column_1111111111', '', 103, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (98, '1', '1', '1', 104, 1);
INSERT INTO `ik_bp_data_column_basic` VALUES (99, '232', '123', '22', 105, 1);

-- ----------------------------
-- Table structure for ik_bp_data_model_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_data_model_basic`;
CREATE TABLE `ik_bp_data_model_basic`  (
  `data_model_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_model_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据模型编码',
  `data_model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据模型名称',
  `data_model_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据模型描述',
  `is_del` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`data_model_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据模型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_data_model_basic
-- ----------------------------
INSERT INTO `ik_bp_data_model_basic` VALUES (1, 'testmodel', '测试数据集模型', NULL, 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (2, 'testmodel01', '测试模型01', '测试模型01', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (19, 'excel_model_code', 'excel数据导入模型111111111111111111111111111111111111111111111111', 'excel_model_remark', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (20, 'excel_model_code01', 'excel数据导入模型01', 'excel_model_remark01', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (21, 'excel_model_code02', 'excel数据导入模型02', 'excel_model_remark02', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (22, 'excel_model_code03', 'excel数据导入模型03', 'excel_model_remark03', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (23, 'excel_model_code04', 'excel数据导入模型04', 'excel_model_remark04', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (24, 'excel_model_code05', 'excel数据导入模型05', 'excel_model_remark05', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (25, 'excel_model_code06', 'excel数据导入模型06', 'excel_model_remark06', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (26, 'excel_model_code07', 'excel数据导入模型07', 'excel_model_remark07', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (27, 'excel_model_code08', 'excel数据导入模型08', 'excel_model_remark08', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (28, 'excel_model_code09', 'excel数据导入模型09', 'excel_model_remark09', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (29, 'excel_model_code10', 'excel数据导入模型10', 'excel_model_remark10', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (30, 'excel_model_code11', 'excel数据导入模型11', 'excel_model_remark11', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (31, 'excel_model_code12', 'excel数据导入模型12', 'excel_model_remark12', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (32, 'excel_model_code13', 'excel数据导入模型13', 'excel_model_remark13', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (33, 'excel_model_code14', 'excel数据导入模型14', 'excel_model_remark14', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (34, '模型测试04', '模型测试04', '模型测试04', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (41, '库管数据', '库管数据', '库管数据', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (42, '进销存系统', '进销存系统', '进销存系统', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (43, '销售系统', '销售系统', '销售系统', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (44, '出库数据', '出库数据', '出库数据', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (45, '计划系统', '计划系统', '计划系统', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (47, 'excel_model_code66', 'excel数据导入模型66', 'excel_model_remark66', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (49, '测试数据项01', '测试数据项01', '测试数据项01', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (52, 'Column_1111111111', 'Column_1111111111', 'Column_1111111111', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (53, '1', 'excel数据', '1', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (54, NULL, '1', '1', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (57, '1', '1', '1', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (58, 'data model_1', 'data model', 'data model_1', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (59, 'data model_2', 'data model_2', 'data model_2', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (60, '1', '1', '1', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (61, '123', '123', '123', 1);
INSERT INTO `ik_bp_data_model_basic` VALUES (62, '123', '123', '123', 0);
INSERT INTO `ik_bp_data_model_basic` VALUES (63, NULL, '11', '11', 0);

-- ----------------------------
-- Table structure for ik_bp_data_source_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_data_source_basic`;
CREATE TABLE `ik_bp_data_source_basic`  (
  `data_source_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源URL地址',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `database_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据库类型',
  `is_del` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`data_source_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据源信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_data_source_basic
-- ----------------------------
INSERT INTO `ik_bp_data_source_basic` VALUES (6, '测试045', '39.106.105.4:23306/sjzl_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', '测试04', 'ZyQ2Eea1LII=', '测试04', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (8, '测试05', '39.106.105.4:23306/sjzl_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', '测试05', 'ZyQ2Eea1LII=', '测试05', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (9, '测试01', '39.106.105.4:23306/sjzl_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', '测试01', 'ZyQ2Eea1LII=', '测试01', 0);
INSERT INTO `ik_bp_data_source_basic` VALUES (10, '测试02', '测试02', '测试02', 'gdH1UBQofv6RlJhJQRexrA==', '测试02', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (11, '测试02', '测试02', '测试02', 'gdH1UBQofv6RlJhJQRexrA==', '测试02', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (12, '测试021', '测试02', '测试02', 'gdH1UBQofv6RlJhJQRexrA==', '测试02', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (13, '1', '1', '1', 'ArPNOJg6D0c=', '1', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (14, '1', '1', '1', 'ArPNOJg6D0c=', '1', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (16, 'whx测试20240305', '39.106.105.4:23306/sjzl_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'gaejbET0AUTMpYDYQ+09dA==', 'mysql', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (17, '测试031', '39.106.105.4:23306/sjzl_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'af2uzCGb1ppZSUP6xeXuFg==', 'mysql', 0);
INSERT INTO `ik_bp_data_source_basic` VALUES (18, '数据库03281548', '127.0.0.1', '0328', 'l/P56vUaqnY=', 'oracle', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (19, '1', '1', '1', 'ArPNOJg6D0c=', '1', 1);
INSERT INTO `ik_bp_data_source_basic` VALUES (20, '1', '1', '1', 'ArPNOJg6D0c=', '1', 1);

-- ----------------------------
-- Table structure for ik_bp_data_source_data_model_relation_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_data_source_data_model_relation_basic`;
CREATE TABLE `ik_bp_data_source_data_model_relation_basic`  (
  `data_source_data_model_relation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_id` int(11) NULL DEFAULT NULL COMMENT '系统Id',
  `data_model_id` int(11) NULL DEFAULT NULL COMMENT '数据模型Id',
  PRIMARY KEY (`data_source_data_model_relation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_data_source_data_model_relation_basic
-- ----------------------------
INSERT INTO `ik_bp_data_source_data_model_relation_basic` VALUES (1, 7, 0);
INSERT INTO `ik_bp_data_source_data_model_relation_basic` VALUES (2, 3, 0);
INSERT INTO `ik_bp_data_source_data_model_relation_basic` VALUES (3, 5, 20);
INSERT INTO `ik_bp_data_source_data_model_relation_basic` VALUES (4, 10, 2);
INSERT INTO `ik_bp_data_source_data_model_relation_basic` VALUES (5, 11, 20);

-- ----------------------------
-- Table structure for ik_bp_data_source_system_relation_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_data_source_system_relation_basic`;
CREATE TABLE `ik_bp_data_source_system_relation_basic`  (
  `data_source_system_relation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `datasource_id` int(11) NULL DEFAULT NULL COMMENT '数据源Id',
  `system_id` int(11) NULL DEFAULT NULL COMMENT '系统Id',
  PRIMARY KEY (`data_source_system_relation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统数据源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_data_source_system_relation_basic
-- ----------------------------
INSERT INTO `ik_bp_data_source_system_relation_basic` VALUES (2, 6, 6);
INSERT INTO `ik_bp_data_source_system_relation_basic` VALUES (3, 6, 7);
INSERT INTO `ik_bp_data_source_system_relation_basic` VALUES (4, 0, 3);
INSERT INTO `ik_bp_data_source_system_relation_basic` VALUES (5, 0, 5);
INSERT INTO `ik_bp_data_source_system_relation_basic` VALUES (6, 0, 10);
INSERT INTO `ik_bp_data_source_system_relation_basic` VALUES (7, 9, 11);

-- ----------------------------
-- Table structure for ik_bp_data_table_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_data_table_basic`;
CREATE TABLE `ik_bp_data_table_basic`  (
  `data_table_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_model_id` int(11) NULL DEFAULT NULL COMMENT '数据模型Id',
  `data_table_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据表编码',
  `data_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据表名称',
  `data_table_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据表描述',
  `is_del` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`data_table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_data_table_basic
-- ----------------------------
INSERT INTO `ik_bp_data_table_basic` VALUES (1, 19, 'tab010101', 'tab010101', 'tab010101', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (2, 1, 'TABLE_B', 'TABLE_B', 'TABLE_B', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (3, 1, 'TABLE_C', 'TABLE_C', 'TABLE_C', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (4, 1, 'TABLE_D', 'TABLE_D', 'TABLE_D', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (5, 1, 'TABLE_E', 'TABLE_E', 'TABLE_E', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (6, 1, 'TABLE_F', 'TABLE_F', 'TABLE_F', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (7, 1, 'TABLE_G', 'TABLE_G', 'TABLE_G', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (8, 1, 'TABLE_H', 'TABLE_H', 'TABLE_H', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (9, 1, 'TABLE_I', 'TABLE_I', 'TABLE_I', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (10, 1, 'TABLE_G', 'TABLE_G', 'TABLE_G', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (11, 1, 'TABLE_K', 'TABLE_K', 'TABLE_K', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (12, 1, 'TABLE_L', 'TABLE_L', 'TABLE_L', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (13, 1, 'TABLE_M', 'TABLE_M', 'TABLE_M', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (14, 1, 'TABLE_N', 'TABLE_N', 'TABLE_N', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (15, 1, 'TABLE_O', 'TABLE_O', 'TABLE_O', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (16, 1, 'TABLE_P', 'TABLE_P', 'TABLE_P', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (17, 1, 'TABLE_Q', 'TABLE_Q', 'TABLE_Q', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (18, 1, 'TABLE_R', 'TABLE_R', 'TABLE_R', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (19, 1, 'TABLE_S', 'TABLE_S', 'TABLE_S', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (20, 1, 'TABLE_T', 'TABLE_T', 'TABLE_T', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (21, 1, 'TABLE_U', 'TABLE_U', 'TABLE_U', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (22, 1, 'TABLE_V', 'TABLE_V', 'TABLE_V', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (23, 1, 'TABLE_W', 'TABLE_W', 'TABLE_W', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (24, 1, 'TABLE_X', 'TABLE_X', 'TABLE_X', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (25, 1, 'TABLE_Y', 'TABLE_Y', 'TABLE_Y', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (26, 1, 'TABLE_Z', 'TABLE_Z', 'TABLE_Z', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (27, 2, '测试03', '测试03', '测试04', 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (54, 41, 'TAB1', '数据表1', NULL, 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (55, 42, 'TABLE_M', '数据表M', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (56, 43, 'T1', '数据表T1', '1', 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (57, 44, 'T2', '数据表T2', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (58, 44, 'T3', '数据表T3', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (61, 45, 'TAB1', '数据表1', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (62, 45, 'TABLE_M', '数据表M', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (63, 45, 'T1', '数据表T1', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (64, 45, 'T2', '数据表T2', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (65, 45, 'T3', '数据表T3', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (66, 45, 'TAB_A', '数据表TAB_A', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (67, 45, 'TAB_B', '数据表TAB_B', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (68, 1, 'ceshi_Car', 'ceshi_Car', '测试数据集-车', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (69, 1, 'ceshi_salesrecord', 'ceshi_salesrecord', '测试数据集', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (70, 1, 'SalesRecord', 'SalesRecord', 'SalesRecord', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (71, 1, 'ceshi_Parts', 'ceshi_Parts', 'ceshi_Parts', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (72, 1, 'ceshi_PartUsage', 'ceshi_PartUsage', 'ceshi_PartUsage', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (92, 33, 'test_table_pdm', 'test_table_pdm2222222222222222222222222222222222222222222222222222222222222222', '', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (93, 20, 'test_table_pdm', 'test_table_pdm', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (94, 21, 'test_table_pdm', 'test_table_pdm', NULL, 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (100, NULL, '测试添加数据项01', '测试添加数据项01', '测试添加数据项01', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (103, 52, 'test_table_pdm1111101', 'test_table_pdm1111101', 'test_table_pdm1111101', 1);
INSERT INTO `ik_bp_data_table_basic` VALUES (104, 63, '1', '1', '1', 0);
INSERT INTO `ik_bp_data_table_basic` VALUES (105, 61, '222', '222', '222', 1);

-- ----------------------------
-- Table structure for ik_bp_system_basic
-- ----------------------------
DROP TABLE IF EXISTS `ik_bp_system_basic`;
CREATE TABLE `ik_bp_system_basic`  (
  `system_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统名称',
  `system_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统编码',
  `system_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统描述',
  `is_del` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`system_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ik_bp_system_basic
-- ----------------------------
INSERT INTO `ik_bp_system_basic` VALUES (1, '测试01', '测试01', '测试01', 1, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (2, '测试02', '测试02', '测试02', 1, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (3, '测试03', '测试03', '测试03', 0, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (4, '测试04', '测试04', '测试04', 1, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (5, '测试03', '测试04', '测试04', 0, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (6, '测试03', '测试06', '测试06', 1, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (7, '系统测试07-1', '系统测试07-1', '系统测试07-1', 0, NULL);
INSERT INTO `ik_bp_system_basic` VALUES (10, '测试0001', 'test_0001', '测试0001', 1, '2024-03-28 15:01:35');
INSERT INTO `ik_bp_system_basic` VALUES (11, 'SystemManage', 'SystemManage', 'SystemManage', 0, '2024-04-01 15:06:54');

SET FOREIGN_KEY_CHECKS = 1;
