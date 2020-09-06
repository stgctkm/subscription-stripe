package ccsr.subscription.domain.subscription;

public class SessionId {
    String value;

    public SessionId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
