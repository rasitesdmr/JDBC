import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBProcesses {

   private static final Logger LOGGER = LogManager.getLogger();

    public static void savePersonnel(Personnel personnel) {

        Connection connection = DBConnection.getConnection();

        String query = "INSERT INTO personnel(personnelId, name, lastName, yearOfBirth, personnelNumber) VALUES(?,?,?,?,?) ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, personnel.getPersonnelId());
            preparedStatement.setString(2, personnel.getName());
            preparedStatement.setString(3, personnel.getLastName());
            preparedStatement.setInt(4, personnel.getYearOfBirth());
            preparedStatement.setString(5, personnel.getPersonnelNumber());
            int i = preparedStatement.executeUpdate();
            LOGGER.info(i + "- Personel eklendi");
        } catch (SQLException e) {
            LOGGER.warn("personel eklenirken hata meydana geldi. HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement);
        }
    }
}