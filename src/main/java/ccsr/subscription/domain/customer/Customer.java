package ccsr.subscription.domain.customer;

public class Customer {
    CustomerId customerId;
    Email email;

    public Customer(CustomerId customerId, Email email) {
        this.customerId = customerId;
        this.email = email;
    }

}
