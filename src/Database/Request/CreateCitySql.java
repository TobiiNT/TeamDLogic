package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.CreateCityRequest;
import Server.API.Users.Response.CreateCityResponse;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CreateCitySql {
    public static CreateCityResponse Create(CreateCityRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call createCity(?,?)}");
            Statement.setString(1, Request.CityName);
            Statement.registerOutParameter(2, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(2);

            CreateCityResponse Response = new CreateCityResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 100)
            {
                while (Result.next()) {
                    Response.CityID = Result.getInt("city_ID");
                }
            }

            return Response;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new CreateCityResponse(103);
    }
}
