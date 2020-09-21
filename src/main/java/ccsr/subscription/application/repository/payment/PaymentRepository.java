package ccsr.subscription.application.repository.payment;

import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.payment.Preparation;

public interface PaymentRepository {
    Preparation prepare(Customer customer);
}
