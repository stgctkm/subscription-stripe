package ccsr.subscription.domain.student;

import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.CustomerId;
import ccsr.subscription.domain.customer.Email;

public class Student {
    Customer customer;

    public Student(Customer customer) {
        this.customer = customer;
    }

    public CustomerId customerId() {
        return customer.customerId();
    }

    public Email email() {
        return customer.email();
    }
}
