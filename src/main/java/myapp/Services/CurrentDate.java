package myapp.Services;

import java.text.SimpleDateFormat;
import java.util.Objects;

public final class CurrentDate {

    public static SimpleDateFormat formater;

    static {
        try {
            formater = new SimpleDateFormat("dd.MM.yyyy");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static CurrentDate instance;
    private String date;

    private CurrentDate(String date) {
        this.date = date;
    }

    public static CurrentDate Set_current_date(String date) throws Exception {
        if (Objects.isNull(instance)) {
            instance = new CurrentDate(date);
        }
        instance.date = date;
        return instance;
    }

    public static String Get_current_date() throws Exception {
        if (instance == null) {
            instance = new CurrentDate(CurrentDate.formater.format(System.currentTimeMillis()));
        }
        return instance.date;
    }
}