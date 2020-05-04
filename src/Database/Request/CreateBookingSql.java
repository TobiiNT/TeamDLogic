package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.CreateBookingRequest;
import Server.API.Users.Response.CreateBookingResponse;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

public class CreateBookingSql {
    public static CreateBookingResponse Create(CreateBookingRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call createBooking(?,?,?,?,?,?)}");
            Statement.setDate(1, Request.BookingDay);
            Statement.setTime(2, Request.GetStartTime());
            Statement.setTime(3, Request.GetEndTime());
            Statement.setInt(4, Request.CourtID);
            Statement.setString(5, Request.PlayerID);
            Statement.registerOutParameter(6, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(6);

            CreateBookingResponse Response = new CreateBookingResponse(ResultCode);
            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 500)
            {
                while (Result.next()) {
                    Response.BookingID = Result.getInt("booking_id");
                }
            }

            return Response;
        }
        catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return new CreateBookingResponse(512);
    }
}
