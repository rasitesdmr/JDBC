package Connection3;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = Application3.getConnection();
        Application3.closeConnection(connection);
    }
}
