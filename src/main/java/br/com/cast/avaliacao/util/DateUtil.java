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
}
