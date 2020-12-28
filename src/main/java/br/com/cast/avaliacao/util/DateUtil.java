package br.com.cast.avaliacao.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by djeison.cassimiro on 28/12/2020
 */
public class DateUtil {

    public static Date somarDiasAData(Date data, int dias) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, dias);
        return c.getTime();
    }

    public static Date zerarHoraData(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        zerarHora(calendar);

        return calendar.getTime();
    }

    private static void zerarHora(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
