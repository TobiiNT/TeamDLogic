package Server.API.Users.Handler;

import Database.Request.GetAllCourtInCenterSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.GetAllCourtInCenterRequest;
import Server.API.Users.Response.GetAllCourtInCenterResponse;

public class GetAllCourtInCenterHandler extends Handler {
    public GetAllCourtInCenterHandler(String MethodAllowed, com.fasterxml.jackson.databind.ObjectMapper ObjectMapper, Server.Errors.GlobalExceptionHandler GlobalExceptionHandler) {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<GetAllCourtInCenterResponse> DoPost(String Input) {
        GetAllCourtInCenterRequest GetAllCourtInCenterRequest = ReadRequest(Input, GetAllCourtInCenterRequest.class);

        GetAllCourtInCenterResponse GetAllCourtInCenterResponse = GetAllCourtInCenterSql.Get(GetAllCourtInCenterRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), GetAllCourtInCenterResponse);
    }
}
