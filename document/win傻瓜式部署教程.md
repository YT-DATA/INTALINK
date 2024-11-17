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

1. 安装完成后，在桌面双击 `Docker Desktop` 图标启动 Docker。  ![image](https://github.com/user-attachments/assets/9eb4590f-59fe-4067-82c6-c1891ccd5975)

2. 启动后会在 Windows 任务栏出现鲸鱼图标。  
3. 等待片刻，当鲸鱼图标静止时，说明 Docker 启动成功，此时可以打开 PowerShell 使用 Docker。

---

## 系统搭建

### 从 DockerHub 上拉取镜像

执行以下命令从 DockerHub 拉取所需镜像：

```bash
docker pull yuantuo/intalink_nginx:1.23.1
```
![image](https://github.com/user-attachments/assets/adc2a0c3-2578-48c6-852a-b73c08ffd4f2)
```bash
docker pull yuantuo/intalink_service:8_latest
```
![image](https://github.com/user-attachments/assets/80c0a2ab-71c4-402d-9eed-aa747c2915dd)

```bash
docker pull yuantuo/intalink_mysql:8.0.27
```
![image](https://github.com/user-attachments/assets/ddc59731-4cab-4932-b4b4-59e421416176)

```bash
docker pull yuantuo/intalink_redis:6.2.6
```
![image](https://github.com/user-attachments/assets/a962be6e-dacd-4c4a-86fa-c64cfaa24a49)

![image](https://github.com/user-attachments/assets/6dfc98ea-1a6d-4076-b041-c3f0f43990c4)


或者可以将 docker_images.zip 中的文件全部解压到文件夹中：

```bash
docker load -i <path>/yuantuo_intalink_mysql_8.0.27.tar
docker load -i <path>/yuantuo_intalink_nginx_1.23.1.tar
docker load -i <path>/yuantuo_intalink_redis_6.2.6.tar
docker load -i <path>/yuantuo_intalink_service_8_latest.tar
```

使用： docker images 命令查看确认是否将这四个tar包都转成了镜像
创建一个文件夹,例D:\docker-compose,创建文件docker-compose.yaml  内容如下(D:\docker-compose\改为新创建文件夹的路径):

```bash
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
```
使用管理员打开powershell，进入到新创建文件夹路径下，执行以下命令：
```bash
docker-compose  up  -d
```

![image](https://github.com/user-attachments/assets/4f14c4c1-02e3-4f6c-a38f-b1c9d0925c82)
以上界面即为启动成功


# 页面访问

根据实际地址访问页面：
```bash
页面访问：http://<IP>/intalinkOpen/index
```

![image](https://github.com/user-attachments/assets/a0d20bc8-6cb0-43ec-b074-93cfbe5cf70c)






