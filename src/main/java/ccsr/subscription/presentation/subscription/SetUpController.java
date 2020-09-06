package ccsr.subscription.presentation.subscription;

import ccsr.subscription.presentation.view.subscription.SettingsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SetUpController {

    String publishableKey;
    String basicPriceId;
    String proPriceId;
    String trainingServicePriceId;


    @GetMapping(value = "setup", produces = MediaType.APPLICATION_JSON_VALUE)
    SettingsResponse getSetting() {
        return new SettingsResponse(publishableKey, trainingServicePriceId, basicPriceId, proPriceId);
    }

    SetUpController(@Value("${publishableKey}") String publishableKey,
                    @Value("${training.service.price.id}") String trainingServicePriceId,
                    @Value("${basic.price.id}") String basicPriceId,
                    @Value("${pro.price.id}") String proPriceId) {
        this.publishableKey = publishableKey;
        this.trainingServicePriceId = trainingServicePriceId;
        this.basicPriceId = basicPriceId;
        this.proPriceId = proPriceId;
    }

}