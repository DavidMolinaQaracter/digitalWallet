package com.group3.digitalWallet.services;

import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScheduledPaymentService {

    private final TransactionService transactionService;


    private final Map<Integer, PaymentThread> activeThreads = new HashMap<>();
    private int nextId = 1;

    public ScheduledPaymentService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    public int createScheduledPayment(User origUser, Currency origCurrency, User destUser, Currency destCurrency, double amount) {
        PaymentThread thread = new PaymentThread(nextId++, origUser, origCurrency, destUser, destCurrency, amount);
        activeThreads.put(nextId++, thread);
        thread.start();
        return nextId++;
    }

    public boolean cancelScheduledPayment(int id) {
        PaymentThread thread = activeThreads.get(id);

        if (thread != null) {
            thread.stopPayment();
            activeThreads.remove(id);
            return true;
        }
        return false;
    }


    public class PaymentThread extends Thread {
        private int id;
        private User origin;
        private User dest;
        private Currency origCurr;
        private Currency destCurr;
        private double amount;

        private boolean active = true;

        public PaymentThread(int id, User origin, Currency origCurr, User dest, Currency destCurr, double amount) {
            this.id = id;
            this.origin = origin;
            this.dest = dest;
            this.origCurr = origCurr;
            this.destCurr = destCurr;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (active) {
                try {
                    System.out.println("Hilo " + id + ": Realizando pago...");
                    transactionService.makeTransaction(origin, origCurr, dest, destCurr, amount);
                    Thread.sleep(10000); //10 segundos entre pagos
                } catch (InterruptedException e) {
                    active = false;
                }
            }
            System.out.println("Hilo " + id + ": Se ha detenido.");
        }

        public void stopPayment() {
            this.active = false;
            this.interrupt();
        }
    }
}