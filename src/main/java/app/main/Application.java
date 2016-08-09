package app.main;

import javax.faces.webapp.FacesServlet;

import app.jsf.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zk.ui.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SaifulArifin on 3/11/2016.
 */
@SpringBootApplication
@ComponentScan(basePackages={"app.*"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Start app.main.Application");
    }

    // JSF Setting
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[] { Application.class, Initializer.class});
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
        return servletRegistrationBean;
    }

    /*
	 * ZK servlets
	 * ZK Setting
	 */
    @Bean
    public ServletRegistrationBean dHtmlLayoutServlet() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("update-uri", "/zkau");
        DHtmlLayoutServlet dHtmlLayoutServlet = new DHtmlLayoutServlet();
        ServletRegistrationBean reg = new ServletRegistrationBean(dHtmlLayoutServlet, "*.zul");
        reg.setLoadOnStartup(1);
        reg.setInitParameters(params);
        return reg;
    }

    @Bean
    public ServletRegistrationBean dHtmlUpdateServlet() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("update-uri", "/zkau/*");
        ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
        reg.setLoadOnStartup(2);
        reg.setInitParameters(params);
        return reg;
    }

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener();
    }
}
