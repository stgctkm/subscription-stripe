package ccsr.subscription.presentation.view.subscription;

import ccsr.subscription.domain.subscription.SessionId;

public class SessionIdResponse {
    String sessionId;

    public SessionIdResponse(SessionId sessionId) {
        this.sessionId = sessionId.toString();
    }
}
