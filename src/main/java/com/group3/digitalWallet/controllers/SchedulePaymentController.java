package com.group3.digitalWallet.controllers;

import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.User;
import com.group3.digitalWallet.services.ScheduledPaymentService;
import com.group3.digitalWallet.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class SchedulePaymentController {

    @RestController
    @RequestMapping("/scheduled")
    public class ScheduledPaymentController {

        private final ScheduledPaymentService scheduledPaymentService;
        private final UserService userService;

        public ScheduledPaymentController(ScheduledPaymentService scheduledPaymentService, UserService userService) {
            this.scheduledPaymentService = scheduledPaymentService;
            this.userService = userService;
        }

        @PostMapping
        public String setupRecurringPayment(@RequestBody Map<String, Object> request) {
            int origUserId = Integer.parseInt(request.get("originUserId").toString());
            int destUserId = Integer.parseInt(request.get("dstUserId").toString());
            Currency origCurrency = Currency.valueOf(request.get("origCurrency").toString().toUpperCase()=; // "USD"
            Currency destCurrency = Currency.valueOf(request.get("destCurrency").toString().toUpperCase()=; // "USD"
            double amount = Double.parseDouble(request.get("amount").toString());

            User origUser =userService.getUserById(origUserId);
            User destUser = userService.getUserById(destUserId);
            scheduledPaymentService.schedulePayment(origUser, origCurrency, destUser,  destCurrency, amount);

            return "Pago recurrente configurado correctamente.";
        }
    }
}
