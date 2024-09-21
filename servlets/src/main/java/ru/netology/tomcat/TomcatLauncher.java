package ru.netology.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.VersionLoggerListener;
import ru.netology.servlet.MainServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TomcatLauncher {
    public static void main(String aArgs[]) {
        try {
            final Tomcat tomcat = new Tomcat();
            final Path baseDir = Files.createTempDirectory("tomcat");
            baseDir.toFile().deleteOnExit();
            tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

            final Connector connector = new Connector();
            connector.setPort(8080);
            tomcat.setConnector(connector);

            Context ctx = tomcat.addContext("", null);
            Wrapper servlet = Tomcat.addServlet(ctx, "MainServlet", new MainServlet());
            servlet.setLoadOnStartup(1);
            servlet.addMapping("/");
            //tomcat.getHost().setAppBase(".");
            //tomcat.addWebapp("",".");

            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
