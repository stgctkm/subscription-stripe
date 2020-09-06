package ccsr.subscription;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements ApplicationRunner {

    @Value("${stripe.api.secret.key}")
    String stripeApikey;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stripe.apiKey = stripeApikey;
    }
}
