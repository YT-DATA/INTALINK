# Intalink 数据库表结构与功能说明

## 目录

1. [链路生成方法表 (`ik_bp_generating_methods_basic`)](#1-链路生成方法表-ik_bpgenerating_methods_basic)
2. [数据申请日志 (`ik_bp_apply_for_basic`)](#2-数据申请日志-ik_bp_apply_for_basic)
3. [数据列基础信息 (`ik_bp_data_column_basic`)](#3-数据列基础信息-ik_bp_data_column_basic)
4. [数据模型表 (`ik_bp_data_model_basic`)](#4-数据模型表-ik_bp_data_model_basic)
5. [数据源信息表 (`ik_bp_data_source_basic`)](#5-数据源信息表-ik_bp_data_source_basic)
6. [系统模型关联表 (`ik_bp_data_source_data_model_relation_basic`)](#6-系统模型关联表-ik_bp_data_source_data_model_relation_basic)
7. [系统数据源关联表 (`ik_bp_data_source_system_relation_basic`)](#7-系统数据源关联表-ik_bp_data_source_system_relation_basic)
8. [数据表信息 (`ik_bp_data_table_basic`)](#8-数据表信息-ik_bp_data_table_basic)
9. [关联关系基础表 (`ik_bp_data_table_relation_basic`)](#9-关联关系基础表-ik_bp_data_table_relation_basic)
10. [字典表 (`ik_bp_dictionary_dic`)](#10-字典表-ik_bp_dictionary_dic)
11. [评价方法表 (`ik_bp_evaluation_method_basic`)](#11-评价方法表-ik_bp_evaluation_method_basic)
12. [输出方式表 (`ik_bp_result_type_basic`)](#12-输出方式表-ik_bp_result_type_basic)
13. [系统配置表 (`ik_bp_sys_setting`)](#13-系统配置表-ik_bp_sys_setting)
14. [系统信息表 (`ik_bp_system_basic`)](#14-系统信息表-ik_bp_system_basic)
15. [优选链路结果 (`ik_fp_better_link_result`)](#15-优选链路结果-ik_fp_better_link_result)
16. [优选输出结果 (`ik_fp_better_result`)](#16-优选输出结果-ik_fp_better_result)
17. [评分权重 (`ik_fp_evaluation_method_weight`)](#17-评分权重-ik_fp_evaluation_method_weight)
18. [数据链路表 (`ik_fp_link_result`)](#18-数据链路表-ik_fp_link_result)
19. [链路权重系数计算结果 (`ik_fp_link_weight_count_result`)](#19-链路权重系数计算结果-ik_fp_link_weight_count_result)
20. [不同评分方法评分结果表 (`ik_fp_score_result`)](#20-不同评分方法评分结果表-ik_fp_score_result)
21. [评分方案表 (`ik_fp_scoring_scheme_basic`)](#21-评分方案表-ik_fp_scoring_scheme_basic)
22. [表间关系存储表 (`ik_rp_data_table_relation`)](#22-表间关系存储表-ik_rp_data_table_relation)

---

## 1. 链路生成方法表 (`ik_bp_generating_methods_basic`)

用于存储用户的链路生成方法。用户需在代码层对方法进行实现，并在本表中对方法进行定义。

## 2. 数据申请日志 (`ik_bp_apply_for_basic`)

用于存储用户的需求申请信息，包括申请来源与申请内容。申请内容以 JSON 的形式进行存储。

## 3. 数据列基础信息 (`ik_bp_data_column_basic`)

用于存储数据表中的数据列基础信息，这些数据列作为用户申请信息的基础。

## 4. 数据模型表 (`ik_bp_data_model_basic`)

用于存储数据模型的定义信息，这些模型是数据智能建模的基础。

## 5. 数据源信息表 (`ik_bp_data_source_basic`)

存储用户的数据源信息，这些信息是后续提取数据模型及智能生成链路后，提取相应的数据信息的基础。

## 6. 系统模型关联表 (`ik_bp_data_source_data_model_relation_basic`)

用于存储用户的系统信息，以便后续业务应用，例如提取某系统下某数据表的特定字段作为数据展示的核心数据项。

## 7. 系统数据源关联表 (`ik_bp_data_source_system_relation_basic`)

用于存储系统与数据源之间的关联信息，以便后续的数据提取。

## 8. 数据表信息 (`ik_bp_data_table_basic`)

用于存储数据模型下的数据表信息，作为基础信息表。

## 9. 关联关系基础表 (`ik_bp_data_table_relation_basic`)

存储关联关系的定义信息，在数据关系探查时，会根据该表的定义进行字段间的关系分析。

## 10. 字典表 (`ik_bp_dictionary_dic`)

用于存储系统字典信息，作为系统的基础表。

## 11. 评价方法表 (`ik_bp_evaluation_method_basic`)

存储对生成链路的评价方法的定义信息，作为系统的基础信息表。

## 12. 输出方式表 (`ik_bp_result_type_basic`)

用于存储输出方式的基础信息表。

## 13. 系统配置表 (`ik_bp_sys_setting`)

用于存储系统的配置信息。

## 14. 系统信息表 (`ik_bp_system_basic`)

用于存储系统信息，包括系统的定义信息。

## 15. 优选链路结果 (`ik_fp_better_link_result`)

用于存储生成的链路，并经过评价方法评分后的优选链路。

## 16. 优选输出结果 (`ik_fp_better_result`)

用于存储生成的链路，并将结果进行优选后的输出信息。

## 17. 评分权重 (`ik_fp_evaluation_method_weight`)

用于存储用户评价方法的评分权重。

## 18. 数据链路表 (`ik_fp_link_result`)

用于存储生成的数据链路。

## 19. 链路权重系数计算结果 (`ik_fp_link_weight_count_result`)

用于存储链路权重系数及其计算结果。

## 20. 不同评分方法评分结果表 (`ik_fp_score_result`)

用于存储生成的链路，经过不同的评分方法生成的链路的评分结果。

## 21. 评分方案表 (`ik_fp_scoring_scheme_basic`)

用于存储链路的评分方案，为系统基础信息表。

## 22. 表间关系存储表 (`ik_rp_data_table_relation`)

用于存储数据模型下，各个表之间存在的数据关联关系，为系统核心信息表。

