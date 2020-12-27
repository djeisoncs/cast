package br.com.cast.avaliacao.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Slf4j
public class MensagemI18N {
    public static final Locale localeDefault = new Locale.Builder().setLanguage("pt").setRegion("BR").build();

    public static ResourceBundle getBundle() {
        return getBundle(localeDefault);
    }

    public static ResourceBundle getBundle(Locale locale) {
        ResourceBundle resourceBundle = null;

        try{
            resourceBundle = ResourceBundle.getBundle("mensagens", locale);

        }catch (MissingResourceException e){
            log.error("Não foi encontrado arquivo de mensagens", e);

        }

        return resourceBundle;
    }

    public static String getKey(String key){
        return getKey(key, localeDefault);
    }

    public static String getKey(String key, @Nullable Object[] parametros){
        return getKey(key, parametros, localeDefault);
    }

    public static String getKey(String key, Locale locale){
        return getKey(key, new Object[0], locale);
    }

    public static String getKey(String key, @Nullable Object[] parametros, Locale locale){
        String mensagem;
        try{

            mensagem = MessageFormat.format(getBundle(locale).getString(key), parametros);

        }catch (MissingResourceException e){
            log.warn("Não foi encontrado a key no arquivo de mensagens: "+key);
            mensagem = key;

        }

        return mensagem;
    }

}
