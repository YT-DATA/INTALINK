# Intalink Database Table Structure and Functionality Description

## Table of Contents

1. [Link Generation Methods Table (`ik_bp_generating_methods_basic`)](#1-link-generation-methods-table-ik_bp_generating_methods_basic)
2. [Data Request Log (`ik_bp_apply_for_basic`)](#2-data-request-log-ik_bp_apply_for_basic)
3. [Data Column Basic Information (`ik_bp_data_column_basic`)](#3-data-column-basic-information-ik_bp_data_column_basic)
4. [Data Model Table (`ik_bp_data_model_basic`)](#4-data-model-table-ik_bp_data_model_basic)
5. [Data Source Information Table (`ik_bp_data_source_basic`)](#5-data-source-information-table-ik_bp_data_source_basic)
6. [System Model Relationship Table (`ik_bp_data_source_data_model_relation_basic`)](#6-system-model-relationship-table-ik_bp_data_source_data_model_relation_basic)
7. [System Data Source Relationship Table (`ik_bp_data_source_system_relation_basic`)](#7-system-data-source-relationship-table-ik_bp_data_source_system_relation_basic)
8. [Data Table Information (`ik_bp_data_table_basic`)](#8-data-table-information-ik_bp_data_table_basic)
9. [Basic Relationship Table (`ik_bp_data_table_relation_basic`)](#9-basic-relationship-table-ik_bp_data_table_relation_basic)
10. [Dictionary Table (`ik_bp_dictionary_dic`)](#10-dictionary-table-ik_bp_dictionary_dic)
11. [Evaluation Methods Table (`ik_bp_evaluation_method_basic`)](#11-evaluation-methods-table-ik_bp_evaluation_method_basic)
12. [Output Methods Table (`ik_bp_result_type_basic`)](#12-output-methods-table-ik_bp_result_type_basic)
13. [System Settings Table (`ik_bp_sys_setting`)](#13-system-settings-table-ik_bp_sys_setting)
14. [System Information Table (`ik_bp_system_basic`)](#14-system-information-table-ik_bp_system_basic)
15. [Optimal Link Result Table (`ik_fp_better_link_result`)](#15-optimal-link-result-table-ik_fp_better_link_result)
16. [Optimal Output Result Table (`ik_fp_better_result`)](#16-optimal-output-result-table-ik_fp_better_result)
17. [Evaluation Method Weight Table (`ik_fp_evaluation_method_weight`)](#17-evaluation-method-weight-table-ik_fp_evaluation_method_weight)
18. [Data Link Table (`ik_fp_link_result`)](#18-data-link-table-ik_fp_link_result)
19. [Link Weight Calculation Result Table (`ik_fp_link_weight_count_result`)](#19-link-weight-calculation-result-table-ik_fp_link_weight_count_result)
20. [Different Scoring Methods Results Table (`ik_fp_score_result`)](#20-different-scoring-methods-results-table-ik_fp_score_result)
21. [Scoring Scheme Table (`ik_fp_scoring_scheme_basic`)](#21-scoring-scheme-table-ik_fp_scoring_scheme_basic)
22. [Inter-table Relationship Storage Table (`ik_rp_data_table_relation`)](#22-inter-table-relationship-storage-table-ik_rp_data_table_relation)

---

## 1. Link Generation Methods Table (`ik_bp_generating_methods_basic`)

Stores user-defined methods for link generation. Users must implement these methods in code and define them in this table.

## 2. Data Request Log (`ik_bp_apply_for_basic`)

Stores information about user data requests, including the source and content of the requests. The content is stored in JSON format.

## 3. Data Column Basic Information (`ik_bp_data_column_basic`)

Stores basic information about the data columns in tables, which forms the basis for user requests.

## 4. Data Model Table (`ik_bp_data_model_basic`)

Stores definitions of data models, which are fundamental for intelligent data modeling.

## 5. Data Source Information Table (`ik_bp_data_source_basic`)

Stores user data source information, which is essential for extracting data models and generating links subsequently.

## 6. System Model Relationship Table (`ik_bp_data_source_data_model_relation_basic`)

Stores system information to facilitate business applications, such as extracting specific fields from a data table under a particular system.

## 7. System Data Source Relationship Table (`ik_bp_data_source_system_relation_basic`)

Stores the relationship information between systems and data sources, facilitating subsequent data extraction.

## 8. Data Table Information (`ik_bp_data_table_basic`)

Stores information about the data tables under data models, serving as a foundational information table.

## 9. Basic Relationship Table (`ik_bp_data_table_relation_basic`)

Stores definitions of relationships, used for analyzing relationships between fields during data relationship exploration.

## 10. Dictionary Table (`ik_bp_dictionary_dic`)

Stores system dictionary information, serving as a fundamental system table.

## 11. Evaluation Methods Table (`ik_bp_evaluation_method_basic`)

Stores definitions of methods for evaluating link generation, serving as a foundational system information table.

## 12. Output Methods Table (`ik_bp_result_type_basic`)

Stores basic information about output methods.

## 13. System Settings Table (`ik_bp_sys_setting`)

Stores system configuration information.

## 14. System Information Table (`ik_bp_system_basic`)

Stores system information, including the definitions of the system.

## 15. Optimal Link Result Table (`ik_fp_better_link_result`)

Stores generated links that have been evaluated using specific methods and selected as optimal.

## 16. Optimal Output Result Table (`ik_fp_better_result`)

Stores the output information of generated links after they have been selected as optimal.

## 17. Evaluation Method Weight Table (`ik_fp_evaluation_method_weight`)

Stores the weighting coefficients for user evaluation methods.

## 18. Data Link Table (`ik_fp_link_result`)

Stores generated data links.

## 19. Link Weight Calculation Result Table (`ik_fp_link_weight_count_result`)

Stores link weight coefficients and their calculation results.

## 20. Different Scoring Methods Results Table (`ik_fp_score_result`)

Stores the scoring results of generated links, calculated using various scoring methods.

## 21. Scoring Scheme Table (`ik_fp_scoring_scheme_basic`)

Stores scoring schemes for links, serving as a foundational system information table.

## 22. Inter-table Relationship Storage Table (`ik_rp_data_table_relation`)

Stores data relationships between tables under a data model, serving as a core system information table.
