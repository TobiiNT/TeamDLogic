package Server.API.Users.Response;

import Struct.Booking;

public class GetBookingInfoResponse {
    public GetBookingInfoResponse(int ResultCode) { this.ResultCode = ResultCode;  }
    public int ResultCode;
    public Booking CurrentBooking;
}
