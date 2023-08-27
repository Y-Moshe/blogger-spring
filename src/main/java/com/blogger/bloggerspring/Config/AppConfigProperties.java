package com.blogger.bloggerspring.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("app")
public class AppConfigProperties {

    private String jwtSecretKey;
    private int jwtExpiration;
}
