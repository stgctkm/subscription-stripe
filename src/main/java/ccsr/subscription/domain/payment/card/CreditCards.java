package ccsr.subscription.domain.payment.card;

import java.util.ArrayList;
import java.util.List;

public class CreditCards {
    List<CreditCard> cards = new ArrayList<>();

    public CreditCards(List<CreditCard> cards) {
        this.cards = cards;
    }
}
