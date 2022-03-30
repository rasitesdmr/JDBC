import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        Personnel personnel1 = new Personnel(4,"**********","**********",1998,"**********");
        Personnel personnel2 = new Personnel(5,"**********","**********",2005,"**********");

        savePersonnel(personnel1);
        savePersonnel(personnel2);

    }

    public static void savePersonnel(Personnel personnel) {

        Logger logger = LogManager.getLogger();

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
            logger.info(i + "- Personel eklendi");
        } catch (SQLException e) {
            logger.warn("personel eklenirken hata meydana geldi. HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement);
        }
    }
}