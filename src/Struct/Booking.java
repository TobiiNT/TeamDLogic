package Struct;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

public class Booking {
    public int CourtID;
    public String PlayerID;
    public int BookingID;
    public Date BookingDay;
    public Time BookingStartTime;
    public Time BookingEndTime;

    public void SetStartTime(Time Time) throws ParseException {
        BookingStartTime = java.sql.Time.valueOf(Time.toLocalTime().minusMinutes(60));
    }
    public void SetEndTime(Time Time) throws ParseException {
        BookingEndTime = java.sql.Time.valueOf(Time.toLocalTime().minusMinutes(60));
    }
}
