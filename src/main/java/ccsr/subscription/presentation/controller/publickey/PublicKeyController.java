package ccsr.subscription.presentation.controller.publickey;

import ccsr.subscription.presentation.view.publickey.PublicKeyView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PublicKeyController {

    String publicKey;

    @GetMapping(value = "/public-key", produces = MediaType.APPLICATION_JSON_VALUE)
    PublicKeyView publicKey() {
        return new PublicKeyView(publicKey);
    }

    PublicKeyController(@Value("${publishableKey}") String publicKey) {
        this.publicKey = publicKey;
    }
}
