package Proje;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProcesses {

    private  final Logger LOGGER = LogManager.getLogger();


    public void createPersonelTable(){

        Connection connection = DBConnection.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE personnel(personnelId INT PRIMARY KEY NOT NULL, name VARCHAR(255), " +
                    "lastName VARCHAR(255),yearOfBirth INT, personnelNumber VARCHAR(20))";
            statement.execute(query);
            LOGGER.info("Personel tablosu başarılı bir şekilde oluşturuldu.");
        } catch (SQLException e) {
            LOGGER.warn("personel tablosu oluşturulurken hata meydana gelid. HATA : " + e);
        }finally {

            DBConnection.closeConnection(connection,statement);
        }
    }
}
