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

        public void deposit(int userId, Currency currency, Double amount) {
            getUserById(userId).deposit(amount, currency);
        }

        public boolean withdraw(int userId, Currency currency, Double amount) {
            if(getUserById(userId).getBalance() - amount < 0)
                return false;

            getUserById(userId).withdraw(amount, currency);
            return true;
        }

        public User getUserById(int userId) {
            for (User user : users) {
                if (user.getId() == userId) {
                    return user;
                }
            }
            return null;
        }

        public
}