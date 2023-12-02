package com.superngb.userservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.superngb.userservice",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*Model"
        )
)
public class ComponentScanConfig {
}
