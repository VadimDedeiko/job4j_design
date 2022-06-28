package ru.job4j.ood.dip;

/**
 * Нарушение принципа DIP заключается в использовании конкретных реализаций хранилища и сервиса отправки уведомлений,
 * а не их абстракций
 */

public class OrderProcessor {

    private MySQLOrderStore repository = new MySQLOrderStore();
    private ConfirmationEmailSender mailSender = new ConfirmationEmailSender();

    public void process(Order order) {
        if (order.isValid() && repository.save(order)) {
            mailSender.sendConfirmationEmail(order);
        }
    }
}

class MySQLOrderStore {
    public boolean save(Order order) {
        return true;
    }
}

class ConfirmationEmailSender {
    public void sendConfirmationEmail(Order order) {
    }
}

class Order {
    private String customerName;
    private String customerEmail;

    public Order(String customerName, String customerEmail) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public boolean isValid() {
        return true;
    }
}

