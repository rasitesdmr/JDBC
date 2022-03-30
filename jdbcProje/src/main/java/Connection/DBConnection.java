package Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class  DBConnection {
    private static final Logger LOGGER = LogManager.getLogger();

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // Static sınıfı sadece bir defa çalışır.
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(inputStream);
            driver = properties.getProperty("db_driver");
            url = properties.getProperty("db_url");
            username = properties.getProperty("db_username");
            password = properties.getProperty("db_password");

        } catch (IOException e) {
            LOGGER.warn("database properties dosyasından verileri çekerken hata meydana geldi . HATA : " + e);
        }
    }

    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            LOGGER.warn("database driver bulunamadı . HATA : " + e);
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.warn("Bağlantı oluşturulurken hata meydana geldi . HATA : " + e);
        }

        return connection;
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                LOGGER.info("resultSet kapatıldı");
            } catch (SQLException e) {
                LOGGER.warn("resultSet kapatılırken hata meydan geldi . HATA : " + e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
                LOGGER.info("connection kapatıldı");
            } catch (SQLException e) {
                LOGGER.warn("connection kapatılırken hata meydan geldi . HATA : " + e);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                LOGGER.info("preparedStatement kapatıldı");
            } catch (SQLException e) {
                LOGGER.warn("preparedStatement kapatılırken hata meydan geldi . HATA : " + e);
            }
        }
    }

}
