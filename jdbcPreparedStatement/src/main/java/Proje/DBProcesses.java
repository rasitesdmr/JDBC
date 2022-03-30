package Proje;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBProcesses {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void createPersonelTable(){

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String query ="CREATE TABLE personnel(personnelId INT PRIMARY KEY NOT NULL, name VARCHAR(255), " +
                "lastName VARCHAR(255),yearOfBirth INT, personnelNumber VARCHAR(20))";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            LOGGER.info("Personel tablosu başarıyla oluşturuldu");
        } catch (SQLException e) {
            LOGGER.warn("Personel tablosu oluşturulurken hata meydana geldi. HATA :  " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement);
        }

    }


}
