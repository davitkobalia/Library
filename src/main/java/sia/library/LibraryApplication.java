package sia.library;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import sia.library.Service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class LibraryApplication {
    UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
