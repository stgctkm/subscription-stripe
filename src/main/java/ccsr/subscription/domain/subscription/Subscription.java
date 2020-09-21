package ccsr.subscription.domain.subscription;

import ccsr.subscription.domain.customer.Customer;

public class Subscription {

    Customer customer;
    SubscriptionPriceId priceId;

    public Subscription(Customer customer, SubscriptionPriceId priceId) {
        this.customer = customer;
        this.priceId = priceId;
    }

    public Customer customer() {
        return customer;
    }

    public SubscriptionPriceId priceId() {
        return priceId;
    }
}
