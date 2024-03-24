package sia.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.library.Security.User;
import sia.library.Service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/users/create")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getAllRoles());
        return "user/create-user";
    }

    @PostMapping("/admin/users")
    public String saveUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/user-list";
    }


    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}

