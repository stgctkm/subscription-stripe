package ccsr.subscription.presentation.subscription;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class WebHookController {

    String webHookSecret;
    Logger logger = LoggerFactory.getLogger(WebHookController.class);


    @PostMapping("/webhook")
    ResponseEntity<Void> webhook(@RequestHeader("Stripe-Signature") String stripeSignature,
                                 @RequestBody String payload) {


        logger.debug(payload);
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, stripeSignature, webHookSecret);
        } catch (SignatureVerificationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        switch (event.getType()) {
            case "checkout.session.completed":
                System.out.println("Payment succeeded!");
                logger.debug("Payment succeeded!");
                return new ResponseEntity<>(HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.OK);
        }


    }

    WebHookController(@Value("${stripe.webhook.sectet}") String webHookSecret) {
        this.webHookSecret = webHookSecret;
    }
}

