package dataacces;

import logging.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String[] getDataBaseProperties() {

        String[] properties = new String[3];
        try (InputStream input = new FileInputStream("D:\\Desktop\\Software\\OIS30\\Big Idea\\s3-zweedspesten\\ZweedsPestenShared\\src\\main\\resources\\config.properties")) {


            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            properties[0] = (prop.getProperty("db.url"));
            properties[1] = (prop.getProperty("db.user"));
            properties[2] = (prop.getProperty("db.password"));

        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }

        return properties;
    }
}
