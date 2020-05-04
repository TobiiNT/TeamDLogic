package Database.Request;

import Database.MySqlConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DeleteAllDataSql {
    public static void Delete()
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call DeleteData}");
            Statement.setQueryTimeout(10);
            Statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
