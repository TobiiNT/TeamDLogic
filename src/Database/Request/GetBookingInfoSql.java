package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.GetBookingInfoRequest;
import Server.API.Users.Response.GetBookingInfoResponse;
import Struct.Booking;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

public class GetBookingInfoSql {
    public static GetBookingInfoResponse Get(GetBookingInfoRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call getBookingInfo(?,?,?)}");
            Statement.setInt(1, Request.BookingID);
            Statement.setString(2, Request.PlayerID);
            Statement.registerOutParameter(3, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(3);

            GetBookingInfoResponse Response = new GetBookingInfoResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();
            Result.first();

            if (ResultCode == 1200)
            {
                Response.CurrentBooking = new Booking();
                Response.CurrentBooking.CourtID = Result.getInt("court");
                Response.CurrentBooking.PlayerID = Result.getString("player");
                Response.CurrentBooking.BookingID = Result.getInt("booking_id");
                Response.CurrentBooking.BookingDay = Result.getDate("date");
                Response.CurrentBooking.SetStartTime(Result.getTime("startTime"));
                Response.CurrentBooking.SetEndTime(Result.getTime("endTime"));
            }

            return Response;
        }
        catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return new GetBookingInfoResponse(1204);
    }
}
