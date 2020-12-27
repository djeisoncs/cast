package br.com.cast.avaliacao.config;

import br.com.cast.avaliacao.util.Constantes;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */

@Configuration
public class ConfigurationFilter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DataSource dataSource;

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${cast.flyway-ativo}")
    private Boolean flywayAtivo;

    @Bean
    public FilterRegistrationBean<CORSFilter> registrarCORSFilter() {
        FilterRegistrationBean<CORSFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CORSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CORSFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public boolean flyway() {
        boolean retorno = false;

        if(flywayAtivo){
            final Flyway flyway = new Flyway();
            flyway.setSchemas(Constantes.SCHEMA_CAST);
            flyway.setDataSource(dataSource);
            flyway.setBaselineOnMigrate(true);
            flyway.migrate();
            retorno = true;
        }

        return retorno;
    }
}
