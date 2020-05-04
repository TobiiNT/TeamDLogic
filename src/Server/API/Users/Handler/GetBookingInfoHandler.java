package Server.API.Users.Handler;

import Database.Request.GetBookingInfoSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.GetBookingInfoRequest;
import Server.API.Users.Response.GetBookingInfoResponse;
import Server.Errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetBookingInfoHandler extends Handler {
    public GetBookingInfoHandler(String MethodAllowed, ObjectMapper ObjectMapper, GlobalExceptionHandler GlobalExceptionHandler) {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<GetBookingInfoResponse> DoPost(String Input) {
        GetBookingInfoRequest GetBookingInfoRequest = super.ReadRequest(Input, GetBookingInfoRequest.class);

        GetBookingInfoResponse GetBookingInfoResponse = GetBookingInfoSql.Get(GetBookingInfoRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), GetBookingInfoResponse);
    }
}