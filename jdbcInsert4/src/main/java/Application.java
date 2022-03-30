import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {

        Personnel personnel1 = new Personnel(6, "**********", "**********", 1988, "**********");
        Personnel personnel2 = new Personnel(7, "**********", "**********", 2005, "**********");
        DBProcesses.savePersonnel(personnel1);
        DBProcesses.savePersonnel(personnel2);

    }


}
