package ccsr.subscription.infrastructure.transfer.payment;

import ccsr.subscription.application.repository.payment.PaymentRepository;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.payment.Preparation;
import ccsr.subscription.domain.payment.card.CreditCard;
import ccsr.subscription.domain.payment.card.CreditCards;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.model.SetupIntent;
import com.stripe.param.PaymentMethodListParams;
import com.stripe.param.SetupIntentCreateParams;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public CreditCards list(Customer customer) {
        PaymentMethodListParams params =
                PaymentMethodListParams.builder()
                        .setCustomer(customer.customerId().toString())
                        .setType(PaymentMethodListParams.Type.CARD)
                        .build();

        try {
            PaymentMethodCollection paymentMethods = PaymentMethod.list(params);
            List<CreditCard> cards = new ArrayList<>();
            paymentMethods.autoPagingIterable().forEach(it -> {
                        PaymentMethod.Card card = it.getCard();
                        cards.add(new CreditCard(it.getId(), card.getBrand(), card.getLast4()));
                    }
            );
            return new CreditCards(cards);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
