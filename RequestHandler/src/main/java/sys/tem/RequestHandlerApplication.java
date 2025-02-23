package sys.tem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"sys.tem.model"})
public class RequestHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestHandlerApplication.class, args);
    }
}