package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.GetAllCourtInCenterRequest;
import Server.API.Users.Response.GetAllCourtInCenterResponse;
import Struct.Court;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GetAllCourtInCenterSql {
    public static GetAllCourtInCenterResponse Get(GetAllCourtInCenterRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call getAllCitiesCentersCourts(?,?)}");
            Statement.setInt(1, Request.CenterID);
            Statement.registerOutParameter(2, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(2);

            GetAllCourtInCenterResponse GetAllCourtInCenterResponse = new GetAllCourtInCenterResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 1000)
            {
                while (Result.next()) {
                    Court CurrentCourt = new Court();
                    CurrentCourt.CourtID =  Result.getInt("court_id");
                    CurrentCourt.CourtName =  Result.getString("name");
                    GetAllCourtInCenterResponse.CurrentCourts.add(CurrentCourt);
                }
            }


            return GetAllCourtInCenterResponse;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new GetAllCourtInCenterResponse(1003);
    }
}
