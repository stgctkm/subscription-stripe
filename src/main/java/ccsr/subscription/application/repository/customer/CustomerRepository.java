package ccsr.subscription.application.repository.customer;

import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.student.Student;

public interface CustomerRepository {
    Customer createCustomer(Email email);

    Customer retrieveCustomer(Student student);
}
