package Server.API.Users.Response;

import Struct.Court;

import java.util.ArrayList;

public class GetAllCourtInCenterResponse {
    public GetAllCourtInCenterResponse(int ResultCode) { this.ResultCode = ResultCode; this.CurrentCourts = new ArrayList<>(); }
    public int ResultCode;
    public ArrayList<Court> CurrentCourts;
}
