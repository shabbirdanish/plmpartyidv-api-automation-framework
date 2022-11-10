package com.santander.api.test.suites;

import com.santander.api.automation.hooks.KafkaHook;
import com.santander.test.automation.aws.AWSHelper;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
     //   tags = "@dataservice",
//        publish = true,
        plugin = {"pretty", "html:target/reports/cucumber.html", "json:target/cucumber-report.json", "junit:target/reports/cucumber.xml"},
        glue = {"classpath:com/santander/api/test/steps"},
        features = "src/test/java/com/santander/api/test/features")

public class TestRunner {

    @AfterClass
    public static void sendReport() {
        try {
            System.out.println("Attempting to upload report to S3");
            String accessPoint = "http://nprtaasbin00.sksvemecs0165711.santanderuk.pre.corp/bprtaasbin00/";

            AWSHelper awsHelper = new AWSHelper();
            String objectkey = awsHelper.uploadCucumberReportToS3("nitroapicage");

            System.out.println("Report Link: " + accessPoint + objectkey);
        } catch (Exception ex) {
            System.out.println("S3 upload has failed:" + ex.getMessage());
        }
    }
}
