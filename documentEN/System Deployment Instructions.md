![image](https://github.com/YT-DATA/INTALINK/assets/162880729/ffde2c43-e005-4690-9584-db1a97d9fcc9)
# Intelligent Modeling (Intalink)

## Table of Contents

- 1. Technical Specifications
  - 1.1 Technical Architecture
  - 1.2 Deployment Environment
  - 1.3 Minimum Application Requirements
- 2. Installation Manual
  - MySQL Installation
  - Nginx Installation
- 3. Intelligent Modeling System Deployment

## 1. Technical Specifications

### 1.1 Technical Architecture
![image](https://github.com/YT-DATA/INTALINK/assets/162880729/f3c50e8a-808a-4886-bebe-a0961cf4cec6)


### 1.2 Deployment Environment

- JDK
- MySQL
- Nginx

### 1.3 Minimum Application Requirements

- JDK: 1.8
- MySQL: 5.7
- Nginx: 1.24

## 2. Installation Manual

### MySQL Installation

#### Windows Installation

**Zip Installation:**

1. Extract the zip file to the desired installation directory.
2. Create a `my.ini` file in this directory and configure it as follows:

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

    **Note:** Configure according to your environment.

3. **Initialize the Database:**
    - Run CMD as Administrator, navigate to the MySQL path, and execute:
      ```bash
      mysqld --initialize --console
      ```

4. **Install the Service:**
    - Run `mysqld --install [service-name]`, then execute `net start [service-name]` to start the service.

5. **Change the Password:**
    - Run `mysql -uroot -p`, enter the recorded password, and execute:
      ```sql
      ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'your-password';
      ```

#### Linux Installation

1. **Obtain the Software Package:**
   - Method 1: Upload the package using Xftp.
   - Method 2: Download using wget:
     ```bash
     wget https://cdn.mysql.com//Downloads/MySQL-8.0/mysql-8.0.32-1.el9.x86_64.rpm-bundle.tar
     ```

2. **Install MySQL Packages and Dependencies:**
    ```bash
    yum localinstall mysql-community-server-8.0.32-1.el9.x86_64.rpm \
    mysql-community-client-8.0.32-1.el9.x86_64.rpm \
    mysql-community-common-8.0.32-1.el9.x86_64.rpm \
    mysql-community-client-plugins-8.0.32-1.el9.x86_64.rpm \
    mysql-community-icu-data-files-8.0.32-1.el9.x86_64.rpm \
    mysql-community-libs-8.0.32-1.el9.x86_64.rpm -y
    ```

3. **Start MySQL Service:**
    ```bash
    systemctl restart mysqld
    ```

4. **Change the Password:**
    - Find the default password, log in to MySQL, and change the password:
      ```sql
      ALTER USER 'root'@'localhost' IDENTIFIED BY 'new-password';
      ```

### Nginx Installation

#### Windows Installation

1. **Download Nginx:**
   - Download the Stable version from [nginx.org](https://nginx.org/en/download.html).

2. **Extract and Start:**
   - Extract to a non-Chinese path, navigate to the extraction directory, and start Nginx:
     ```bash
     start nginx
     ```


#### Linux Installation

1. **Download and Transfer Nginx:**
   - Use the `rz` command to upload to the Linux server.

2. **Compile and Install:**
   - Install compilation dependencies, extract, compile, and install:
     ```bash
     yum install gcc pcre-devel openssl openssl-devel
     tar -xvf nginx-1.16.1.tar.gz
     cd nginx-1.16.1
     ./configure
     make
     make install
     ```

3. **Start Nginx:**
   - Navigate to the `sbin` directory of the installation and execute:
     ```bash
     ./nginx
     ```

## 3. Intelligent Modeling System Deployment

### Frontend

1. **Build Command:**
   ```bash
   npm run build:prod
