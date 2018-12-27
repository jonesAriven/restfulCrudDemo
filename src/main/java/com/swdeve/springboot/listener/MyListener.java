package com.swdeve.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("contextInitialized web 容器启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("contextDestroyed web 容器销毁");
    }
}
