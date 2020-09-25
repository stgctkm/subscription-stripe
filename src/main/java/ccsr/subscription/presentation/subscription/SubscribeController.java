package ccsr.subscription.presentation.subscription;

import ccsr.subscription.application.service.customer.CustomerService;
import ccsr.subscription.application.service.subscription.SubscriptionService;
import ccsr.subscription.domain.payment.PaymentMethodId;
import ccsr.subscription.domain.subscription.SubscriptionPriceId;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.subscription.Subscription;
import ccsr.subscription.presentation.view.subscription.CreateSubscriptionRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SubscribeController {

    CustomerService customerService;
    SubscriptionService subscriptionService;

    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    String createSubscription(@RequestBody CreateSubscriptionRequest request) throws Exception {

        Customer customer = customerService.retrieveCustomer();

        SubscriptionPriceId trainingServicePriceId =
                new SubscriptionPriceId(request.priceId());

        PaymentMethodId paymentMethodId = new PaymentMethodId(request.paymentMethodId());
        customerService.update(customer, paymentMethodId);

        subscriptionService.subscribe(new Subscription(customer, trainingServicePriceId));

        return "success";
    }

    SubscribeController(CustomerService customerService, SubscriptionService subscriptionService) {
        this.customerService = customerService;
        this.subscriptionService = subscriptionService;
    }
}
