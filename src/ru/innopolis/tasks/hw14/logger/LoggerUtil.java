package ru.innopolis.tasks.hw14.logger;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Взять за основу ДЗ_13,
 * <p>
 * покрыть код логированием
 * <p>
 * в основных блоках try покрыть уровнем INFO
 * <p>
 * с исключениях catch покрыть уровнем ERROR
 * <p>
 * настроить конфигурацию логера, что бы все логи записывались в БД, таблица LOGS,
 * <p>
 * колонки ID, DATE, LOG_LEVEL, MESSAGE, EXCEPTION
 */
public class LoggerUtil {

    public static final Logger LOGGER = LogManager.getLogger("myLogger");

    static {
        PropertyConfigurator.configure("./src/ru/innopolis/tasks/hw14/logger/log4j.properties");
    }

}