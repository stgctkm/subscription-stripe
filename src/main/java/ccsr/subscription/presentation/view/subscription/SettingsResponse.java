package ccsr.subscription.presentation.view.subscription;

public class SettingsResponse {
    String publishableKey;
    String trainingServicePriceId;
    String basicPrice;
    String proPrice;

    public SettingsResponse(String publishableKey, String trainingServicePriceId, String basicPrice, String proPrice) {
        this.publishableKey = publishableKey;
        this.trainingServicePriceId = trainingServicePriceId;
        this.basicPrice = basicPrice;
        this.proPrice = proPrice;
    }

}
