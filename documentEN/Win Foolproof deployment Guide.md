# Technical Specifications

---

## 1. Technical Architecture
![image](https://github.com/user-attachments/assets/ac2e8b81-ae2a-4994-b3bd-22e1960d5a9c)

---

## 2. Deployment Environment

- **JDK**  
- **MySQL**  
- **Nginx**  

---

## 3. Minimum Application Requirements

- **JDK**: 1.8  
- **MySQL**: 5.7  
- **Nginx**: 1.24  

---

# Installation Manual

---

## Docker Installation

### System Requirements

- **Docker Desktop for Windows**  
  Supports 64-bit Windows 10 Pro with Hyper-V enabled (not required for versions v1903 or higher) or 64-bit Windows 10 Home v1903 or higher.

### Installation Steps

1. Download Docker Desktop for Windows from the following link:  
   [Docker Desktop Installer.exe](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe)  
2. Double-click the downloaded `Docker Desktop Installer.exe` to begin the installation.

### Run Docker

1. After installation, double-click the `Docker Desktop` icon on the desktop to launch Docker.  
   ![image](https://github.com/user-attachments/assets/9eb4590f-59fe-4067-82c6-c1891ccd5975)

2. After starting, the whale icon will appear in the Windows taskbar.  
3. Wait until the whale icon becomes stationary, indicating Docker has started successfully. You can now open PowerShell to use Docker.

---

## System Setup

### Pull Required Images from DockerHub

Run the following commands to pull the required images from DockerHub:

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


Alternatively, extract all files from docker_images.zip into a folder and load them using:

```bash
docker load -i <path>/yuantuo_intalink_mysql_8.0.27.tar
docker load -i <path>/yuantuo_intalink_nginx_1.23.1.tar
docker load -i <path>/yuantuo_intalink_redis_6.2.6.tar
docker load -i <path>/yuantuo_intalink_service_8_latest.tar
```

Create docker-compose.yaml File
Create a folder, e.g., D:\docker-compose.
Inside the folder, create a file named docker-compose.yaml with the following content (replace D:\docker-compose\ with the path to your folder):

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
Start Docker Compose
Open PowerShell as Administrator.
Navigate to the folder where the docker-compose.yaml file is located.
Run the following command:
```bash
docker-compose  up  -d
```

![image](https://github.com/user-attachments/assets/4f14c4c1-02e3-4f6c-a38f-b1c9d0925c82)
Once the above interface is displayed, the services have started successfully.


# Access the Application

Visit the application page using the actual address:
```bash
http://<IP>/intalinkOpen/index
```

![image](https://github.com/user-attachments/assets/a0d20bc8-6cb0-43ec-b074-93cfbe5cf70c)


