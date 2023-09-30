package br.ufpb.dcx.project;

import br.ufpb.dcx.project.filter.FiltroDeTokens;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab1v1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab1v1Application.class, args);
    }
    @Bean
    public FilterRegistrationBean<FiltroDeTokens> filterJwt() {
        FilterRegistrationBean<FiltroDeTokens> filterRB = new FilterRegistrationBean<FiltroDeTokens>();
        filterRB.setFilter(new FiltroDeTokens());
        filterRB.addUrlPatterns("/auth/api/disciplinas/*", "/auth/api/user/*");
        return filterRB;
    }
}
