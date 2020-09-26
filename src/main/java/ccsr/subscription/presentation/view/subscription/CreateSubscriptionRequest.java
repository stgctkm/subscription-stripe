package ccsr.subscription.presentation.view.subscription;

public class CreateSubscriptionRequest {
    String customerId;
    String paymentMethodId;
    String priceId;

    public String customerId() {
        return customerId;
    }

    public String paymentMethodId() {
        return paymentMethodId;
    }

    public String priceId() {
        return priceId;
    }
}
