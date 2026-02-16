package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.ConfigReader;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {
        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setReportName("API Automation Report");
            spark.config().setDocumentTitle("GoRest API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            
            extent.setSystemInfo("Project", "API Automation Framework");
            extent.setSystemInfo("Environment", ConfigReader.get("Environment"));
            extent.setSystemInfo("Base URL", "https://gorest.co.in/public/v2/users");
            extent.setSystemInfo("Executed By", ConfigReader.get("ExecutedBy"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            
        }
        return extent;
    }
}
