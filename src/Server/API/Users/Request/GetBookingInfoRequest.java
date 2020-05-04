package Server.API.Users.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingInfoRequest {
    public int BookingID;
    public String PlayerID;
}