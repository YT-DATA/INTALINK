# Data Association Expression Rules Documentation

## 1. Concept of Association

Data association refers to the method of linking data items from two or more tables through specific conditions. This method can match and combine data from different tables during data querying or processing, facilitating more complex data analysis and applications. Associations are typically implemented through primary and foreign key relationships, where a primary key uniquely identifies a record in one table, and a foreign key in another table references the primary key, establishing a connection between the two tables.

## 2. Implemented Association Condition Types

The system has defined the following 8 types of association conditions:

| Type | Description |
|------|-------------|
| Date Part Comparison | `DATE_FORMAT(${data_column_id}$, '%Y-%m') = DATE_FORMAT(${relation_data_column_id}$, '%Y-%m')` |
| Null Handling Comparison | `COALESCE(${data_column_id}$, 0) = ${relation_data_column_id}$` |
| Character Comparison | `LEFT(${data_column_id}$, 3) = ${relation_data_column_id}$` |
| Direct Value Comparison | `${data_column_id}$ = ${relation_data_column_id}$` |
| Partial Character Comparison | `LEFT(${data_column_id}$, 4) = LEFT(${relation_data_column_id}$, 4)` |
| Case-Insensitive Partial Character Comparison | `LOWER(${data_column_id}$) = LOWER(${relation_data_column_id}$)` |
| Trimmed Character Comparison | `TRIM(${data_column_id}$) = TRIM(${relation_data_column_id}$)` |

## 3. Description Method

When describing associations, follow these steps:

1. **Table and Data Item Splitting**: Identify the associated tables and data items based on the data model.
2. **Clarification of Relationships**: Clearly specify the relationships between tables and data items, including primary and foreign key relationships or other types of associations.
3. **Abstraction Description**: Use abstraction to describe relationships to keep the logic of data relationships unaffected by changes in physical databases, enhancing the portability and scalability of the system.

## 4. Data Model Description Method

When describing data models, detail the model ID, model name, and description; table ID, table name, and description; and data item ID, name, and description.

### Example: Data Model and Table Information

#### Data Model Table (`ik_bp_data_model_basic`)

| Column Name | Data Type | Nullable | Primary Key | Auto Increment | Default Value | Description |
|-------------|-----------|----------|-------------|----------------|---------------|-------------|
| data_model_id | int | No | Yes | Yes | | Primary Key |
| data_model_code | varchar(255) | Yes | No | No | | Data Model Code |
| data_model_name | varchar(255) | Yes | No | No | | Data Model Name |
| data_model_remark | varchar(255) | Yes | No | No | | Data Model Description |
| is_del | int | Yes | No | No | | Deletion Status |

#### Data Table Information (`ik_bp_data_table_basic`)

| Column Name | Data Type | Nullable | Primary Key | Auto Increment | Default Value | Description |
|-------------|-----------|----------|-------------|----------------|---------------|-------------|
| data_table_id | int | No | Yes | Yes | | Primary Key |
| data_model_id | int | Yes | No | No | | Data Model ID |
| data_table_code | varchar(255) | Yes | No | No | | Data Table Code |
| data_table_name | varchar(255) | Yes | No | No | | Data Table Name |
| data_table_remark | varchar(255) | Yes | No | No | | Data Table Description |
| is_del | int | Yes | No | No | | Deletion Status |

## 5. Association Expression

Association expressions are used to represent logical relationships between data tables and should be clearly defined and standardized.

## 6. Usage

Clearly define association fields, write association expressions, match and transform based on the primary table ID, and ensure the accuracy and reliability of data processing.

## 7. Data Entry Instructions

Define the association relationship, fill in the primary table data item ID and the associated table data item ID, and write a compliant association expression.

## 8. Example

Provide an example of an association condition type and corresponding association expression.
