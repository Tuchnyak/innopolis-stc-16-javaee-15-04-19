package ru.innopolis.tasks.hw14.properties;

import ru.innopolis.tasks.hw14.logger.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Утильный класс для получения настроек системы из файла
 */
public class PropsUtil {

    private static final Properties PROPERTIES = new Properties();

    /**
     * Ключ значения для установки настройки успешности загрузки файла
     */
    public static final String ERROR_KEY = "errorCode";
    public static final String ERROR_CODE_SUCCESS = "0";
    public static final String ERROR_CODE_FAIL = "1";

    private static final String PROPERTIES_FILE = "./src/ru/innopolis/tasks/hw14/properties/props.properties";

    static {
        try {
            PROPERTIES.load(new FileInputStream(PROPERTIES_FILE));
            PROPERTIES.setProperty(ERROR_KEY, ERROR_CODE_SUCCESS);
            LoggerUtil.LOGGER.info(">>> Properties have been loaded");
        } catch (IOException e) {
            PROPERTIES.setProperty(ERROR_KEY, ERROR_CODE_FAIL);
            LoggerUtil.LOGGER.error(">>> Problems during loading property file", e);
        }
    }

    private PropsUtil() {
    }

    public static String getDataBaseConnectionUrl() {
        return String.format(getDataBaseConnectionPattern(), getDataBaseConnectionHost(), getDataBaseName());
    }

    public static String getDataBaseConnectionHost() {
        return PROPERTIES.getProperty("pgs.host");
    }

    public static String getDataBaseConnectionPattern() {
        return PROPERTIES.getProperty("pgs.connection_pattern");
    }

    public static String getDataBaseName() {
        return PROPERTIES.getProperty("pgs.dbname");
    }

    public static String getDataBaseUser() {
        return PROPERTIES.getProperty("pgs.user");
    }

    public static String getDataBasePassword() {
        return PROPERTIES.getProperty("pgs.password");
    }

    public static String getErrorCode() {
        return PROPERTIES.getProperty(ERROR_KEY);
    }

}
