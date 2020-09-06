package ccsr.subscription.presentation.view.subscription;

import ccsr.subscription.domain.subscription.SubscriptionPriceId;

public class PriceIdRequest {
    String priceId;

    @Override
    public String toString() {
        return priceId;
    }

    public SubscriptionPriceId toSubscriptionPriceId() {
        return new SubscriptionPriceId(priceId);
    }
}
