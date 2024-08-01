# Intalink 链路数据说明

## 目录

- [链路获取接口返回值](#链路获取接口返回值)
  - [返回值含义](#返回值含义)
  - [返回值示例](#返回值示例)
- [链路获取转化为 SQL](#链路获取转化为-sql)
  - [链路分解](#链路分解)
  - [数据表、数据项信息获取](#数据表数据项信息获取)
  - [生成 SQL](#生成-sql)

## 链路获取接口返回值

### 返回值含义

- `msg`: 接口返回提示
- `code`: 状态码
- `data`: 返回值，list 类型，其中元素为关系链路
- `data[i]`: `data` 中的元素，list 类型，其中元素为链路的每个连接（按链路顺序）
- `before`: 连接前端点对应数据表
- `after`: 连接后端点对应数据表
- `MainTable`: 表达式 MainColumn 对应数据表
- `RelationTable`: 表达式 RelationColumn 对应数据表
- `Relation`: 表间关系表达式，表间各关系使用英文问号（?）连接，其中 "77-53" 代表 MainColumn 和 RelationColumn 对应的数据项 ID；在 "77-53" 后边的字符串 `COALESCE(${MainColumn}$, 0) = ${RelationColumn}$` 代表这两个字段的关联表达式。

### 返回值示例

```json
{
    "msg": "操作成功",
    "code": 200,
    "data": [
        [
            {
                "before": null,
                "after": "车辆销售表"
            },
            {
                "before": "车辆销售表",
                "after": "车辆信息表",
                "MainTable": "车辆信息表",
                "RelationTable": "车辆销售表",
                "relation": "77-53:${MainColumn}$ = ${RelationColumn}$"
            }
        ]
    ]
}
```
## 链路获取转化为-sql

### 链路分解

使用接口返回值的示例，我们识别出以下链路：

- 车辆销售表 -- 车辆信息表

### 数据表、数据项信息获取

通过数据库数据获取的表信息及字段信息如下：

| 数据项id | 数据项code     | 数据项名称   | 所属数据表id | 数据表code  | 数据表名称       |
|----------|----------------|--------------|--------------|-------------|------------------|
| 52       | ProductionDate | 生产日期     | 68           | Car         | 车辆信息表       |
| 54       | CarID          | 车辆ID       | 69           | salesrecord | 车辆销售表       |
| 77       | SalesRecordID  | 销售记录     | 68           | Car         | 车辆信息表       |
| 53       | RecordID       | 销售记录ID   | 69           | salesrecord | 车辆销售表       |
| 55       | SaleDate       | 销售日期     | 69           | salesrecord | 车辆销售表       |
| 64       | UsageDate      | 使用日期     | 72           | PartUsage   | 零件使用情况     |
| 63       | PartID         | 零件ID       | 72           | PartUsage   | 零件使用情况     |
| 58       | PartID         | 零件ID       | 71           | Parts       | 车辆零件表       |
| 50       | Manufacturer   | 制造商       | 68           | Car         | 车辆信息表       |
| 76       | NewDate        | 制造日期     | 71           | Parts       | 车辆零件表       |
| 59       | PartName       | 零件名称     | 71           | Parts       | 车辆零件表       |
| 72       | Color          | 颜色         | 68           | Car         | 车辆信息表       |

### 生成-sql

#### ① 初步 SQL 编写

给定接口的参数 `52,54`，根据链路，涉及的表分别是车辆销售表 (`salesrecord`) 和车辆信息表 (`Car`)，可以编写如下 SQL：

```sql
SELECT ProductionDate, CarID FROM salesrecord, Car
```
#### ① 添加关联表达式

对表达式 77-53:${MainColumn}$ = ${RelationColumn}$ 进行分析，将表达式简化为 77=53，并将对应的数据项 ID 更改为数据项 code，得到 SalesRecordID=RecordID。然后，将这个条件放入 WHERE 子句中，得到最终的 SQL：

```sql
SELECT ProductionDate, CarID FROM salesrecord, Car WHERE SalesRecordID = RecordID
```
