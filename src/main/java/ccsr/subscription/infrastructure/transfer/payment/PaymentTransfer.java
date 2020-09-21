package ccsr.subscription.infrastructure.transfer.payment;

import ccsr.subscription.application.repository.payment.PaymentRepository;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.payment.Preparation;
import com.stripe.exception.StripeException;
import com.stripe.model.SetupIntent;
import com.stripe.param.SetupIntentCreateParams;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentTransfer implements PaymentRepository  {

    @Override
    public Preparation prepare(Customer customer) {
        SetupIntentCreateParams params = new SetupIntentCreateParams.Builder()
                .setCustomer(customer.customerId().toString())
                .build();
        try {
            SetupIntent setupIntent = SetupIntent.create(params);
            return new Preparation(setupIntent.getClientSecret(), customer.email().toString());

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
