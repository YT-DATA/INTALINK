package com.intalink.configoperations.listener;

import com.intalink.configoperations.service.relationshipAnalysis.RelationshipAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    public RelationshipAnalysisService relationshipAnalysisService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 应用启动完成时的逻辑
        System.out.println("应用程序已经启动，即将执行相关方法!");

        //启动数据关系分析
        relationshipAnalysisService.start();
    }
}
