package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.CreateCityCenterCourtRequest;
import Server.API.Users.Response.CreateCityCenterCourtResponse;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CreateCityCenterCourtSql {
    public static CreateCityCenterCourtResponse Create(CreateCityCenterCourtRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call createCityCenterCourt(?,?,?)}");
            Statement.setInt(2, Request.CenterID);
            Statement.setString(1, Request.CourtName);
            Statement.registerOutParameter(3, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(3);

            CreateCityCenterCourtResponse Response = new CreateCityCenterCourtResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();

            if (ResultCode ==  300)
            {
                while (Result.next()) {
                    Response.CourtID = Result.getInt("court_ID");
                }
            }
            return Response;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new CreateCityCenterCourtResponse(304);
    }
}
