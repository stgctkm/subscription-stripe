package ccsr.subscription.domain.customer;

public class CustomerId {
    String value;

    public CustomerId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
