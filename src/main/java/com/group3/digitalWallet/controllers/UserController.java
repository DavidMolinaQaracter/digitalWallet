package com.group3.digitalWallet.controllers;
import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.User;
import com.group3.digitalWallet.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody Map<String, String> request) {
        return userService.createUser(request.get("name"));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Endpoint para añadir saldo: /users/1/balance
    @PostMapping("/{id}/balance")
    public User addBalance(@PathVariable int id, @RequestBody Map<String, Object> request) {
        Currency curr = Currency.valueOf(request.get("currency").toString().toUpperCase());
        Double amount = Double.valueOf(request.get("amount").toString());
        userService.deposit(id, curr, amount);
        return userService.getUserById(id);
    }
}