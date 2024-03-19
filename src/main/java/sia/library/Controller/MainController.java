package sia.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sia.library.Security.User;
import sia.library.Service.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {
    UserService userService;


    @GetMapping("/")
    public String mainMenu(){
        return "main/main";
    }


}
