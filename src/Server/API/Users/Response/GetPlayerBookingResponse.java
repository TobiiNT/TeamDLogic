package Server.API.Users.Response;

import Struct.Booking;

import java.util.ArrayList;

public class GetPlayerBookingResponse {
    public GetPlayerBookingResponse(int ResultCode) { this.ResultCode = ResultCode; this.CurrentBookings = new ArrayList<>(); }
    public int ResultCode;
    public ArrayList<Booking> CurrentBookings;
}
