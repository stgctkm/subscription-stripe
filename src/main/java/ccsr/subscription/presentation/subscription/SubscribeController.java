package ccsr.subscription.presentation.subscription;

import ccsr.subscription.application.service.customer.CustomerService;
import ccsr.subscription.application.service.subscription.SubscriptionService;
import ccsr.subscription.domain.customer.CustomerId;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.payment.PaymentMethodId;
import ccsr.subscription.domain.student.Student;
import ccsr.subscription.domain.subscription.SubscriptionPriceId;
import ccsr.subscription.presentation.view.subscription.CreateSubscriptionRequest;
import com.stripe.exception.CardException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
class SubscribeController {

    CustomerService customerService;
    SubscriptionService subscriptionService;

    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    String createSubscription(@RequestBody CreateSubscriptionRequest request) throws Exception {
//    String createSubscription() throws Exception {

        ccsr.subscription.domain.customer.Customer customer =
                customerService.retrieveCustomer(new Student(null));

        SubscriptionPriceId trainingServicePriceId =
                new SubscriptionPriceId(request.priceId());
//        new SubscriptionPriceId("price_1HM1mnCv6UGSRfZUSGlFIv5P");


//        PaymentMethodId paymentMethodId = new PaymentMethodId("pm_1HTfmsCv6UGSRfZUjRw2oSWr");
        PaymentMethodId paymentMethodId = new PaymentMethodId(request.paymentMethodId());
        customerService.update(customer, paymentMethodId);

        subscriptionService.subscribe(
                new ccsr.subscription.domain.subscription.Subscription(
                        customer,
                        trainingServicePriceId //TODO
                ));


        return "success";
    }

    SubscribeController(CustomerService customerService, SubscriptionService subscriptionService) {
        this.customerService = customerService;
        this.subscriptionService = subscriptionService;
    }
}
