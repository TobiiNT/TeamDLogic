package Server.API.Users.Handler;

import Database.Request.GetCourtBookingSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.GetCourtBookingRequest;
import Server.API.Users.Response.GetCourtBookingResponse;
import Server.Errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetCourtBookingHandler extends Handler {
    public GetCourtBookingHandler(String MethodAllowed, ObjectMapper ObjectMapper, GlobalExceptionHandler GlobalExceptionHandler) {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<GetCourtBookingResponse> DoPost(String Input) {
        GetCourtBookingRequest GetCourtBookingRequest = super.ReadRequest(Input, GetCourtBookingRequest.class);

        GetCourtBookingResponse GetCourtBookingResponse = GetCourtBookingSql.Get(GetCourtBookingRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), GetCourtBookingResponse);
    }
}
