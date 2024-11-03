# 一、Docker安装

## 步骤

1. **上传压缩包**
   - 将下载的压缩包（文件参考 `docker-27.2.1.tgz`）上传到Linux服务器上。

2. **解压压缩包**
   ```bash
   tar -xvf docker-27.2.1.tgz
   ```

3. **移动文件**
   - 将解压出来的Docker文件内容移动到 `/usr/bin/` 目录下：
   ```bash
   mv docker/* /usr/bin/
   ```
   或者使用复制命令：
   ```bash
   cp docker/* /usr/bin/
   ```

4. **注册Docker为服务**
   - 根据自己的配置编写 `docker.service`，参考附件1：
   ```bash
   vi /etc/systemd/system/docker.service
   ```

5. **启动Docker**
   ```bash
   chmod +x /etc/systemd/system/docker.service  # 设置权限
   systemctl daemon-reload                       # 重新加载服务
   systemctl --now start docker                  # 启动Docker
   ```

6. **检查安装**
   - 使用以下命令检查Docker是否安装成功：
   ```bash
   docker version
   ```
![image](https://github.com/user-attachments/assets/9a85de29-2c4d-4fcd-afee-4ea0440a0dde)

---

# 二、Docker-compose安装

## 步骤

- 将 `docker-compose` 文件放在 `/usr/local/bin/` 目录下。
![image](https://github.com/user-attachments/assets/65287966-3ed3-4010-aa5d-afd5579a7a44)

- 检查 `docker-compose` 是否安装成功：
```bash
docker-compose --version
```
![image](https://github.com/user-attachments/assets/f669385b-94d4-4357-8a6e-eb26527be543)


---

# 三、系统搭建

## 步骤

- 从Docker Hub上拉取所需镜像：
```bash
docker pull yuantuo/intalink_nginx:1.23.1
docker pull yuantuo/intalink_service:8_latest
docker pull yuantuo/intalink_mysql:8.0.27
docker pull yuantuo/intalink_redis:6.2.6
```
![image](https://github.com/user-attachments/assets/28548507-7bf5-4275-bda6-03e29ad2aba5)

---

# 四、启动容器

## 步骤

- 将 `docker-compose.yaml` 文件放在 `/home/intalink` 目录下。
- ![image](https://github.com/user-attachments/assets/fb145891-34f6-4bee-9b8c-dfce06b8edcf)


- 在后台运行Docker Compose：
```bash
docker-compose up -d
```
![image](https://github.com/user-attachments/assets/aa52b3a8-28a5-46f1-be82-ba132b2800df)

---

# 五、页面访问

- 根据不同的地址进行修改（端口为80）：
  - 页面访问链接：http://<IP>/intalinkOpen/index
