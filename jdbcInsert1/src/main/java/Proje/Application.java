package Proje;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger();

        Connection connection = DBConnection.getConnection();

        String query = "INSERT INTO personnel(personnelId, name, lastName, yearOfBirth, personnelNumber) VALUES(?,?,?,?,?) ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Raşit");
            preparedStatement.setString(3, "Eşdemir");
            preparedStatement.setInt(4, 2000);
            preparedStatement.setString(5, "123456789");
            int i = preparedStatement.executeUpdate();
            logger.info(i + ") Personel eklendi");
        } catch (SQLException e) {
            logger.warn("personel eklenirken hata meydana geldi. HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement);
        }
    }
}
