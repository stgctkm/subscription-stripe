package ccsr.subscription.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlIndexController {
    @GetMapping("/customer")
    String customer() {
        return "html/customer/index";
    }


    @GetMapping("/payment-method")
    String paymentMethod() {
        return "html/payment-method/paymentMethod";
    }


    @GetMapping("/subscription")
    String subscription() {
        return "html/subscription/index";
    }

    @GetMapping("/")
    String index() {
        return "html/index";
    }

    @GetMapping("/subscribe/result")
    String subscribeResult() {
        return "html/subscription/result";
    }
}
