package com.rls;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication(scanBasePackages = { "com.rls" })
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.rls")
public class EventfulApp implements CommandLineRunner {
	
	
    public static void main( String[] args)   {
    	ApplicationContext ctx = SpringApplication.run(EventfulApp.class, args);
    	System.out.println(ctx.getDisplayName());
    }
  
    public void run(String... args) throws Exception {
    }
    
   
     @Bean public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    } 
}


