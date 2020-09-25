package ccsr.subscription.domain.payment.card;

public class CreditCard {

    String paymentMethodId;
    String brand;
    String last4;

    public CreditCard(String id, String brand, String last4) {
        this.paymentMethodId = id;
        this.brand = brand;
        this.last4 = last4;
    }

}
