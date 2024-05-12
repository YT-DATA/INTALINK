# Intalink Link Interface Call Method

## Table of Contents

- [1. Link Retrieval Interface](#1-link-retrieval-interface)
- [2. Link Retrieval Interface Parameters](#2-link-retrieval-interface-parameters)
- [3. Full Path of the Link Retrieval Interface](#3-full-path-of-the-link-retrieval-interface)

## 1. Link Retrieval Interface

- **Interface Path**: `[IP]:[PORT]/findTableAddress/getAddress`
- **Interface Protocol**: HTTP
- **Interface Type**: GET

## 2. Link Retrieval Interface Parameters

- **Number of Parameters**: 1
- **Parameter Name**: `columns`
- **Parameter Rules**: `[columnId1],[columnId2],[columnId3]...`
- **Meaning of Parameter**: Multiple field IDs connected by a comma `,` that must belong to at least two tables with inter-table relationships.
- **Parameter Example**: `54,52`

### Notes

1. The fields included in the parameter must belong to at least two different tables.
2. These tables must have inter-table relationships; otherwise, the interface will return no results.

## 3. Full Path of the Link Retrieval Interface

- **Full Path Example**: `[IP]:[PORT]/findTableAddress/getAddress?columns=54,52`

This interface is used to obtain the link relationships between tables related to the specified set of field IDs. Using this interface, you can easily query and analyze the associations between tables in the database.
