package Server.API.Users.Handler;

import Database.Request.CreateCityCenterSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.CreateCityCenterRequest;
import Server.API.Users.Response.CreateCityCenterResponse;
import Server.Errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateCityCenterHandler extends Handler {
    public CreateCityCenterHandler(String MethodAllowed, ObjectMapper ObjectMapper, GlobalExceptionHandler GlobalExceptionHandler)  {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<CreateCityCenterResponse> DoPost(String Input) {
        CreateCityCenterRequest CreateCityCenterRequest = ReadRequest(Input, CreateCityCenterRequest.class);

        CreateCityCenterResponse CreateCityCenterResponse = CreateCityCenterSql.Create(CreateCityCenterRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), CreateCityCenterResponse);
    }
}
