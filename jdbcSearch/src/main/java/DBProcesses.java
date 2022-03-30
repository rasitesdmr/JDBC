import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBProcesses {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Personnel findPersonnelById(int personelId) {
        Connection connection = DBConnection.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM personnel WHERE personnelId=?";
        Personnel personnel = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, personelId);
            resultSet = preparedStatement.executeQuery();

           if (resultSet.next()){
               int personnelId = resultSet.getInt(1);
               String name = resultSet.getString(2);
               String lastName = resultSet.getString(3);
               int yearOfBirth = resultSet.getInt(4);
               String personnelNumber = resultSet.getString(5);
               personnel = new Personnel(personnelId,name,lastName,yearOfBirth,personnelNumber);
           }
            LOGGER.info("veriler Okundu");

        } catch (SQLException e) {
        LOGGER.warn(personelId + "id li personel bulunurken hata meydana geldi . HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }

        return personnel;
    }


}