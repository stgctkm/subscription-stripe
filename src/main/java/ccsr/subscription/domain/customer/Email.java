package ccsr.subscription.domain.customer;

public class Email {
    String value;

    public Email(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
