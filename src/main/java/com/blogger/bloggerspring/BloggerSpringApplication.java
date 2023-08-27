package com.blogger.bloggerspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.blogger.bloggerspring.Config.AppConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigProperties.class)
public class BloggerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerSpringApplication.class, args);
	}

}
