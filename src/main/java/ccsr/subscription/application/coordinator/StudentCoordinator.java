package ccsr.subscription.application.coordinator;

import ccsr.subscription.application.service.customer.CustomerService;
import ccsr.subscription.application.service.student.StudentService;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.customer.Email;
import ccsr.subscription.domain.student.Student;

public class StudentCoordinator {

    CustomerService customerService;
    StudentService studentService;

    void register(Email email) {
        Customer customer = customerService.createCustomer(email);
        studentService.register(new Student(customer));
    }

    StudentCoordinator(CustomerService customerService, StudentService studentService) {
        this.customerService = customerService;
        this.studentService = studentService;
    }
}
