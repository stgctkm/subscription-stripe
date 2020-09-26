package ccsr.subscription.domain.payment;

public class Preparation {
    String clientSecret;
    String email;

    public Preparation(String clientSecret, String email) {
        this.clientSecret = clientSecret;
        this.email = email;
    }
}
