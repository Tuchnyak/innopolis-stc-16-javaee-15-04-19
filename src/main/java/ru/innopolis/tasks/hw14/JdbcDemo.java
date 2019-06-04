package ru.innopolis.tasks.hw14;

import ru.innopolis.tasks.hw14.logger.LoggerUtil;
import ru.innopolis.tasks.hw14.properties.PropsUtil;

import java.sql.Connection;

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
public class JdbcDemo {

    private static Connection connection;

    public static void main(String[] args) {
        if (PropsUtil.getErrorCode().equals(PropsUtil.ERROR_CODE_SUCCESS)) {
            connection = ConnectionUtil.getConnection();
            if (connection != null) {
                ExecutionUtil.preparedStatementDemo(connection);
                ExecutionUtil.batchDemo(connection);
                ExecutionUtil.paramGetDemo("Rachel", 123, connection);
                ExecutionUtil.savePointDemoNoException(connection);
                ExecutionUtil.savePointDemoWithException(connection);
                ConnectionUtil.closeConnection(connection);
            } else {
                LoggerUtil.LOGGER.warn(">>> Подключение к БД не было установлено!");
            }
        } else {
            LoggerUtil.LOGGER.warn(">>> Возникла проблема при загрузке файла-настроек!");
        }
    }

}
