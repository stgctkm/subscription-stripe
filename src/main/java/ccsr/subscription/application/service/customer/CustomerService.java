package ccsr.subscription.application.service.customer;

import ccsr.subscription.application.repository.customer.CustomerRepository;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.payment.PaymentMethodId;
import ccsr.subscription.domain.student.Student;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public Customer createCustomer(Email email) {
        Customer customer = customerRepository.createCustomer(email);
        return customer;
    }

    public Customer retrieveCustomer() {
        return customerRepository.retrieveCustomer();
    }

    public void update(Customer customer, PaymentMethodId paymentMethodId) {
        customerRepository.update(customer, paymentMethodId);
    }

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
