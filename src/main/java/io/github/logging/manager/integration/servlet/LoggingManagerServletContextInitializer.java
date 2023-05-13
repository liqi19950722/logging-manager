package io.github.logging.manager.integration.servlet;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.util.Set;

public class LoggingManagerServletContextInitializer  implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        ctx.addListener(new LoggingManagerServletContextListener());
    }
}
