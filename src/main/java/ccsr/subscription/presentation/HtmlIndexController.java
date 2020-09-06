package ccsr.subscription.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlIndexController {
    @GetMapping("/customer")
    String customer() {
        return "html/customer";
    }

    @GetMapping("/subscription")
    String subscription() {
        return "html/subscription/index";
    }

    @GetMapping("/")
    String index() {
        return "html/index";
    }

}
