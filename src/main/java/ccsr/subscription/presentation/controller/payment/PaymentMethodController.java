package ccsr.subscription.presentation.controller.payment;

import ccsr.subscription.application.service.customer.CustomerService;
import ccsr.subscription.application.service.payment.PaymentMethodService;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.payment.Preparation;
import ccsr.subscription.domain.payment.card.CreditCards;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PaymentMethodController {
    CustomerService customerService;
    PaymentMethodService paymentMethodService;


    @PostMapping(value = "/create-setup-intent", produces = MediaType.APPLICATION_JSON_VALUE)
    Preparation createSetupIntent() {
        Customer customer = customerService.retrieveCustomer();

        Preparation preparation = paymentMethodService.preparePaymentMethod(customer);
        return preparation;
    }

    @GetMapping(value = "/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditCards list() {
        Customer customer = customerService.retrieveCustomer();
        CreditCards cards = paymentMethodService.list(customer);
        return cards;
    }

    PaymentMethodController(CustomerService customerService,
                            PaymentMethodService paymentMethodService) {
        this.customerService = customerService;
        this.paymentMethodService = paymentMethodService;
    }

}
