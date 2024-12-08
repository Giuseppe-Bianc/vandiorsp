package org.dersbian.vandiorsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VandiorspApplication {

    public static void main(String[] args) {
        SpringApplication.run(VandiorspApplication.class, args);
    }

}
