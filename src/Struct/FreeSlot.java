package Struct;

import java.sql.Time;

public class FreeSlot {
    public FreeSlot(int CourtID, Time StartTime, Time EndTime) {
        this.CourtID = CourtID;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }
    public int CourtID;
    public Time StartTime;
    public Time EndTime;
}
