package ccsr.subscription.presentation.subscription;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class SuccessController {

    @GetMapping("/subscription/success.html")
    String success() {
        return "/html/subscription/success";
    }
}
