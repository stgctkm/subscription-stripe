package ccsr.subscription.infrastructure.transfer.subscription;

import ccsr.subscription.application.repository.subscription.SubscriptionRepository;
import ccsr.subscription.domain.subscription.SessionId;
import ccsr.subscription.domain.subscription.SubscriptionPriceId;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionTransfer implements SubscriptionRepository {

    String domainUrl;

    @Override
    public SessionId creatCheckoutSession(SubscriptionPriceId subscriptionPriceId) {
        SessionCreateParams.Builder builder = new SessionCreateParams.Builder();
        builder.setSuccessUrl(domainUrl + "/subscription/success.html?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(domainUrl + "/subscription/canceled.html")
                .setCustomer("cus_HvtrRGqunHF95m")
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)

                .setMode(SessionCreateParams.Mode.SUBSCRIPTION);

        SessionCreateParams.LineItem item =
                new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L)
                        .setPrice(subscriptionPriceId.toString())
                        .build();
        builder.addLineItem(item);

        SessionCreateParams createParams = builder.build();
        try {
            Session session = Session.create(createParams);
            return new SessionId(session.getId());
        } catch (StripeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    SubscriptionTransfer(@Value("${domain}") String domainUrl) {
        this.domainUrl = domainUrl;
    }

}
