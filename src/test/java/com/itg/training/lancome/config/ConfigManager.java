package com.itg.training.lancome.config;

import com.itg.training.lancome.util.ExcelReader;

public class ConfigManager {

    private static final String CONFIG_PATH = "src/test/resources/testdata/config.xlsx";
    private static String driverPath = "C:\\\\Drivers\\\\chromedriver.exe";
    private static String siteURL = "https://certwcs.ballarddesigns.com/?aka_bypass=5C73514EE7A609054D81DE61DD9CA3D6";
    private static String validEmail;
    private static String validPassword;

    static {
        loadConfigFromExcel();
    }

    private static void loadConfigFromExcel() {
        try {
            ExcelReader excel = new ExcelReader(CONFIG_PATH, "Config");
            
            // اقرأ أول مستخدم (السطر الثاني في الإكسل)
            validEmail = excel.getCellData(1, 1);      // العمود B
            validPassword = excel.getCellData(1, 2);   // العمود C
            
            System.out.println("✅ Loaded from Excel successfully:");
            System.out.println("📧 Email: " + validEmail);
            System.out.println("🔑 Password: " + validPassword);
            
        } catch (Exception e) {
            System.out.println("❌ Failed to load Excel configuration: " + e.getMessage());
        }
    }

    public static String getDriverPath() { return driverPath; }
    public static String getSiteURL() { return siteURL; }
    public static String getValidEmail() { return validEmail; }
    public static String getValidPassword() { return validPassword; }
}
