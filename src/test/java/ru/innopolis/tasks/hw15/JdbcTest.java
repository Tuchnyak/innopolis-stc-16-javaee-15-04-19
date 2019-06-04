package ru.innopolis.tasks.hw15;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.innopolis.tasks.hw13.*;

import java.sql.Connection;

/**
 * Взять за основу ДЗ_13. Написать тест на CRUD операции
 * <p>
 * Инициализацию соединение с БД произвести один раз перед началом тестов, после завершения всех тестов, закрыть соединие с БД
 * <p>
 * Подготовку данных для CRUD операций производить в методе Init использую @Before
 * <p>
 * Задание 2
 * <p>
 * Использую Spy и Mockito создать заглушки для интерфейса JDBC
 */
public class JdbcTest {

    private static Connection cn;

    @BeforeAll
    public static void connectionInit(){
        cn = ConnectionUtil.getConnection();
    }

    @Test
    public void checkConnection(){
        Assertions.assertNotNull(cn);
    }

    @AfterAll
    public static void closeConnection(){
        if (cn != null) {
            ConnectionUtil.closeConnection(cn);
        }
    }

}
