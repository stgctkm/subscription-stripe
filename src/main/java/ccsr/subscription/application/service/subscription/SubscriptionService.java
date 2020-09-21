package ccsr.subscription.application.service.subscription;

import ccsr.subscription.application.repository.subscription.SubscriptionRepository;
import ccsr.subscription.domain.subscription.SessionId;
import ccsr.subscription.domain.subscription.Subscription;
import ccsr.subscription.domain.subscription.SubscriptionPriceId;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;

    public SessionId creatCheckoutSession(SubscriptionPriceId subscriptionPriceId) {
        return subscriptionRepository.creatCheckoutSession(subscriptionPriceId);
    }

    public void subscribe(Subscription subscription) {
        subscriptionRepository.subscribe(subscription);
    }

    SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
}
