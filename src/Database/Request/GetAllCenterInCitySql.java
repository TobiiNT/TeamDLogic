package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.GetAllCenterInCityRequest;
import Server.API.Users.Response.GetAllCenterInCityResponse;
import Struct.Center;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GetAllCenterInCitySql {
    public static GetAllCenterInCityResponse Get(GetAllCenterInCityRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call getAllCitiesCenters(?,?)}");
            Statement.setInt(1, Request.CityID);
            Statement.registerOutParameter(2, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(2);

            GetAllCenterInCityResponse GetAllCenterInCityResponse = new GetAllCenterInCityResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 900)
            {
                while (Result.next()) {
                    Center CurrentCenter = new Center();
                    CurrentCenter.CenterID = Result.getInt("center_id");
                    CurrentCenter.CenterName = Result.getString("name");
                    GetAllCenterInCityResponse.CurrentCenters.add(CurrentCenter);
                }
            }

            return GetAllCenterInCityResponse;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new GetAllCenterInCityResponse(903);
    }
}
