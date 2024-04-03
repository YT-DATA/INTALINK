package com.intalink.configoperations;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 业务基础配置
 *
 * @author whx
 * @date 2024/3/17
 */
@SpringBootApplication
@MapperScan("com.intalink.configoperations.mapper")
public class IntalinkConfigoperationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntalinkConfigoperationsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  基础配置启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
