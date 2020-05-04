package Server.API.Users.Handler;

import Database.Request.CreateBookingSql;
import Server.API.Constants;
import Server.API.Handler;
import Server.API.ResponseEntity;
import Server.API.StatusCode;
import Server.API.Users.Request.CreateBookingRequest;
import Server.API.Users.Response.CreateBookingResponse;
import Server.Errors.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class CreateBookingHandler extends Handler {
    public CreateBookingHandler(String MethodAllowed, ObjectMapper ObjectMapper, GlobalExceptionHandler GlobalExceptionHandler)  {
        super(MethodAllowed, ObjectMapper, GlobalExceptionHandler);
    }

    @Override
    protected ResponseEntity<CreateBookingResponse> DoPost(String Input) {
        CreateBookingRequest CreateBookingRequest = ReadRequest(Input, CreateBookingRequest.class);

        CreateBookingResponse CreateBookingResponse = CreateBookingSql.Create(CreateBookingRequest);

        return new ResponseEntity<>(StatusCode.OK, GetHeaders(Constants.ContentType, Constants.ApplicationJSON), CreateBookingResponse);
    }
}