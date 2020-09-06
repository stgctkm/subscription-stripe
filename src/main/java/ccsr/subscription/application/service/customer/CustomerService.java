package ccsr.subscription.application.service.customer;

import ccsr.subscription.application.repository.customer.CustomerRepository;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.Email;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public Customer createCustomer(Email email) {
        Customer customer = customerRepository.createCustomer(email);
        return customer;
    }

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
