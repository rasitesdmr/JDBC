import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        savePersonnel(2,"**********","**********",1999,"**********");
        savePersonnel(3,"**********","**********",2001,"**********");

    }

    public static void savePersonnel(int personnelId , String name , String lastName,int yearOfBirth,String personnelNumber) {

        Logger logger = LogManager.getLogger();

        Connection connection = DBConnection.getConnection();

        String query = "INSERT INTO personnel(personnelId, name, lastName, yearOfBirth, personnelNumber) VALUES(?,?,?,?,?) ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, personnelId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, yearOfBirth);
            preparedStatement.setString(5, personnelNumber);
            int i = preparedStatement.executeUpdate();
            logger.info(i + ") Personel eklendi");
        } catch (SQLException e) {
            logger.warn("personel eklenirken hata meydana geldi. HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement);
        }
    }
}
