package ru.netology;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.DataInput;
import java.lang.annotation.Annotation;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.netology");
        context.refresh();

        final var servlet = new DispatcherServlet(context);
        final var registration = servletContext.addServlet("MainServlet", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
