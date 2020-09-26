package ccsr.subscription.application.repository.payment;

import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.payment.Preparation;
import ccsr.subscription.domain.payment.card.CreditCards;

public interface PaymentRepository {
    Preparation prepare(Customer customer);

    CreditCards list(Customer customer);
}
