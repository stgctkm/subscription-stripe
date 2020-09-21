package ccsr.subscription.application.service.payment;

import ccsr.subscription.application.repository.payment.PaymentRepository;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.payment.Preparation;
import ccsr.subscription.domain.payment.card.CreditCards;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {

    PaymentRepository paymentRepository;

    public Preparation preparePaymentMethod(Customer customer) {
        return paymentRepository.prepare(customer);
    }

    public CreditCards list(Customer customer) {
        CreditCards cards = paymentRepository.list(customer);
        return cards;
    }

    PaymentMethodService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
