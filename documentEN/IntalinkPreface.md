# Preface

Intalink's core is the parsing of database structural relationships. In this project, the basis for relationship parsing is founded on the data tables and data items under the data model, distinct from a purely extracted data dictionary from the database. Such a dictionary, due to business needs, may contain many redundant fields or tables. However, the core of the extracted relationships remains consistent, which can interfere with and impact both relationship extraction and link generation. Therefore, we consider using a standardized data model as the foundational basis for relationship parsing.

The key points to consider are:

- **Core Objective**: Intalink aims to parse database structural relationships effectively.
- **Basis for Parsing**: The project uses data tables and data items under the data model as the primary basis for parsing.
- **Difference from Data Dictionary**: Unlike data dictionaries that are directly extracted from databases and may contain redundant data due to business requirements, Intalink focuses on the core relationships.
- **Impact of Redundancy**: Redundant fields and tables in the data dictionary can interfere with relationship extraction and link generation.
- **Standardized Data Model**: To minimize inconsistencies and improve parsing accuracy, Intalink utilizes a standardized data model as the prelude to relationship parsing.

This approach ensures that relationship parsing and link generation are based on the most relevant and standardized data, thereby enhancing the effectiveness and accuracy of the entire process.
