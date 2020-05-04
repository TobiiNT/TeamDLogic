package Server.API.Users.Handler;

import Database.Request.GetPlayerBookingSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.GetPlayerBookingRequest;
import Server.API.Users.Response.GetPlayerBookingResponse;
import Server.Errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetPlayerBookingHandler extends Handler {
    public GetPlayerBookingHandler(String MethodAllowed, ObjectMapper ObjectMapper, GlobalExceptionHandler GlobalExceptionHandler) {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<GetPlayerBookingResponse> DoPost(String Input) {
        GetPlayerBookingRequest GetPlayerBookingRequest = super.ReadRequest(Input, GetPlayerBookingRequest.class);

        GetPlayerBookingResponse GetPlayerBookingResponse = GetPlayerBookingSql.Get(GetPlayerBookingRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), GetPlayerBookingResponse);
    }
}
