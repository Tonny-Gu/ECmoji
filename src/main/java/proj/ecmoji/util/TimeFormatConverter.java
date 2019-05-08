package proj.ecmoji.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatConverter {
    private static SimpleDateFormat JDformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static long JDFormatToLong(String JDCommentTime) throws Exception {
        Date date = JDformat.parse(JDCommentTime);
        return date.getTime();
    }
}