package Server.API.Users.Response;

import Struct.FreeSlot;

import java.util.ArrayList;

public class GetAvalableSlotInCenterResponse {
    public GetAvalableSlotInCenterResponse(int ResultCode) { this.ResultCode = ResultCode; this.CurrentFreeSlots = new ArrayList<>(); }
    public int ResultCode;
    public ArrayList<FreeSlot> CurrentFreeSlots;
}
