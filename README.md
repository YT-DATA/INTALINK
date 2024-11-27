# INTALINK: Data Integration and Intelligent Association Analysis Platform

<p align="center">
  <a href="https://github.com/yt-data/intalink/stargazers">
    <img src="https://img.shields.io/github/stars/yt-data/intalink.svg?style=social&label=Stars" alt="GitHub stars"/>
  </a>
  <a href="https://github.com/yt-data?tab=followers">
    <img src="https://img.shields.io/github/followers/yt-data.svg?style=social&label=Follow" alt="GitHub followers"/>
  </a>
  <a href="https://discord.com/invite/FvhqEZ6z95">
    <img src="https://img.shields.io/badge/INTALINK-Join%20intalink-blue.svg?style=social&logo=discord" alt="Join Discord"/>
  </a>
  <a href="your Stack Overflow link">
    <img src="https://img.shields.io/badge/Stack%20Overflow-Ask%20intalink-orange.svg?style=social&logo=stack-overflow" alt="Stack Overflow"/>
  </a>
</p>

<p align="center">
  <a href="https://github.com/YT-DATA/INTALINK/blob/main/README.zh.md">Chinese</a> |
  <a href="https://discord.com/invite/FvhqEZ6z95">Discord</a> |
  <a href="https://www.yuque.com/chenshiyi-ur8az/tt35ml">Documentation</a> |
  <a href="#contact-us">WeChat</a> |  
  <a href="https://github.com/yt-data/community/blob/main/README.md">Community</a> |
</p>

## What is INTALINK?
🤖️ INTALINK is an intelligent data integration tool that automatically identifies and builds relationships between data tables. It supports multi-source heterogeneous data environments, enabling users to easily perform data queries without needing to understand the underlying database logic, thus simplifying the multi-table data integration process.

## Demo
🔥🔥🔥 [INTALINK Open Source Demo 1.0.3](http://39.106.28.179/intalink/login?redirect=/index)

![image](https://github.com/user-attachments/assets/e23c010f-4246-4478-90fe-fef757c3d85c)


## Table of Contents
- [Architecture Solution](#architecture-solution)
- [instruction](#instruction)
- [Feature Overview](#feature-overview)
- [Contribution](#contribution)
- [Contact Us](#contact-us)

### Architecture Solution

![图片1](https://github.com/user-attachments/assets/ef450562-9821-415b-bd57-15a1549515c7)



#### Core Capabilities include:
- **Automatic Inter-table Relationship Generation**: Automatically detects and constructs relationships between two data tables, building a global data table topology based on foundational inter-table relationships.
- **Automatic Query Statement Construction**: Users specify the needed data items, and the software generates the correct SQL query statements, intelligently incorporating intermediate tables for data association, thus avoiding the complexity of manual writing.
- **Data Linkage Optimization Analysis**: Evaluates data linkage quality from multiple pathways, angles, and methods, and intelligently recommends optimized data query methods.
- **Lowering Technical Barriers**: Reduces the need for data developers to engage in extensive business research and data resource organization, and decreases the workload for data operations personnel.
- **Enhanced Data Governance**: Assists data managers in quickly identifying issues and making decisions, especially in dealing with data silo problems.
- **Functionality Enhancement**: Upgrades existing software features, such as transforming manual analyses in BI tools into intelligent analyses, and automating data migration and query functions.

### instruction

- [Preface](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/IntalinkPreface.md)
- [Intalink Table Structure Explanation](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Intalink%20Table%20Structure%20Explanation.md)
- [Intalink (Open Source Edition Data Table Structure)](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Intalink%20(Open%20Source%20Edition%20Data%20Table%20Structure))
- [Data Relationship Expression Rules Explanation](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Data%20Relationship%20Expression%20Rules%20Explanation.md)
- [Intalink API Usage Methods](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Intalink%20API%20Usage%20Methods.md)
- [Intalink Link Data Explanation](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Intalink%20Link%20Data%20Explanation.md)
- [System Deployment Instructions](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/System%20Deployment%20Instructions.md)
- [Intalink Core Capability Video - v0.8](#feature-overview)


### Feature Overview

#### Intelligent Data Integration and Association Analysis
- Achieves multi-table data integration, automatically identifying and constructing relationships between data tables.
- Simplifies the complexity of needing intermediate tables for data association, automates query statement construction, such as SQL query generation based on relationships between multiple tables.

#### Data Association Engine
- Provides automated, intelligent association logic for data integration applications through data analysis.
- Supports intelligent integration across data table contents without requiring users to deeply understand business logic or database knowledge.

#### Self-service Data Exploration
- Allows users to independently try and explore different data combinations, providing freedom in data application.
- User-friendly interface, supporting data use as needed, reducing dependency on developers.

#### Data Governance
- Provides data association and governance tools for managers to identify data silos and execute data governance tasks.
- Includes technical support to ensure data integrity and associability.

#### Business Efficiency Improvement
- Reduces the workload of data developers in business research and data resource organization.
- Enables data operations and management personnel to quickly focus on issues, reducing technical support work.

#### Technology Upgrade and Compatibility
- Supports open source and API proxy models, including integration of models such as LLaMA/LLaMA2, Baichuan, ChatGLM, and more.
- Enhances existing software functionalities, including automated BI tool analysis and data warehouse migration.

#### Data Privacy and Security
- Utilizes private big models and proxy anonymization technologies to ensure privacy and security in data processing.

#### Supported Data Sources
- Provides natural language interaction capabilities with various data sources including Excel, databases, data warehouses, etc.
- ---  

- # 🚀 Quick Deployment Guide

To help everyone quickly get started with deploying the INTALINK platform, we have prepared a detailed **Quick Deployment Guide**. Whether you are a developer or a new user, you can easily complete the deployment by following the steps.

- [**Click here to view the Win Quick Deployment Guide.**](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Win%20Quick%20Deployment%20Guide.md)
- [**Click here to view the Linux Quick Deployment Guide.**](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Linux%20Quick%20Deployment%20Guide.md)
- [**Click here to view the Mac Quick Deployment Guide.**](https://github.com/YT-DATA/INTALINK/blob/main/documentEN/Mac%20Quick%20Deployment%20Guide.md)


### Contribution

#### For a more detailed contribution guide, please refer to [How to Contribute](https://github.com/yt-data/community/blob/main/README.md).

This tool is complex and innovative for databases, and our project is in urgent development, with new features being released continuously. If you encounter any specific issues during use, please raise an issue under the project first. If necessary, contact the following WeChat, and I will do my best to help. We also warmly welcome everyone to participate in the project construction.

### License

GNU Affero General Public License v3.0

### Contact Us
- ![企业微信](https://github.com/YT-DATA/INTALINK/assets/162880729/0f34328a-3d8a-4271-b199-96e7bc5e2ca6)

- [Official Website](https://www.idataops.com.cn/h-col-110.html)
