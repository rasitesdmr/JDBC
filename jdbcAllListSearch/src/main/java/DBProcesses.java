import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBProcesses {

    private static final Logger LOGGER = LogManager.getLogger();

    public static List<Personnel> findPersonels() {
        Connection connection = DBConnection.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM personnel ";
        List<Personnel> personnels = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int personnelId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int yearOfBirth = resultSet.getInt(4);
                String personnelNumber = resultSet.getString(5);
                Personnel personnel = new Personnel(personnelId, name, lastName, yearOfBirth, personnelNumber);
                personnels.add(personnel);
            }


            LOGGER.info("veriler Okundu");

        } catch (SQLException e) {
            LOGGER.warn(" personel listesi alınırken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return personnels;
    }
}