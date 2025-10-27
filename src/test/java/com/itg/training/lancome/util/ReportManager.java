package com.itg.training.lancome.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ✅ ReportManager
 * مسؤول عن إنشاء وإدارة تقارير ExtentReports
 * الإصدار 5.x (باستخدام ExtentSparkReporter)
 */
public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // 🔹 تهيئة التقرير (تشغيل مرة واحدة قبل بداية التستات)
    public static void initReport() {
        try {
            // إنشاء تقرير جديد داخل test-output
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/AutomationReport.html");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Lancome Test Execution Report");
            sparkReporter.config().setEncoding("UTF-8");
            sparkReporter.config().setTheme(Theme.DARK); // 🎨 وضع مظلم للتقرير

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // معلومات عامة تظهر في التقرير
            extent.setSystemInfo("Tester", "Ahmad Hanani");
            extent.setSystemInfo("Environment", "QA Environment");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
        } catch (Exception e) {
            System.out.println("❌ Failed to initialize Extent Report: " + e.getMessage());
        }
    }

    // 🔹 إنشاء اختبار جديد داخل التقرير
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testThread.set(test);
        return test;
    }

    // 🔹 استرجاع الاختبار الحالي (Thread-Safe)
    public static ExtentTest getTest() {
        return testThread.get();
    }

    // 🔹 إنهاء التقرير وتوليد الملف النهائي
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("📄 Report generated successfully at: test-output/AutomationReport.html");
        }
    }
}
