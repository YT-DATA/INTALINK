# 数据关联表达式规则说明文档

## 1. 关联关系的概念

数据关联关系是指将两个或多个数据表中的数据项通过特定条件联系起来的方法。这种方法可以在数据查询或处理过程中匹配和组合不同表中的数据，以实现更复杂的数据分析和应用。关联关系通常通过主外键关系实现，其中主键在一个表中唯一标识一条记录，而外键则在另一个表中引用主键，建立两个表之间的联系。

## 2. 已实现的关联条件类型

系统定义了以下 8 种关联条件类型：

| 类型 | 描述 |
| --- | --- |
| 日期部分比较 | `DATE_FORMAT(${data_column_id}$, '%Y-%m') = DATE_FORMAT(${relation_data_column_id}$, '%Y-%m')` |
| 空值处理比较 | `COALESCE(${data_column_id}$, 0) = ${relation_data_column_id}$` |
| 字符比较 | `LEFT(${data_column_id}$, 3) = ${relation_data_column_id}$` |
| 直接值比较 | `${data_column_id}$ = ${relation_data_column_id}$` |
| 部分字符比较 | `LEFT(${data_column_id}$, 4) = LEFT(${relation_data_column_id}$, 4)` |
| 部分字符不区分大小写比较 | `LOWER(${data_column_id}$) = LOWER(${relation_data_column_id}$)` |
| 去除首尾空格后比较 | `TRIM(${data_column_id}$) = TRIM(${relation_data_column_id}$)` |

## 3. 描述方法

在描述关联关系时，应遵循以下步骤：

1. **表和数据项拆分**：根据数据模型，识别涉及的关联表和数据项。
2. **关系明确化**：明确指明表和数据项之间的关系，包括主外键关系或其他类型的关联关系。
3. **抽象化描述**：通过抽象化描述方法，保持数据关系的逻辑不受物理库变化的影响，提高系统的可移植性和可扩展性。

## 4. 数据模型描述方法

在描述数据模型时，应详细列出模型ID、模型名称和描述，表ID、表名称和描述，数据项ID、名称和描述。

### 示例：数据模型和数据表信息

#### 数据模型表（ik_bp_data_model_basic）

| 列名 | 数据类型 | 是否为空 | 主键 | 自增 | 默认值 | 备注 |
| --- | --- | --- | --- | --- | --- | --- |
| data_model_id | int | 否 | 是 | 是 | | 主键 |
| data_model_code | varchar(255) | 是 | 否 | 否 | | 数据模型编码 |
| data_model_name | varchar(255) | 是 | 否 | 否 | | 数据模型名称 |
| data_model_remark | varchar(255) | 是 | 否 | 否 | | 数据模型描述 |
| is_del | int | 是 | 否 | 否 | | 是否删除 |

#### 数据表信息（ik_bp_data_table_basic）

| 列名 | 数据类型 | 是否为空 | 主键 | 自增 | 默认值 | 备注 |
| --- | --- | --- | --- | --- | --- | --- |
| data_table_id | int | 否 | 是 | 是 | | 主键 |
| data_model_id | int | 是 | 否 | 否 | | 数据模型Id |
| data_table_code | varchar(255) | 是 | 否 | 否 | | 数据表编码 |
| data_table_name | varchar(255) | 是 | 否 | 否 | | 数据表名称 |
| data_table_remark | varchar(255) | 是 | 否 | 否 | | 数据表描述 |
| is_del | int | 是 | 否 | 否 | | 是否删除 |

## 5. 关联表达式

关联表达式用于表示数据表之间逻辑关系，应明确定义并统一规范。

## 6. 用法

明确关联字段定义，编写关联表达式，根据主表ID进行匹配和转换，确保数据处理的准确性和可靠性。

## 7. 数据填写说明

定义关联关系，填写主表数据项ID和关联表数据项ID，编写符合规范的关联表达式。

## 8. 示例

提供一个关联条件类型示例和对应的关联表达式。
