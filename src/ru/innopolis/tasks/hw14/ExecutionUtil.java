package ru.innopolis.tasks.hw14;

import org.apache.log4j.NDC;
import ru.innopolis.tasks.hw14.logger.LoggerUtil;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;

/**
 * Класс методов, выполняющих действия с БД
 */
public class ExecutionUtil {

    /**
     * Демонстрация работы с PreparedStatement
     *
     * @param cn подключение к БД
     * @throws SQLException
     */
    public static void preparedStatementDemo(Connection cn) {
        String name = null;
        int loginId = 0;
        try (PreparedStatement pst = cn.prepareStatement(
                "INSERT INTO public.users(user_name, login_id, city, email, description) values (?,?,?,?,?)")
        ) {
            name = "Monica Geller";
            loginId = getRandInt();

            NDC.push(name);
            NDC.push(String.valueOf(loginId));

            pst.setString(1, name);
            pst.setInt(2, loginId);
            pst.setString(3, "NY");
            pst.setString(4, "mon@friends.com");
            pst.setString(5, "Talented cook");
            int rows = pst.executeUpdate();

            LoggerUtil.LOGGER.info(">>> Person has been added to DB");
            printRowsNumber(rows);
        } catch (SQLException e) {
            LoggerUtil.LOGGER.error(">>> Error in preparedStatementDemo():", e);
        } finally {
            NDC.remove();
        }
    }

    /**
     * Демонстрация работы с batch процессом
     *
     * @param cn подключение к БД
     * @throws SQLException
     */
    public static void batchDemo(Connection cn) {
        try (PreparedStatement pst = cn.prepareStatement(
                "INSERT INTO public.roles(role_name, description) VALUES (?,?)")
        ) {
            NDC.push("batchDemo method");

            pst.setString(1, "Administration");
            pst.setString(2, "Main role");
            pst.addBatch();

            pst.setString(1, "Clients");
            pst.setString(2, "PR manager");
            pst.addBatch();

            pst.setString(1, "Billing");
            pst.setString(2, "Account manager");
            pst.addBatch();

            printRowsNumber((Object) pst.executeBatch());
        } catch (SQLException e) {
            LoggerUtil.LOGGER.error(">>> Error in batchDemo():", e);
        } finally {
            NDC.clear();
        }
    }

    /**
     * Пример параметризированной выборки по двум параметрам
     *
     * @param userName имя из БД
     * @param loginId  логин из БД
     * @param cn       подключение к БД
     */
    public static void paramGetDemo(String userName, int loginId, Connection cn) {
        try (PreparedStatement pst = cn.prepareStatement(
                "SELECT * from public.users where user_name like '%'||?||'%' AND login_id=?")
        ) {
            NDC.push(userName);
            NDC.push(String.valueOf(loginId));

            pst.setString(1, userName);
            pst.setInt(2, loginId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Name: ").append(rs.getString(2)).append("\n");
                sb.append("\tLogin_ID: ").append(rs.getInt(3)).append("\n");
                sb.append("\tCity: ").append(rs.getString(4)).append("\n");
                sb.append("\tEmail: ").append(rs.getString(5)).append("\n");
                sb.append("\tDescription: ").append(rs.getString(6)).append("\n");
                LoggerUtil.LOGGER.info("User from a DB: ".concat(sb.toString()));
            }
        } catch (SQLException e) {
            LoggerUtil.LOGGER.error(">>> Error in paramGetDemo():", e);
        } finally {
            NDC.clear();
        }
    }

    /**
     * Работа с точкой сохранения без ошибки
     *
     * @param cn подключение к БД
     */
    public static void savePointDemoNoException(Connection cn) {
        Savepoint savepointA = null;
        Statement st = null;
        NDC.push("method savePointDemoNoException()");
        try {
            cn.setAutoCommit(false);
            st = cn.createStatement();
            st.executeUpdate(
                    "INSERT INTO public.users(user_name, login_id, city, email, description) " +
                            "VALUES ('Winston Churchill',900,'London','smoker@vi.uk','Prime minister of Grate Britain')"
            );
            savepointA = cn.setSavepoint("A");
            st.executeUpdate(
                    "INSERT INTO public.user_role (user_id, role_id) " +
                            "VALUES(" +
                            "(SELECT id FROM public.users WHERE user_name = 'Winston Churchill')," +
                            " (SELECT id FROM public.roles WHERE role_name = 'Administration'))"
            );
            cn.commit();
        } catch (SQLException e) {
            handleCatch(cn, savepointA, e);
        } finally {
            try {
                cn.setAutoCommit(true);
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                LoggerUtil.LOGGER.error(">>> Error during closing statement:", e);
            }
            NDC.clear();
        }
    }

    /**
     * Демонстрация отката при исключении после точки сохранения
     *
     * @param cn подключение к БД
     */
    public static void savePointDemoWithException(Connection cn) {
        Savepoint savepoint = null;
        Statement st = null;
        NDC.push("method savePointDemoWithException()");
        try {
            cn.setAutoCommit(false);
            st = cn.createStatement();
            st.executeUpdate(
                    "INSERT INTO public.users(user_name, login_id, city, email, description) " +
                            "VALUES ('Clark Kent',1000,'NY','super@dc.com','Extraterrestrial man')"
            );
            savepoint = cn.setSavepoint();
            st.executeUpdate(
                    "INSERT INTO public.users(user_name, login_id, city, email, description) " +
                            "VALUES ('Clark Kent Dark',1000,'NY','super@dc.com','Extraterrestrial man')"
            );
            cn.commit();
        } catch (SQLException e) {
            handleCatch(cn, savepoint, e);
        } finally {
            try {
                cn.setAutoCommit(true);
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                LoggerUtil.LOGGER.error(">>> Error during closing statement:", e);
            }
            NDC.clear();
        }
    }

    /**
     * Замена повторяющегося кода
     *
     * @param cn
     * @param savepoint
     * @param e
     */
    private static void handleCatch(Connection cn, Savepoint savepoint, SQLException e) {
        LoggerUtil.LOGGER.error(">>> Error in savePointDemo:", e);
        try {
            if (savepoint != null) {
                cn.rollback(savepoint);
            }
            cn.commit();
        } catch (SQLException ex) {
            LoggerUtil.LOGGER.error(">>> Error during rollback to savepoint:", ex);
            try {
                cn.rollback();
            } catch (SQLException exc) {
                LoggerUtil.LOGGER.error(">>> Error during rollback:", exc);
            }
        }
    }

    /**
     * Печать количества задействованных в SQL-выражении строк при выполнении batch процесса
     *
     * @param rows массив с количеством строк из каждого batch процесса
     */
    private static void printRowsNumber(Object...rows) {
        LoggerUtil.LOGGER.info(">>> ".concat(Arrays.toString(rows)).concat(" rows affected!"));
    }

    /**
     * Генерация положительного целого чисоа
     *
     * @return положительное целое число
     */
    private static int getRandInt() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(Integer.MAX_VALUE);
    }


}
