
# Installation and Deployment Guide

## Table of Contents
1. Technical Specifications  
   1.1. Technical Architecture  
   1.2. Deployment Environment  
   1.3. Minimum Application Configuration  
2. Installation Manual  
   2.1. Install MySQL  
   2.2. Install Nginx  
3. IntaLink System Deployment  

---

## 1. Technical Specifications

### 1.1. Technical Architecture
![image](https://github.com/user-attachments/assets/0dae9956-b525-47bf-88f0-f1fd8df742b4)

### 1.2. Deployment Environment
- JDK, MySQL, Nginx

### 1.3. Minimum Application Configuration
- **JDK**: 1.8
- **MySQL**: 5.7
- **Nginx**: 1.24

---

## 2. Installation Manual

### 2.1. Install Docker Desktop

**Download Docker Desktop**  
Download link: [Docker Desktop Download](https://www.docker.com/products/docker-desktop)  
Click the **Download Docker Desktop for Mac** button to download the Docker Desktop installer.

**Install Docker Desktop**  
Find Docker in the "Applications" folder and double-click to start.  
Upon first launch, Docker will ask for your system password to obtain administrator rights. After installation, the Docker icon will appear in the top menu bar.
![image](https://github.com/user-attachments/assets/27b1fabc-8e0d-4431-a515-d5c9a7be514a)

**Docker Desktop Interface Overview**  
- **Main Navigation Bar**: Located on the left, providing quick access to various features.  
- **Status Bar**: Located at the top, showing Docker's runtime status and other information.  
- **Main Panel**: Displays detailed information about containers and images.

**Docker Desktop Interface Components**  
| Component        | Description                                                   |
|------------------|---------------------------------------------------------------|
| Containers Tab   | Lists currently running and stopped containers, allows for starting, stopping, or deleting containers. |
| Images Tab       | Lists local images, supports pulling, deleting, or viewing image details. |
| Settings Tab     | Configures various Docker options, such as resource allocation, network settings, etc. |
| Status Indicator | Shows Docker's runtime status (e.g., running, stopped, error).  |

### 2.2. Configure Docker Desktop

**Configure Resources**  
Docker Desktop allows you to configure system resources such as CPU and memory. Follow these steps to configure:
![image](https://github.com/user-attachments/assets/af24272e-16b6-4e2e-afb3-0d1c0cb15cc9)

1. Click the **Settings** (gear icon) in the top right.
2. In the pop-up window, select the **Resources** tab.
3. Adjust the CPU, memory, and swap space settings according to your system configuration.

Recommended resource allocation:
- **CPU**: Controls how many processor cores Docker can use. Increasing this value can improve performance.
- **Memory**: Adjust the maximum amount of memory Docker can use, based on needs.
- **Disk Image Size**: Set the maximum disk space for storing images and containers.

**Network Configuration**  
In the **Network** tab, configure Docker's network settings:
![image](https://github.com/user-attachments/assets/878f82a0-0ed2-4c06-93d7-49e171f3645b)

1. In the settings window, select the **Network** tab.
2. Configure DNS server and proxy settings.

### 2.3. Set Up the System

**Pull Images from Docker Hub**  
Use the following commands to pull the required images:
```bash
docker pull yuantuo/intalink_nginx:1.23.1
```
![image](https://github.com/user-attachments/assets/2fd60fbb-499e-47fa-af03-a8e13632dbec)
```bash
docker pull yuantuo/intalink_service:8_latest
```
![image](https://github.com/user-attachments/assets/14f41aa0-359b-41f0-acb5-591e3089eacf)

```bash
docker pull yuantuo/intalink_mysql:8.0.27
```
![image](https://github.com/user-attachments/assets/5a7fdc9b-abd9-4fde-a86f-c072634773a9)

```bash
 docker pull yuantuo/intalink_redis:6.2.6
```
![image](https://github.com/user-attachments/assets/573927e3-b10e-4807-bdd0-b4b2b658cf02)

Alternatively, you can extract the `docker_images.zip` file and use the following command to load the images into Docker:
```bash
docker load -i <path>/yuantuo_intalink_mysql_8.0.27.tar
docker load -i <path>/yuantuo_intalink_nginx_1.23.1.tar
docker load -i <path>/yuantuo_intalink_redis_6.2.6.tar
docker load -i <path>/yuantuo_intalink_service_8_latest.tar
```

Use the `docker images` command to confirm whether the images have been successfully loaded.

**Create Docker Compose Folder**  
Create a folder (e.g., `D:\docker-compose`) and create a `docker-compose.yaml` file within that folder with the following content:
```bash
version: '2.2'

networks:
  yuantuo:
    driver: bridge
```

```bash
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
```

```bash
  intalink-redis:
    image: yuantuo/intalink_redis:6.2.6
    ports:
      - "16379:6379"
    volumes:
      - D:\docker-compose
edis:/data
    command: --requirepass 2RSD.YTXX.INTALINK
    networks:
      - yuantuo
    restart: always
```

```bash
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
```

```bash
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

### 2.4. Start Containers

Open PowerShell as an administrator, navigate to the directory where the `docker-compose.yaml` file is located, and run the following command to start the containers:
```bash
docker-compose up -d
```
![image](https://github.com/user-attachments/assets/a3a5a39d-25e1-4b04-a4aa-ffbfd1f51a9f)

This command will start the containers, and you can see them running in the Docker Desktop interface.

---

## 3. IntaLink System Deployment

**Access the Application**  
After deployment, you can access the IntaLink system via the following URL:

http://<IP>/intalinkOpen/index

Please replace `<IP>` with your server's actual IP address.
![image](https://github.com/user-attachments/assets/e9755bb7-4f02-41a3-93b0-3f52531fc0b5)
