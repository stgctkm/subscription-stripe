package ccsr.subscription.presentation.controller.subscription.session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class CancelController {

    @GetMapping("/subscription/canceled.html")
    String success() {
        return "/html/subscription/one-time/canceled";
    }
}
