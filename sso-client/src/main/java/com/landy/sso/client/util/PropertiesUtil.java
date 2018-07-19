package com.landy.sso.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author landyl
 * @create 4:11 PM 07/19/2018
 */
public class PropertiesUtil {

    private static Properties prop = new Properties();

    public static String getPropertiesValue(String key) {
        if(!prop.containsKey(key)) {
            InputStream inStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("sso-client.properties");
            try {
                prop.load(inStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return prop.getProperty(key);
    }

}
