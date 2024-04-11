# INTALINK:数据集成与智能关联分析平台


<p align="center">
  <a href="https://github.com/yt-data/intalink/stargazers">
    <img src="https://img.shields.io/github/stars/yt-data/intalink.svg?style=social&label=Stars" alt="GitHub stars"/>
  </a>
  <a href="https://github.com/yt-data?tab=followers">
    <img src="https://img.shields.io/github/followers/yt-data.svg?style=social&label=Follow" alt="GitHub followers"/>
  </a>
  <a href="https://discord.gg/n53PxSrh">
    <img src="https://img.shields.io/badge/INTALINK-Join%20intalink-blue.svg?style=social&logo=discord" alt="Join Discord"/>
  </a>
  <a href="你的Stack Overflow链接">
    <img src="https://img.shields.io/badge/Stack%20Overflow-Ask%20intalink-orange.svg?style=social&logo=stack-overflow" alt="Stack Overflow"/>
  </a>
</p>

<p align="center">
  <a href="https://github.com/YT-DATA/INTALINK/blob/main/README.md">English</a> |
  <a href="https://discord.gg/n53PxSrh">Discord</a> |
  <a href="https://www.yuque.com/chenshiyi-ur8az/tt35ml">文档</a> |
  <a href="#联系我们">微信</a> |  
  <a href="https://github.com/yt-data/community/blob/main/README.md">社区</a> |
</p>

# INTALINK 是什么？
🤖️INTALINK是一个智能数据集成工具，它自动识别并构建数据表之间的关联关系，支持多源异构数据环境，允许用户轻松执行数据查询，无需了解底层数据库逻辑，简化多表数据集成的过程。

# 效果演示
🔥🔥🔥 [INTALINK开源演示1.0.3](http://39.106.28.179/intalink/login?redirect=/index)

![image](https://github.com/yt-data/INTALINK/assets/162880729/f76d6a0e-2eb1-4dc9-9a6d-dff01ab9446d)


# 目录

- [架构方案](#架构方案)
- [安装](#安装)
- [特性简介](#特性一览)
- [贡献](#贡献)
- [联系我们](#联系我们)


# 架构方案

![image](https://github.com/yt-data/INTALINK/assets/162880729/efe18b27-5f78-475c-afe0-7c37cbf5f3d6)

## 核心能力主要有以下几个部分:
- 自动表间关系生成：自动探测和构建两个数据表之间的关系，通过基础表间关系构建全局数据表拓扑结构。

- 查询语句自动构建：用户只需指明所需的数据项，软件即可生成正确的SQL查询语句，智能添加用于数据关联的中间表，避免了手工编写的复杂性。

- 数据链路优化分析：多链路、多角度、多方法数据链路质量评价，智能推荐优化的数据查询方法。

- 降低技术门槛：数据开发人员不再需要繁琐的业务调研和数据资源梳理，而数据运维人员则减少了技术支持的工作量。

- 增强数据治理：帮助数据管理人员快速识别问题和做出决策，尤其是在面对数据孤岛的问题时。

- 功能增强：对现有软件功能进行升级，例如将BI工具中的手工分析转变为智能分析，以及自动化数据迁移和查询功能。





# 安装

教程
- [快速开始](https://www.yuque.com/chenshiyi-ur8az/tt35ml/biecgo4ey3w102nh)
  - [源码安装-v0.8](#特性一览)
- [使用手册-v0.8](https://www.yuque.com/chenshiyi-ur8az/tt35ml/vgk4qc60g1bb37v1)
  - [知识库-v0.8](#特性一览)
  - [数据对话-v0.8](#特性一览)
  - [Excel对话-v0.8](#特性一览)
  -[数据库对话-v0.8](#特性一览)
- [进阶教程-v0.8](#特性一览)
  - [智能应用使用-v0.8](#特性一览)
  - [多模型管理-v0.8](#特性一览)
  - [命令行使用-v0.8](#特性一览)
- [模型服务部署-v0.8](#特性一览)
  - [单机部署-v0.8](#特性一览)
  - [集群部署-v0.8](#特性一览)
- [如何Debug-v0.8](#特性一览)
- [AWEL-v0.8](#特性一览)
- [FAQ-v0.8](#特性一览)




# 特性一览

## 智能数据集成与关联分析
- 实现多表数据集成，自动识别和构建数据表之间的关联关系。
- 简化了需要中间表进行数据关联的复杂性，自动化构建查询语句，如基于多表间关系的SQL查询生成。

## 数据关联引擎
- 通过数据分析，自动化、智能化提供数据集成应用的关联逻辑。
- 支持跨数据表内容的智能集成，无需用户深入理解业务逻辑或数据库知识。

## 自助式数据探索
- 允许用户自主尝试和探索不同的数据组合，提供数据应用的自由化。
- 用户界面友好，支持按需使用数据，减少对开发人员依赖。

## 数据治理
- 为管理人员提供数据关联和治理工具，以判断数据孤岛并执行数据治理任务。
- 数据管理部分包括保证数据完整性和可关联性的技术支持。

## 业务效率提升
- 为数据开发人员减少业务调研和数据资源整理的工作量。
- 数据运维人员和管理人员可以快速聚焦问题，减少了技术支持工作。

## 技术升级与兼容性
- 支持开源和API代理模型，提供包括但不限于LLaMA/LLaMA2、Baichuan、ChatGLM等模型的集成。
- 提升现有软件功能，包括自动化的BI工具分析和数据仓库迁移。

## 数据隐私与安全
- 采用私有化大模型和代理脱敏技术，确保数据处理的隐私安全。

## 支持的数据源
- 提供与Excel、数据库、数据仓等多种数据源的自然语言交互能力。

# 贡献

### 更加详细的贡献指南请参考[如何贡献](https://github.com/yt-data/community/blob/main/README.md)。

这是一个用于数据库的复杂且创新的工具, 我们的项目也在紧急的开发当中, 会陆续发布一些新的feature。如在使用当中有任何具体问题, 优先在项目下提issue, 如有需要, 请联系如下微信，我会尽力提供帮助，同时也非常欢迎大家参与到项目建设中。

## Licence

Apache License 2.0

# 联系我们
- 微信：YT18526434675
- ![Uploading image.png…]()


- [产品官网](https://www.idataops.com.cn/h-col-110.html)
