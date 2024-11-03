# 1. Docker Installation

## Steps

1. **Upload the Compressed Package**
   - Upload the downloaded compressed package (reference file: `docker-27.2.1.tgz`) to the Linux server.

2. **Extract the Compressed Package**
   ```bash
   tar -xvf docker-27.2.1.tgz
   ```

3. **Move the Files**
   - Move the extracted Docker files to the `/usr/bin/` directory:
   ```bash
   mv docker/* /usr/bin/
   ```
   Alternatively, you can use the copy command:
   ```bash
   cp docker/* /usr/bin/
   ```

4. **Register Docker as a Service**
   - Create a `docker.service` file according to your configuration, referring to Attachment 1:
   ```bash
   vi /etc/systemd/system/docker.service
   ```

5. **Start Docker**
   ```bash
   chmod +x /etc/systemd/system/docker.service  # Set permissions
   systemctl daemon-reload                       # Reload the service
   systemctl --now start docker                  # Start Docker
   ```

6. **Check Installation**
   - Use the following command to check if Docker has been installed successfully:
   ```bash
   docker version
   ```
![image](https://github.com/user-attachments/assets/9a85de29-2c4d-4fcd-afee-4ea0440a0dde)

---

# 2. Docker-Compose Installation

## Steps

- Place the `docker-compose` file in the `/usr/local/bin/` directory.
![image](https://github.com/user-attachments/assets/65287966-3ed3-4010-aa5d-afd5579a7a44)

- Check if `docker-compose` has been installed successfully:
```bash
docker-compose --version
```
![image](https://github.com/user-attachments/assets/f669385b-94d4-4357-8a6e-eb26527be543)

---

# 3. System Setup

## Steps

- Pull the required images from Docker Hub:
```bash
docker pull yuantuo/intalink_nginx:1.23.1
docker pull yuantuo/intalink_service:8_latest
docker pull yuantuo/intalink_mysql:8.0.27
docker pull yuantuo/intalink_redis:6.2.6
```
![image](https://github.com/user-attachments/assets/28548507-7bf5-4275-bda6-03e29ad2aba5)

---

# 4. Start the Containers

## Steps

- Place the `docker-compose.yaml` file in the `/home/intalink` directory.
![image](https://github.com/user-attachments/assets/fb145891-34f6-4bee-9b8c-dfce06b8edcf)

- Run Docker Compose in the background:
```bash
docker-compose up -d
```
![image](https://github.com/user-attachments/assets/aa52b3a8-28a5-46f1-be82-ba132b2800df)

---

# 5. Accessing the Page

- Modify according to different addresses (the port is 80):
  - Page access link: http://<IP>/intalinkOpen/index
