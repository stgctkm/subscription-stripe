package ccsr.subscription.infrastructure.transfer.customer;

import ccsr.subscription.application.repository.customer.CustomerRepository;
import ccsr.subscription.domain.customer.CustomerId;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.payment.PaymentMethodId;
import com.stripe.exception.StripeException;
import ccsr.subscription.domain.customer.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import org.springframework.stereotype.Component;

@Component
public class CustomerTransfer implements CustomerRepository {

    String customerId;

    @Override
    public Customer createCustomer(Email email) {
        CustomerCreateParams customerParams = CustomerCreateParams
                .builder()
                .setEmail(email.toString())
                .build();

        try {
            var customer = com.stripe.model.Customer.create(customerParams);
            customerId = customer.getId();
            return new Customer(
                    new CustomerId(customer.getId()),
                    email);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer retrieveCustomer() {
        try {
            var customer = com.stripe.model.Customer.retrieve(customerId);
            return new Customer(
                    new CustomerId(customer.getId()),
                    new Email(customer.getEmail()));
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer, PaymentMethodId paymentMethodId) {

        com.stripe.model.Customer stripeCustomer = null;
        try {
            stripeCustomer = com.stripe.model.Customer.retrieve(customer.customerId().toString());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        CustomerUpdateParams customerUpdateParams =
                CustomerUpdateParams
                        .builder()
                        .setInvoiceSettings(
                                CustomerUpdateParams
                                        .InvoiceSettings.builder()
                                        .setDefaultPaymentMethod(paymentMethodId.toString())
                                        .build()
                        )
                        .build();

        try {
            stripeCustomer.update(customerUpdateParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
