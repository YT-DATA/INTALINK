# Intalink Link Data Explanation

## Table of Contents

- [Link Retrieval Interface Return Values](#link-retrieval-interface-return-values)
  - [Return Value Meanings](#return-value-meanings)
  - [Return Value Example](#return-value-example)
- [Link Retrieval Transformed to SQL](#link-retrieval-transformed-to-sql)
  - [Link Decomposition](#link-decomposition)
  - [Data Table and Data Item Information Retrieval](#data-table-and-data-item-information-retrieval)
  - [SQL Generation](#sql-generation)

## Link Retrieval Interface Return Values

### Return Value Meanings

- **`msg`**: Interface return message.
- **`code`**: Status code.
- **`data`**: Return value, list type, each element is a relationship link.
- **`data[i]`**: Elements within `data`, list type, each is a link's connection (ordered by the link sequence).
- **`before`**: Data table corresponding to the connection's start point.
- **`after`**: Data table corresponding to the connection's endpoint.
- **`MainTable`**: Data table corresponding to the `MainColumn` in the expression.
- **`RelationTable`**: Data table corresponding to the `RelationColumn` in the expression.
- **`Relation`**: Inter-table relationship expression. Each relationship is connected by a `?`. "77-53" represents the data item IDs for `MainColumn` and `RelationColumn`. The string `COALESCE(${MainColumn}$, 0) = ${RelationColumn}$` after "77-53" represents the join expression between these two fields.

### Return Value Example

```json
{
    "msg": "Operation successful",
    "code": 200,
    "data": [
        [
            {
                "before": null,
                "after": "Vehicle Sales Table"
            },
            {
                "before": "Vehicle Sales Table",
                "after": "Vehicle Information Table",
                "MainTable": "Vehicle Information Table",
                "RelationTable": "Vehicle Sales Table",
                "relation": "77-53:${MainColumn}$ = ${RelationColumn}$"
            }
        ]
    ]
}
```

## Link Retrieval Transformed to SQL

### Link Decomposition

Using the example from the interface return value, we identify the following link:

- Vehicle Sales Table -- Vehicle Information Table

### Data Table and Data Item Information Retrieval

The table and field information retrieved from the database is as follows:

| Data Item ID | Data Item Code | Data Item Name   | Belonging Table ID | Data Table Code | Data Table Name       |
|--------------|----------------|------------------|--------------------|-----------------|-----------------------|
| 52           | ProductionDate | Production Date  | 68                 | Car             | Vehicle Information Table |
| 54           | CarID          | Vehicle ID       | 69                 | salesrecord     | Vehicle Sales Table      |
| 77           | SalesRecordID  | Sales Record     | 68                 | Car             | Vehicle Information Table |
| 53           | RecordID       | Sales Record ID  | 69                 | salesrecord     | Vehicle Sales Table      |
| 55           | SaleDate       | Sale Date        | 69                 | salesrecord     | Vehicle Sales Table      |
| 64           | UsageDate      | Usage Date       | 72                 | PartUsage       | Part Usage Situation     |
| 63           | PartID         | Part ID          | 72                 | PartUsage       | Part Usage Situation     |
| 58           | PartID         | Part ID          | 71                 | Parts           | Vehicle Parts Table      |
| 50           | Manufacturer   | Manufacturer     | 68                 | Car             | Vehicle Information Table |
| 76           | NewDate        | Manufacture Date | 71                 | Parts           | Vehicle Parts Table      |
| 59           | PartName       | Part Name        | 71                 | Parts           | Vehicle Parts Table      |
| 72           | Color          | Color            | 68                 | Car             | Vehicle Information Table |

### SQL Generation

#### Initial SQL Writing

Given the parameters `52,54`, based on the link, the tables involved are Vehicle Sales Table (`salesrecord`) and Vehicle Information Table (`Car`). The initial SQL could be:

```sql
SELECT ProductionDate, CarID FROM salesrecord, Car
```

#### Adding Join Expression

Analyzing the expression 77-53:${MainColumn}$ = ${RelationColumn}$, simplify it to 77=53, and replace the corresponding data item IDs with data item codes, resulting in SalesRecordID = RecordID. Then, incorporate this condition into the WHERE clause to obtain the final SQL:

```sql
SELECT ProductionDate, CarID FROM salesrecord, Car WHERE SalesRecordID = RecordID
```
