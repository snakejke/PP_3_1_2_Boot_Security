package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController() {//todo: зачем?
    }

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/admin/allusers")
    public String displayAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsersWithRoles());
        return "allusers";//todo: достаточно users (all -избыточно)
    }

    @GetMapping("/user")
    public String getUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = {"/", "/login"})
    public String loginForm() {
        return "login";
    }

    @GetMapping("/admin/adduser")//todo: описал..
    public String displayNewUserForm(Model model) {
        List<Role> availableRoles = userService.getAllRoles();
        model.addAttribute("headerMessage", "Add User Details");
        model.addAttribute("user", new User());
        model.addAttribute("availableRoles", availableRoles);
        return "adduser";//todo: описал..
    }

    @PostMapping("/admin/adduser")
    public String saveNewUser(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        String plainPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(encodedPassword);
        //todo: codeStyle пустые строки в методе
        userService.saveUser(user);
        return "redirect:/admin/allusers";
    }

    @GetMapping("/admin/edituser")
    public String displayEditUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> availableRoles = userService.getAllRoles();
        model.addAttribute("headerMessage", "Edit User Details");
        model.addAttribute("user", user);
        model.addAttribute("availableRoles", availableRoles);
        model.addAttribute("password", "");
        return "edituser";//todo: описал..
    }

    @PostMapping("/admin/edituser")
    public String saveEditedUser(@ModelAttribute("user") @Valid User user,
            @RequestParam String password, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        if (!password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userService.saveUser(user);
        return "redirect:/admin/allusers";//todo: описал..
    }

    @GetMapping("/admin/deleteuser")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/allusers";//todo: описал..
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

}

