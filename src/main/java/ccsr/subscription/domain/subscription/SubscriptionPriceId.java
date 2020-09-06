package ccsr.subscription.domain.subscription;

public class SubscriptionPriceId {
    String value;

    public SubscriptionPriceId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
