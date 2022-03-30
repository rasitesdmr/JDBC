package Connection2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application2 {

    private static Connection connection = null;

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
    openConnection();
    closeConnection();

    }

    private static void openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql Driver Bulunamadı. HATA : " + e);
            return;
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme","root","*********");
            logger.info("Mysql Bağlandık");
        } catch (SQLException e) {
            logger.warn("Mysql bağlanırken hata oluştu.");
        }
    }

    private static void closeConnection(){

        if (connection !=null){
            try {
                connection.close();
                logger.info("Veritabanı bağlantısı kapatıldı");
            } catch (SQLException e) {
                logger.warn("Veritanı kapatılamadı. Hata : " + e);
            }
        }
    }

}
