package com.intalink.configoperations.service.dataTableRelationBasic.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class test {

    public static void main(String[] args) {
        try {
            // 构建ProcessBuilder来启动Python脚本
            ProcessBuilder pb = new ProcessBuilder("python", "D:\\Project\\MyProject\\pythonUtil\\spider.py", "123", "456");
            Process process = pb.start();

            // 获取Python脚本的输出流
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 读取Python脚本的输出
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Python输出: " + line);
            }

            // 等待Python脚本执行完毕并检查返回码
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python脚本成功执行");
            } else {
                System.err.println("Python脚本执行失败");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
