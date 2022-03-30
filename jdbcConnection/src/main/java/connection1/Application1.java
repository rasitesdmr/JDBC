package connection1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Application1 {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.warn("Msyql Driver bulunamadı . Hata : " + e);
            return;
        }
            Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme","root","*********");
            logger.info("Mysql Bağlandık");
        } catch (SQLException e) {
            logger.warn("Mysql bağlanırken hata meydana geldi : " );
        }finally {
            if (connection !=null){
                try {
                    connection.close();
                    logger.info("Bağlantı Başarılı Bir Şekilde Kapatıldı");
                } catch (SQLException e) {
                logger.warn("Bağlantı kapatılırken Hata meydana geli");
                }
            }
        }
    }
}
