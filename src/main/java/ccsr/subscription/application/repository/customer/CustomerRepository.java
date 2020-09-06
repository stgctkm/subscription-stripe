package ccsr.subscription.application.repository.customer;

import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.Email;

public interface CustomerRepository {
    Customer createCustomer(Email email);
}
