import Database.MySqlConnection;
import Database.Request.CreateCitySql;
import Database.Request.GetAllCitySql;
import Server.API.Users.Request.CreateCityRequest;
import Server.API.Users.Response.CreateCityResponse;
import Server.API.Users.Response.GetAllCityResponse;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

public class TestBooking {
    private static String DbUrl = "jdbc:mysql://103.90.227.107:3306/booking_app";
    private static String DbUsername = "sa";
    private static String DbPassword = "teamdpe2020";
    int CurrentCityID = 0;

    @Before
    public void SetUp() throws SQLException, ClassNotFoundException {
        MySqlConnection.OpenConnection(DbUrl, DbUsername, DbPassword);
        //DeleteAllDataSql.Delete();
    }

    @Test
    public void CreateNewCity() {
        CreateCityRequest Request = new CreateCityRequest();
        Request.CityName = "HCM";
        CreateCityResponse Response = CreateCitySql.Create(Request);
        assertEquals(100, Response.ResultCode);

        CurrentCityID = Response.CityID;
        assertNotSame(0, CurrentCityID);
    }

    @Test
    public void SelectAllCity() {
        GetAllCityResponse Response = GetAllCitySql.Get();
        assertEquals(800, Response.ResultCode);

        boolean CreatedNewCity = false;

        for (int i = 0; i < Response.CurrentCities.size(); i++) {
            CreatedNewCity = Response.CurrentCities.get(i).CityID == CurrentCityID;
            if (CreatedNewCity)
                break;
        }

        assertEquals(true, CreatedNewCity);
    }
}
