package Server.API.Users.Handler;

import Database.Request.GetAvalableSlotInCenterSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.GetAvalableSlotInCenterRequest;
import Server.API.Users.Response.GetAvalableSlotInCenterResponse;
import Server.Errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetAvalableSlotInCenterHandler extends Handler {
    public GetAvalableSlotInCenterHandler(String MethodAllowed, ObjectMapper ObjectMapper, GlobalExceptionHandler GlobalExceptionHandler)  {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<GetAvalableSlotInCenterResponse> DoPost(String Input) {
        GetAvalableSlotInCenterRequest GetAvalableSlotInCenterRequest = ReadRequest(Input, GetAvalableSlotInCenterRequest.class);

        GetAvalableSlotInCenterResponse GetAvalableSlotInCenterResponse = GetAvalableSlotInCenterSql.Get(GetAvalableSlotInCenterRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), GetAvalableSlotInCenterResponse);
    }


}
