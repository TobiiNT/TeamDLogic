package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.GetCourtBookingRequest;
import Server.API.Users.Response.GetCourtBookingResponse;
import Struct.Booking;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

public class GetCourtBookingSql {
    public static GetCourtBookingResponse Get(GetCourtBookingRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call getCourtBooking(?,?,?)}");
            Statement.setDate(1, Request.BookingDay);
            Statement.setInt(2, Request.CourtID);
            Statement.registerOutParameter(3, Types.INTEGER);
            Statement.setQueryTimeout(10);
            boolean Executed = Statement.execute();

            int ResultCode = Statement.getInt(3);

            GetCourtBookingResponse Response = new GetCourtBookingResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 1400)
            {
                while (Result.next()) {
                    Booking CurrentBooking = new Booking();
                    CurrentBooking.CourtID = Request.CourtID;
                    CurrentBooking.PlayerID = Result.getString("player");
                    CurrentBooking.BookingID = Result.getInt("booking_id");
                    CurrentBooking.BookingDay = Result.getDate("date");
                    CurrentBooking.SetStartTime(Result.getTime("startTime"));
                    CurrentBooking.SetEndTime(Result.getTime("endTime"));

                    Response.CurrentBookings.add(CurrentBooking);
                }
            }



            return Response;
        }
        catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return new GetCourtBookingResponse(1403);
    }
}
