package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.User;
import com.example.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login.html"; // Assuming "login" is the name of your login page HTML file
    }
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Create a new User object and set the role to "USER"
        User user = new User();
        user.setRole("USER");
        model.addAttribute("user", user); // Add the user object to the model
        return "user/register.html"; // Assuming "register" is the name of your register page HTML file
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Save the user
        userService.save(user);
        // Redirect to the login page
        return "redirect:/users/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Your authentication logic here
        // For simplicity, let's assume username is the email
        // You should replace this with your actual authentication logic
        
        // Check if the username and password are valid
        if (isValidUser(username, password)) {
            // If valid, redirect to the home page or any other page you want
            return "redirect:/users/home"; // Assuming "home" is the URL mapping for the home page
        } else {
            // If not valid, redirect back to the login page with an error message
            return "redirect:/users/login?error"; // Assuming "login" is the URL mapping for the login page
        }
    }

    private boolean isValidUser(String username, String password) {
        // Implement your authentication logic here
        // This is just a placeholder method
        // You should replace it with your actual authentication logic
        return true; // For example, always return true for demonstration purposes
    }
    @GetMapping("/home")
    public String showHomePage() {
        return "user/home.html"; // Assuming "login" is the name of your login page HTML file
    }
    @GetMapping("/details/{userId}")
    public String getUserDetailsById(@PathVariable Long userId, Model model) {
        // Retrieve user details from the database based on the ID
        User user = userService.getUserById(userId);

        // Check if user exists
        if (user == null) {
            // Handle case when user is not found
            return "redirect:/error"; // Redirect to an error page or appropriate URL
        }

        // Add user object to the model
        model.addAttribute("user", user);

        // Return the Thymeleaf template name to display user details
        return "userDetails"; // Assuming "userDetails" is the name of your user details page HTML file
    }

    @GetMapping("/logout")
    public String logout() {
        // Add your logout logic here
        return "redirect:/users/login"; // Redirect to the home page or login page after logout
    }
    
}
