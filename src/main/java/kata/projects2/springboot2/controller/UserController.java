package kata.projects2.springboot2.controller;

import jakarta.transaction.Transactional;
import kata.projects2.springboot2.model.User;
import kata.projects2.springboot2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@Component
@RequestMapping("/")
@Transactional
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/error")
    public String error () {
        return "error";
    }

    @GetMapping(value = "/users")
    public String showAllUsersOnWeb(Model model) {
        List<User> usersList = userService.showUsers();
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/refactor")
    public String refactorUsers(Model model,@PathVariable("id") int id) {
        model.addAttribute("user", userService.findUserById(id));
        return "refactor";
    }

    @PostMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
