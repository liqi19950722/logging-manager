package io.github.logging.manager.integration.servlet;

import io.github.logging.manager.LoggingManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class LoggingManagerServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var configuration = sce.getServletContext().getInitParameter("logging-manager-configuration-location");

        var loggingManager = LoggingManager.get(getClass().getClassLoader());
        loggingManager.initialize(configuration);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
