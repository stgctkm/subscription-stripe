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
        //Stripeが用意した決済ページへのリダイレクトでの課金
        return "html/subscription/one-time/index";
    }

    @GetMapping("/")
    String index() {
        return "html/index";
    }

}
