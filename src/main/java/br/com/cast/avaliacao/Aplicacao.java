package br.com.cast.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@SpringBootApplication
@EntityScan(basePackages = Aplicacao.PACOTE_MODEL)
@EnableJpaRepositories(Aplicacao.PACOTE_DAO)
@EnableScheduling
@EnableFeignClients
public class Aplicacao {

    public static final String PACOTE_MODEL = "br.com.cast.avaliacao.model";
    public static final String PACOTE_DAO = "br.com.cast.avaliacao.repository";

    public static void main(String[] args){
        SpringApplication.run(Aplicacao.class, args);
    }
}
