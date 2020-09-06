package ccsr.subscription.presentation.subscription;

import ccsr.subscription.application.service.subscription.SubscriptionService;
import ccsr.subscription.domain.subscription.SessionId;
import ccsr.subscription.presentation.view.subscription.PriceIdRequest;
import ccsr.subscription.presentation.view.subscription.SessionIdResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class CreateCheckoutSessionController {

    SubscriptionService subscriptionService;

    @PostMapping(value = "/create-checkout-session", produces = MediaType.APPLICATION_JSON_VALUE)
    SessionIdResponse createCheckoutSession(@RequestBody PriceIdRequest priceIdRequest) {
        SessionId sessionId = subscriptionService.creatCheckoutSession(priceIdRequest.toSubscriptionPriceId());
        return new SessionIdResponse(sessionId);
    }


    CreateCheckoutSessionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
}
