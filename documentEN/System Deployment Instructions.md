![image](https://github.com/YT-DATA/INTALINK/assets/162880729/ffde2c43-e005-4690-9584-db1a97d9fcc9)# 智慧建模（Intalink）

## 目录

- 一、技术参数
  - 1. 技术架构
  - 2. 部署环境
  - 3. 应用最低配置
- 二、安装手册
  - Mysql安装
  - Nginx安装
- 三、智慧建模系统部署

## 一、技术参数

### 1. 技术架构
![image](https://github.com/YT-DATA/INTALINK/assets/162880729/f3c50e8a-808a-4886-bebe-a0961cf4cec6)


### 2. 部署环境

- JDK
- Mysql
- Nginx

### 3. 应用最低配置

- JDK：1.8
- Mysql：5.7
- Nginx：1.24

## 二、安装手册

### Mysql安装：

#### Windows下安装部署：

**zip安装：**

1. 将压缩包解压到想要安装的目录下；
2. 在该目录下创建`my.ini`文件，并配置如下：

    ```ini
    [mysqld]
    basedir = D:\\soft\\mysql\\mysql-5.7.29
    datadir = D:\\soft\\mysql\\mysql-5.7.29\\data
    port = 3306
    sql_mode = NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
    max_connections = 200
    character-set-server = utf8mb4
    default-storage-engine = INNODB
    default_authentication_plugin = mysql_native_password

    [mysql]
    default-character-set = utf8mb4

    [client]
    port = 3306
    default-character-set = utf8mb4
    ```

    **注意：** 根据自己的环境进行配置。

3. **初始化数据库**：
    - 以管理员身份运行CMD，进入MySQL路径下，执行：
      ```bash
      mysqld --initialize --console
      ```

4. **安装服务**：
    - 运行`mysqld --install [服务名]`，然后执行`net start [服务名]`启动服务。

5. **修改密码**：
    - 运行`mysql -uroot -p`，输入记录的密码，执行：
      ```sql
      ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '你的密码';
      ```

#### Linux下安装部署：

1. **获取软件包**：
   - 方法1：使用Xftp上传软件包；
   - 方法2：使用wget下载：
     ```bash
     wget https://cdn.mysql.com//Downloads/MySQL-8.0/mysql-8.0.32-1.el9.x86_64.rpm-bundle.tar
     ```

2. **安装MySQL软件包及其所有依赖**：
    ```bash
    yum localinstall mysql-community-server-8.0.32-1.el9.x86_64.rpm \
    mysql-community-client-8.0.32-1.el9.x86_64.rpm \
    mysql-community-common-8.0.32-1.el9.x86_64.rpm \
    mysql-community-client-plugins-8.0.32-1.el9.x86_64.rpm \
    mysql-community-icu-data-files-8.0.32-1.el9.x86_64.rpm \
    mysql-community-libs-8.0.32-1.el9.x86_64.rpm -y
    ```

3. **启动MySQL服务**：
    ```bash
    systemctl restart mysqld
    ```

4. **修改密码**：
    - 查找默认密码，登录MySQL，然后更改密码：
      ```bash
      ALTER USER 'root'@'localhost' IDENTIFIED BY '修改后的密码';
      ```

### Nginx安装：

#### Windows安装：

1. **下载Nginx**：
   - 从[nginx.org](https://nginx.org/en/download.html)下载Stable version。

2. **解压并启动**：
   - 解压到非中文路径，进入解压目录，启动Nginx：
     ```bash
     start nginx
     ```

#### Linux安装：

1. **下载并传输Nginx**：
   - 使用`rz`命令上传到Linux服务器。

2. **编译安装**：
   - 安装编译依赖，解压，编译，安装：
     ```bash
     yum install gcc pcre-devel openssl openssl-devel
     tar -xvf nginx-1.16.1.tar.gz
     cd nginx-1.16.1
     ./configure
     make
     make install
     ```

3. **启动Nginx**：
   - 进入安装目录下的`sbin`，执行：
     ```bash
     ./nginx
     ```

## 三、智慧建模系统部署：

### 前端：

1. **打包命令**：
   ```bash
   npm run build:prod
