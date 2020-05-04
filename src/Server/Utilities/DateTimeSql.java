package Server.Utilities;

import java.sql.Time;

public class DateTimeSql {
    public static long getDiffByMinute(Time DateOne, Time DateTwo) { //Convert difference between two time to minutes
        long Diff = (DateTwo.getTime() - DateOne.getTime()) / (60 * 1000);
        return Diff;
    }
}
