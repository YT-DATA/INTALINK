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

![image](https://github.com/user-attachments/assets/5aa47014-4e8c-4f7a-8eb3-fea91c085268)


# 目录

- [架构方案](#架构方案)
- [安装](#使用说明)
- [特性简介](#特性一览)
- [贡献](#贡献)
- [联系我们](#联系我们)


# 架构方案

![图片2](https://github.com/user-attachments/assets/867ddb40-db07-4d7c-984e-a171abda378c)


## 核心能力主要有以下几个部分:
- 自动表间关系生成：自动探测和构建两个数据表之间的关系，通过基础表间关系构建全局数据表拓扑结构。

- 查询语句自动构建：用户只需指明所需的数据项，软件即可生成正确的SQL查询语句，智能添加用于数据关联的中间表，避免了手工编写的复杂性。

- 数据链路优化分析：多链路、多角度、多方法数据链路质量评价，智能推荐优化的数据查询方法。

- 降低技术门槛：数据开发人员不再需要繁琐的业务调研和数据资源梳理，而数据运维人员则减少了技术支持的工作量。

- 增强数据治理：帮助数据管理人员快速识别问题和做出决策，尤其是在面对数据孤岛的问题时。

- 功能增强：对现有软件功能进行升级，例如将BI工具中的手工分析转变为智能分析，以及自动化数据迁移和查询功能。





# 使用说明

- [前言](https://github.com/YT-DATA/INTALINK/blob/main/document/Intalink%E5%89%8D%E8%A8%80.md)
- [Intalink表结构说明](https://github.com/YT-DATA/INTALINK/blob/main/document/Intalink%E8%A1%A8%E7%BB%93%E6%9E%84%E8%AF%B4%E6%98%8E.md)
- [Intalink(开源版数据表结构)](https://github.com/YT-DATA/INTALINK/blob/main/document/Intalink(%E5%BC%80%E6%BA%90%E7%89%88%E6%95%B0%E6%8D%AE%E8%A1%A8%E7%BB%93%E6%9E%84).md)
- [数据关系表达式规则说明 ](https://github.com/YT-DATA/INTALINK/blob/main/document/%E6%95%B0%E6%8D%AE%E5%85%B3%E8%81%94%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%A7%84%E5%88%99%E8%AF%B4%E6%98%8E%E6%96%87%E6%A1%A3.md)
- [Intalink接口调用方法](https://github.com/YT-DATA/INTALINK/blob/main/document/%E9%93%BE%E8%B7%AF%E6%8E%A5%E5%8F%A3%E8%B0%83%E7%94%A8%E6%96%87%E6%A1%A3.md)
- [Intalink链路数据说明](https://github.com/YT-DATA/INTALINK/blob/main/document/Intalink%E9%93%BE%E8%B7%AF%E6%95%B0%E6%8D%AE%E8%AF%B4%E6%98%8E.md)
- [系统部署说明](https://github.com/YT-DATA/INTALINK/blob/main/document/intalink%E7%B3%BB%E7%BB%9F%E9%83%A8%E7%BD%B2%E8%AF%B4%E6%98%8E.md)
- [intalink核心能力视频-v0.8](#特性一览)




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

- ---

# 🚀 傻瓜式部署使用教程

为了方便大家快速上手部署INTALINK平台，我们特地准备了详细的 **傻瓜式部署使用教程**。无论你是开发人员还是新手用户，都可以按照步骤轻松完成部署。

- [**点击这里查看win傻瓜式部署使用教程**](https://github.com/YT-DATA/INTALINK/blob/main/document/win%E5%82%BB%E7%93%9C%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B.md)
- [**点击这里查看linux傻瓜式部署使用教程**](https://github.com/YT-DATA/INTALINK/blob/main/document/%E5%82%BB%E7%93%9C%E5%BC%8F%E9%83%A8%E7%BD%B2%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B.md)

# 贡献

### 更加详细的贡献指南请参考[如何贡献](https://github.com/yt-data/community/blob/main/README.md)。

这是一个用于数据库的复杂且创新的工具, 我们的项目也在紧急的开发当中, 会陆续发布一些新的feature。如在使用当中有任何具体问题, 优先在项目下提issue, 如有需要, 请联系如下微信，我会尽力提供帮助，同时也非常欢迎大家参与到项目建设中。

## Licence

GNU Affero General Public License v3.0

# 联系我们
- ![企业微信](https://github.com/YT-DATA/INTALINK/assets/162880729/1c739c36-160c-474d-9f9b-c43e46dd1159)



- [产品官网](https://www.idataops.com.cn/h-col-110.html)
