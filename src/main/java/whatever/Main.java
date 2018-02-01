package whatever;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import whatever.model.ProductCategoryRepository;
import whatever.model.ProductRepository;


@SpringBootApplication
public class Main {


    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }
}