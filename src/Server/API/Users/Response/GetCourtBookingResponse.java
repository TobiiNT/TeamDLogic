package Server.API.Users.Response;

import Struct.Booking;

import java.util.*;

public class GetCourtBookingResponse {
    public GetCourtBookingResponse(int ResultCode) { this.ResultCode = ResultCode; this.CurrentBookings = new ArrayList<>(); }
    public int ResultCode;
    public ArrayList<Booking> CurrentBookings;
}
