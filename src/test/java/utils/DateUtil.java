package utils;

import infrastructure.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static DateUtil instance = null;

    private DateUtil() {
    }

    public static synchronized DateUtil getInstance() {
        if (instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }

    public String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        Logger.getInstance().info(String.format("Today is %s", today));
        return today;
    }

}
