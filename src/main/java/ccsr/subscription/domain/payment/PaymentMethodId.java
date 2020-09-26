package ccsr.subscription.domain.payment;

public class PaymentMethodId {
    String value;

    public PaymentMethodId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
