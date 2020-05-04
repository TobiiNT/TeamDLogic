package Server.API.Users.Request;

import lombok.*;

import java.sql.Time;
import java.sql.Date;
import java.text.ParseException;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    public Date BookingDay;
    public Time StartTime;
    public Time EndTime;
    public int CourtID;
    public String PlayerID;

    public Time GetStartTime() throws ParseException {
        return java.sql.Time.valueOf(StartTime.toLocalTime().plusMinutes(60));
    }
    public Time GetEndTime() throws ParseException {
        return java.sql.Time.valueOf(EndTime.toLocalTime().plusMinutes(60));
    }
}
