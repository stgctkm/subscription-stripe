package ccsr.subscription.infrastructure.transfer.customer;

import ccsr.subscription.application.repository.customer.CustomerRepository;
import ccsr.subscription.domain.customer.CustomerId;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.student.Student;
import com.stripe.exception.StripeException;
import ccsr.subscription.domain.customer.Customer;
import com.stripe.param.CustomerCreateParams;
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
    public Customer retrieveCustomer(Student student) {
        try {
            var customer = com.stripe.model.Customer.retrieve(customerId);
            return new Customer(
                    new CustomerId(customer.getId()),
                    new Email(customer.getEmail()));
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
