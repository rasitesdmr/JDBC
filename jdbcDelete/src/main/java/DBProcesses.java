import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBProcesses {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void deletePersonnelByPersonnelId(int personnelId){
    String query = "DELETE FROM personnel WHERE personnelId=?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,personnelId);
            preparedStatement.executeUpdate();
            LOGGER.info(personnelId +"Id li personel silindi ");
        } catch (SQLException e) {
        LOGGER.warn(personnelId + "Id li personel silinirken hata meydana gelid. HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement);
        }
    }
    public static void deletePersonnelByName(String name){
    String query = "DELETE FROM personnel WHERE name=?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name );
            preparedStatement.executeUpdate();
            LOGGER.info(name +"isimli personel silindi ");
        } catch (SQLException e) {
            LOGGER.warn(name + "isimli personel silinirken hata meydana gelid. HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement);
        }

    }

}