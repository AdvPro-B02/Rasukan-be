package advpro.b2.rasukanbuysell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class RasukanBuySellApplication {

    public static void main(String[] args) {
        SpringApplication.run(RasukanBuySellApplication.class, args);
    }

}
