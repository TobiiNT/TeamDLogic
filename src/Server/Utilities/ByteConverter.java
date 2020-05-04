package Server.Utilities;

import java.io.InputStream;
import java.util.Scanner;

public class ByteConverter {
    public static String Convert(InputStream InputStream)  {
        Scanner sc = new Scanner(InputStream);
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()){
            sb.append(sc.nextLine());
        }
        return sb.toString();
    }
}
