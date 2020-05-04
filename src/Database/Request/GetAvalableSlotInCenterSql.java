package Database.Request;

import Server.API.Users.Request.GetAllCourtInCenterRequest;
import Server.API.Users.Request.GetAvalableSlotInCenterRequest;
import Server.API.Users.Request.GetCourtBookingRequest;
import Server.API.Users.Response.GetAllCourtInCenterResponse;
import Server.API.Users.Response.GetAvalableSlotInCenterResponse;
import Server.Utilities.DateTimeSql;
import Struct.Booking;
import Struct.Court;
import Struct.FreeSlot;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class GetAvalableSlotInCenterSql {
    public static GetAvalableSlotInCenterResponse Get(GetAvalableSlotInCenterRequest Request)
    {
        ArrayList<FreeSlot> AllFreeSlots = new ArrayList<>();

        GetAllCourtInCenterRequest GetAllCourtInCenterRequest = new GetAllCourtInCenterRequest();
        GetAllCourtInCenterRequest.CenterID = Request.CenterID;

        GetAllCourtInCenterResponse Response = GetAllCourtInCenterSql.Get(GetAllCourtInCenterRequest);
        ArrayList<Court> CourtList = Response.CurrentCourts;
        int ResultCode = Response.ResultCode;

        if (ResultCode == 1000)
        {
            for (int j = 0; j < CourtList.size(); j++) {
                Court CurrentCourt = CourtList.get(j);

                ArrayList<FreeSlot> SlotList = GetAllFreeSlotsInCourt(CurrentCourt, Request.BookingDay);
                for (int k = 0; k < SlotList.size(); k++) {
                    AllFreeSlots.add(SlotList.get(k));
                }
            }
        }

        GetAvalableSlotInCenterResponse GetAvalableSlotResponse = new GetAvalableSlotInCenterResponse(ResultCode);
        GetAvalableSlotResponse.CurrentFreeSlots = AllFreeSlots;

        return GetAvalableSlotResponse;
    }

    private static ArrayList<FreeSlot> GetAllFreeSlotsInCourt(Court CurrentCourt, Date BookingDay) {
        Time OpeningTime = Time.valueOf("07:00:00"); //Center opening time
        Time ClosingTime = Time.valueOf("21:00:00"); //Center closing time
        int MinimumLength = 45;

        GetCourtBookingRequest GetCourtBookingRequest = new GetCourtBookingRequest();
        GetCourtBookingRequest.CourtID = CurrentCourt.CourtID;
        GetCourtBookingRequest.BookingDay = BookingDay;

        ArrayList<Booking> BookingList = GetCourtBookingSql.Get(GetCourtBookingRequest).CurrentBookings;

        ArrayList<FreeSlot> SlotList = new ArrayList<FreeSlot>();
        if (BookingList.size() > 0) {
            for (int i = 0; i < BookingList.size(); i++) {
                if (BookingList.get(i).BookingDay.toString().compareTo(BookingDay.toString()) == 0) { //only check booking at selected date
                    if (i == 0 && DateTimeSql.getDiffByMinute(OpeningTime, BookingList.get(i).BookingStartTime) >= MinimumLength) {
                        SlotList.add(new FreeSlot(CurrentCourt.CourtID, OpeningTime, BookingList.get(i).BookingStartTime));
                    }
                    if (i == BookingList.size() - 1 && DateTimeSql.getDiffByMinute(BookingList.get(i).BookingEndTime, ClosingTime) >= MinimumLength) {
                        SlotList.add(new FreeSlot(CurrentCourt.CourtID, BookingList.get(i).BookingEndTime, ClosingTime));
                    }
                    if (i < BookingList.size() - 1 && DateTimeSql.getDiffByMinute(BookingList.get(i).BookingEndTime, BookingList.get(i + 1).BookingStartTime) >= MinimumLength) {
                        SlotList.add(new FreeSlot(CurrentCourt.CourtID, BookingList.get(i).BookingEndTime, BookingList.get(i + 1).BookingStartTime));
                    }
                }
            }
        }
        else {
            SlotList.add(new FreeSlot(CurrentCourt.CourtID, OpeningTime, ClosingTime));
        }

        return SlotList;
    }
}
