import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    // Блок объявления констант
    public static final String DB_URL = "jdbc:postgresql://localhost:9000/Library";
    public static final String DB_Driver = "org.postgresql.Driver";

    public static void main(String[] args) {
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL,"postgres", "1qaz!QAZ");//соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            Insert(connection, "Заголовок", "Автор", "1985");
            LibList(connection);
            Delete(connection, 1);
            LibList(connection);
            Update(connection, "Заголовок1", "Автор1", "1986", 3);
            LibList(connection);
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }


    }

    public static void Insert(Connection connection, String title, String author, String year) {
        ResultSet resultSet = null;

        try (Statement statement = connection.createStatement()) {

            String selectSql = "Insert into lib(title, author, year) values ('" + title + "', '" + author + "', '" + year + "-01-01')";
            statement.executeQuery(selectSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void LibList(Connection connection) {
        ResultSet resultSet = null;

        try (Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "Select * from lib";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Update(Connection connection, String title, String author, String year, int id) {
        ResultSet resultSet = null;

        try (Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "Update lib set title = '" + title + "', author = '" + author + "', year = '" + year + "-01-01' where id = " + id;
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Delete(Connection connection, int id) {
        ResultSet resultSet = null;

        try (Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "Delete from lib where id = " + id;
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}