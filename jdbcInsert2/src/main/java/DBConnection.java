import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class DBConnection {

    private static final Logger LOGGER = LogManager.getLogger();

    public static  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql Driver Bulunamadı. HATA : " + e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme", "root", "**********");
            LOGGER.info("Mysql Bağlandık");
        } catch (SQLException e) {
            LOGGER.warn("Mysql bağlanırken hata oluştu.");
        }

        return connection;
    }

    public static void closeConnection(Connection connection , PreparedStatement preparedStatement) {
        if (preparedStatement !=null){
            try {
                preparedStatement.close();
                LOGGER.info("PreparedStatement kapatıldı");
            } catch (SQLException e) {
                LOGGER.warn("PreparedStatement kapatılamadı . HATA : " + e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
                LOGGER.info("Veritabanı bağlantısı kapatıldı");
            } catch (SQLException e) {
                LOGGER.warn("Veritanı kapatılamadı. Hata : " + e);
            }
        }
    }
}
