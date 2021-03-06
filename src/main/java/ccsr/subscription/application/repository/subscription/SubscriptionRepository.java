package ccsr.subscription.application.repository.subscription;

import ccsr.subscription.domain.subscription.SessionId;
import ccsr.subscription.domain.subscription.Subscription;
import ccsr.subscription.domain.subscription.SubscriptionPriceId;

public interface SubscriptionRepository {
    SessionId creatCheckoutSession(SubscriptionPriceId subscriptionPriceId);

    void subscribe(Subscription subscription);
}
