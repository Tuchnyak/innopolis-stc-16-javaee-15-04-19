package ru.innopolis.tasks.hw13;

import ru.innopolis.tasks.hw13.properties.PropsUtil;

import java.sql.*;
import java.util.Arrays;

/**
 * 1)    Спроектировать базу
 * -      Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 * -      Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 * -      Таблица USER_ROLE содержит поля id, user_id, role_id
 * <p>
 * Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 * <p>
 * 2)      Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a)      Используя параметризированный запрос
 * b)      Используя batch процесс
 * <p>
 * 3)      Сделать параметризированную выборку по login_ID и name одновременно
 * <p>
 * 4)      Перевести connection в ручное управление транзакциями
 * a)      Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить логическую точку сохранения(SAVEPOINT)
 * б)      Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные на последней
 * операции, что бы транзакция откатилась к логической точке SAVEPOINT A
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
                System.out.println(">>> Подключение к БД не было установлено!");
            }
        } else {
            System.out.println(">>> Возникла проблема при загрузке файла-настроек!");
        }
    }

}
