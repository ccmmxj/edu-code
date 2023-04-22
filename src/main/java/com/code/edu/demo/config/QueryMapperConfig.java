package com.code.edu.demo.config;

import com.code.edu.demo.anno.QueryMapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@QueryMapperScan("com.code.edu.demo")
public class QueryMapperConfig {
}
