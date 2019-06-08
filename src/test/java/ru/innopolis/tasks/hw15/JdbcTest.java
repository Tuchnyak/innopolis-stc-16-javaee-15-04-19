package ru.innopolis.tasks.hw15;

import org.junit.jupiter.api.*;
import ru.innopolis.tasks.hw13.*;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JdbcTest {

    private static Connection cn;
    private static PreparedStatement pst;
    private static int randomLongId;
    private static String sqlInsertUser;
    private static String sqlGetUser;
    private static String sqlUpdateUser;
    private static String sqlDeleteUser;

    @BeforeAll
    public static void connectionInit() {
        cn = ConnectionUtil.getConnection();
        randomLongId = new SecureRandom().nextInt(Integer.MAX_VALUE);
    }

    @BeforeEach
    public void init() throws SQLException {
        sqlInsertUser = "INSERT INTO public.users(user_name, login_id, city, email, description) values (?,?,?,?,?)";
        sqlGetUser = "SELECT * FROM public.users WHERE login_id = ?";
        sqlUpdateUser = "UPDATE public.users SET city=? WHERE login_id = ?";
        sqlDeleteUser = "DELETE FROM public.users WHERE login_id = ?";
    }

    @Test
    @Order(0)
    public void checkConnection() {
        Assertions.assertNotNull(cn);
    }

    @Test
    @Order(1)
    public void testInsert() throws SQLException {
        pst = cn.prepareStatement(sqlInsertUser);
        pst.setString(1, "Monica Geller");
        pst.setInt(2, randomLongId);
        pst.setString(3, "NY");
        pst.setString(4, "mon@friends.com");
        pst.setString(5, "Talented cook");
        int rows = pst.executeUpdate();
        Assertions.assertEquals(1, rows);
    }

    @Test
    @Order(2)
    public void testGet() throws SQLException {
        pst = cn.prepareStatement(sqlGetUser);
        pst.setInt(1, randomLongId);
        ResultSet rs = pst.executeQuery();
        rs.next();
        Assertions.assertEquals("Monica Geller", rs.getString(2));
        Assertions.assertEquals(randomLongId, rs.getInt(3));
    }

    @Test
    @Order(3)
    public void testUpdate() throws SQLException {
        pst = cn.prepareStatement(sqlUpdateUser);
        pst.setString(1, "SPb");
        pst.setInt(2, randomLongId);
        int rows = pst.executeUpdate();
        Assertions.assertEquals(1, rows);
    }

    @Test
    @Order(4)
    public void testDelete() throws SQLException {
        pst = cn.prepareStatement(sqlDeleteUser);
        pst.setInt(1, randomLongId);
        int rows = pst.executeUpdate();
        Assertions.assertEquals(1, rows);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (pst != null) {
            pst.close();
        }
    }

    @AfterAll
    public static void closeConnection() {
        if (cn != null) {
            ConnectionUtil.closeConnection(cn);
        }
    }

}
