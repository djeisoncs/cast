package br.com.cast.avaliacao.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by djeison.cassimiro on 03/01/2021
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.packages("br.com.cast.avaliacao.controller");
    }
}
