package ccsr.subscription.presentation.customer;

import ccsr.subscription.application.service.customer.CustomerService;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.presentation.view.customer.EmailRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

    CustomerService customerService;

    @PostMapping(value = "/create-customer", produces = "application/json")
    @ResponseBody
    Customer createCustomer(@RequestBody EmailRequest emailRequest) {
        Customer customer = customerService.createCustomer(emailRequest.toEmail());
        return customer;
    }

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
