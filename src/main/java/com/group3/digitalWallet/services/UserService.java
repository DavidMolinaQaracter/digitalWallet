package com.group3.digitalWallet.services;
import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


    @Service
    public class UserService {
        private final List<User> users = new ArrayList<>();
        private int nextId = 1;

        // Crea un usuario con carteras vacías
        public User createUser(String name) {
            User user = new User(nextId++, name, new HashMap<>());
            users.add(user);
            return user;
        }

        public List<User> getAllUsers() {
            return users;
        }

        // Añade o actualiza el saldo de una moneda específica
        public User addBalance(int userId, Currency currency, Double amount) {
            return users.stream()
                    .filter(u -> u.getId() == userId)
                    .findFirst()
                    .map(user -> {
                        user.getWallets().put(currency, amount);
                        return user;
                    }).orElse(null);
        }

        public User getUserById(int userId) {
            for (User user : users) {
                if (user.getId() == userId) {
                    return user;
                }
            }
            return null;
        }
}