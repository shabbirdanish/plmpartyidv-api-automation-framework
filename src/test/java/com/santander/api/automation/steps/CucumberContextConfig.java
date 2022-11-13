package com.santander.api.automation.steps;

import com.santander.api.automation.config.ApplicationConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration

@SpringBootTest(classes= ApplicationConfig.class )

public class CucumberContextConfig {
}
