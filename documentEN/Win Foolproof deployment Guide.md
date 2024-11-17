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


