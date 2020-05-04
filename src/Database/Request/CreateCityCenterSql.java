package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.CreateCityCenterRequest;
import Server.API.Users.Response.CreateCityCenterResponse;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CreateCityCenterSql {
    public static CreateCityCenterResponse Create(CreateCityCenterRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call createCityCenter(?,?,?)}");
            Statement.setInt(1, Request.CityID);
            Statement.setString(2, Request.CenterName);
            Statement.registerOutParameter(3, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(3);

            CreateCityCenterResponse Response = new CreateCityCenterResponse(ResultCode);
            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 200)
            {
                while (Result.next()) {
                    Response.CenterID = Result.getInt("center_ID");
                }
            }

            return Response;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new CreateCityCenterResponse(204);
    }
}
