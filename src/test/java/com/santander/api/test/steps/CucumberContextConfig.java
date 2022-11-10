package com.santander.api.test.steps;

import com.santander.api.automation.config.ApplicationConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@CucumberContextConfiguration

@SpringBootTest(classes= ApplicationConfig.class )

public class CucumberContextConfig {
}
