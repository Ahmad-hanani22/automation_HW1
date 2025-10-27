package com.itg.training.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    static {
        try {
            // تحميل ملف الإعدادات من resources
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("❌ Failed to load config.properties file: " + e.getMessage());
        }
    }

    // 🔹 قراءة قيمة حسب الاسم (المفتاح)
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
