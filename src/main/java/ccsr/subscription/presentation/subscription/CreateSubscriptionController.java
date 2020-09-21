package ccsr.subscription.presentation.subscription;

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
class CreateSubscriptionController {

    @PostMapping(value = "/create-subscription", produces = MediaType.APPLICATION_JSON_VALUE)
    Subscription createSubscription(@RequestBody CreateSubscriptionRequest request) throws Exception {
        Customer customer = Customer.retrieve(request.customerId());

        try {
            // Set the default payment method on the customer
            PaymentMethod pm = PaymentMethod.retrieve(
                    request.paymentMethodId()
            );
            pm.attach(
                    PaymentMethodAttachParams
                            .builder()
                            .setCustomer(customer.getId())
                            .build()
            );
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught
            Map<String, String> responseErrorMessage = new HashMap<>();
            responseErrorMessage.put("message", e.getLocalizedMessage());
            Map<String, Object> responseError = new HashMap<>();
            responseError.put("error", responseErrorMessage);

//            return gson.toJson(responseError);
        }

        CustomerUpdateParams customerUpdateParams =
                CustomerUpdateParams
                .builder()
                .setInvoiceSettings(
                        CustomerUpdateParams
                                .InvoiceSettings.builder()
                                .setDefaultPaymentMethod(request.paymentMethodId())
                                .build()
                )
                .build();

        customer.update(customerUpdateParams);

        // Create the subscription
        SubscriptionCreateParams subCreateParams = SubscriptionCreateParams
                .builder()
                .addItem(
                        SubscriptionCreateParams
                                .Item.builder()
                                .setPrice(request.priceId())
                                .build()
                )
                .setCustomer(customer.getId())
                .addAllExpand(Arrays.asList("latest_invoice.payment_intent"))
                .build();

        Subscription subscription = Subscription.create(subCreateParams);

        return subscription;
    }

}
