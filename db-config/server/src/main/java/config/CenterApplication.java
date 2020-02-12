package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zcm
 */
@SpringBootApplication
@EnableConfigServer
public class CenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class,args);
    }
}
