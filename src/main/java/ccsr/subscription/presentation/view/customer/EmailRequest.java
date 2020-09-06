package ccsr.subscription.presentation.view.customer;

import ccsr.subscription.domain.customer.Email;

public class EmailRequest {
    String email;

    public Email toEmail() {
        return new Email(email);
    }
}
