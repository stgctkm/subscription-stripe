package ccsr.subscription.application.repository.customer;

import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.payment.PaymentMethodId;

public interface CustomerRepository {
    Customer createCustomer(Email email);

    Customer retrieveCustomer();

    void update(Customer customer, PaymentMethodId paymentMethodId);
}
