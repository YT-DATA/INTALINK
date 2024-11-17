# 技术参数

---

## 1. 技术架构
![image](https://github.com/user-attachments/assets/ac2e8b81-ae2a-4994-b3bd-22e1960d5a9c)

---

## 2. 部署环境

- **JDK**  
- **MySQL**  
- **Nginx**  

---

## 3. 应用最低配置

- **JDK**: 1.8  
- **MySQL**: 5.7  
- **Nginx**: 1.24  

---

# 安装手册

---

## Docker 安装

### 系统要求

- **Docker Desktop for Windows** 支持 64 位版本的 Windows 10 Pro，且必须开启 Hyper-V（若版本为 v1903 及以上则无需开启 Hyper-V），或者 64 位版本的 Windows 10 Home v1903 及以上版本。

### 安装

1. 点击以下链接下载 Docker Desktop for Windows：  
   [Docker Desktop Installer.exe](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe)  
2. 下载后双击 `Docker Desktop Installer.exe` 开始安装。

### 运行

1. 安装完成后，在桌面双击 `Docker Desktop` 图标启动 Docker。  
2. 启动后会在 Windows 任务栏出现鲸鱼图标。  
3. 等待片刻，当鲸鱼图标静止时，说明 Docker 启动成功，此时可以打开 PowerShell 使用 Docker。

---

## 系统搭建

### 从 DockerHub 上拉取镜像

执行以下命令从 DockerHub 拉取所需镜像：

```bash
docker pull yuantuo/intalink_nginx:1.23.1
docker pull yuantuo/intalink_service:8_latest
docker pull yuantuo/intalink_mysql:8.0.27
docker pull yuantuo/intalink_redis:6.2.6
使用 docker_images.zip 解压镜像
将 docker_images.zip 文件中的内容全部解压到某个文件夹中。

使用以下命令将 .tar 包加载为 Docker 镜像：

bash
复制代码
docker load -i <path>/yuantuo_intalink_mysql_8.0.27.tar
docker load -i <path>/yuantuo_intalink_nginx_1.23.1.tar
docker load -i <path>/yuantuo_intalink_redis_6.2.6.tar
docker load -i <path>/yuantuo_intalink_service_8_latest.tar
注意: <path> 是 .tar 文件所在的文件夹路径，例如 /home/user/docker_images。

使用以下命令检查是否成功加载所有镜像：

bash
复制代码
docker images
配置 docker-compose
创建一个文件夹，例如 D:\docker-compose。

在该文件夹中创建 docker-compose.yaml 文件，并添加以下内容（请将 D:\docker-compose 替换为您的实际路径）：

yaml
复制代码
version: '2.2'

networks:
  yuantuo:
    driver: bridge

services:
  intalink-mysql:
    image: yuantuo/intalink_mysql:8.0.27
    ports:
      - "3306:3306"
    volumes:
      - D:\docker-compose\mysql:/var/lib/mysql
    networks:
      - yuantuo
    restart: always

  intalink-redis:
    image: yuantuo/intalink_redis:6.2.6
    ports:
      - "16379:6379"
    volumes:
      - D:\docker-compose\redis:/data
    command: --requirepass 2RSD.YTXX.INTALINK
    networks:
      - yuantuo
    restart: always

  intalink-service:
    image: yuantuo/intalink_service:8_latest
    ports:
      - "9207:9207"
    depends_on:
      - intalink-mysql
      - intalink-redis
    networks:
      - yuantuo
    restart: always

  intalink-nginx:
    image: yuantuo/intalink_nginx:1.23.1
    ports:
      - "80:80"
    depends_on:
      - intalink-service
    networks:
      - yuantuo
    restart: always
启动服务
使用管理员权限打开 PowerShell。

进入您创建的文件夹路径，例如：D:\docker-compose。

执行以下命令启动容器：

bash
复制代码
docker-compose up -d
启动成功后，您将看到以下界面提示服务已成功启动。

页面访问
根据实际地址访问页面：

plaintext
复制代码
http://<IP>/intalinkOpen/index
注意: 请将 <IP> 替换为您的服务器 IP 地址。

yaml
复制代码

---

这就是完整的 Markdown 文件内容，确保所有细节都已经包括在内。如果还有任何进一步需求，请告诉我！





