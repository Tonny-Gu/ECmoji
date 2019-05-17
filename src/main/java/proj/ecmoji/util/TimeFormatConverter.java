package proj.ecmoji.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatConverter {
    private static SimpleDateFormat JDformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static long JDFormatToLong(String JDCommentTime) throws Exception {
        return JDformat.parse(JDCommentTime).getTime();
    }
    public static String LongToJDFormat(long time) throws Exception {
        return JDformat.format( new Date(time) );
    }
}