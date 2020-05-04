package Database.Request;

import Database.MySqlConnection;
import Server.API.Users.Request.GetPlayerBookingRequest;
import Server.API.Users.Response.GetPlayerBookingResponse;
import Struct.Booking;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

public class GetPlayerBookingSql {
    public static GetPlayerBookingResponse Get(GetPlayerBookingRequest Request)
    {
        try {
            CallableStatement Statement = MySqlConnection.GetProcedureStatement("{Call getCourtBooking(?,?,?)}");
            Statement.setDate(1, Request.BookingDay);
            Statement.setString(2, Request.PlayerID);
            Statement.registerOutParameter(3, Types.INTEGER);
            Statement.setQueryTimeout(10);
            Statement.execute();

            int ResultCode = Statement.getInt(3);

            GetPlayerBookingResponse Response = new GetPlayerBookingResponse(ResultCode);

            ResultSet Result = Statement.getResultSet();

            if (ResultCode == 1500)
            {
                while (Result.next()) {
                    Booking CurrentBooking = new Booking();
                    CurrentBooking.CourtID = Result.getInt("court");
                    CurrentBooking.PlayerID = Result.getString("Player ID");
                    CurrentBooking.BookingID = Result.getInt("booking_id");
                    CurrentBooking.BookingDay = Result.getDate("date");
                    CurrentBooking.SetStartTime(Result.getTime("Start Time"));
                    CurrentBooking.SetEndTime(Result.getTime("End Time"));

                    Response.CurrentBookings.add(CurrentBooking);
                }
            }



            return Response;
        }
        catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return new GetPlayerBookingResponse(1504);
    }
}
