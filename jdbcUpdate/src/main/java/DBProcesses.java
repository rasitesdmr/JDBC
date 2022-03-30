import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBProcesses {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void updatePersonel(Personnel personnel) {

        Connection connection = DBConnection.getConnection();

        String query = "UPDATE personnel SET name=?,lastName=?,yearOfBirth=?,personnelNumber=? WHERE personnelId=?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, personnel.getName());
            preparedStatement.setString(2, personnel.getLastName());
            preparedStatement.setInt(3, personnel.getYearOfBirth());
            preparedStatement.setString(4, personnel.getPersonnelNumber());
            preparedStatement.setInt(5, personnel.getPersonnelId());
            preparedStatement.executeUpdate();
            LOGGER.info(personnel.getPersonnelId() + " :Personel Güncellendi");
        } catch (SQLException e) {
            LOGGER.warn(personnel.getPersonnelId() + "Personel güncellenirken HATA meydana geldi. HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement);
        }

    }

}