package nrw.florian.cloudflareapi.credential;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
public final class CloudflareCredentials {

    /**
     *
     */
    private final String xAuthKey;

    /**
     *
     */
    private final String xAuthMail;

    /**
     *
     */
    private final String xAuthToken;



    public CloudflareCredentials(final String xAuthKey, final String xAuthMail) {
        this.xAuthKey = Objects.requireNonNull(xAuthKey);
        this.xAuthMail = Objects.requireNonNull(xAuthMail);
        this.xAuthToken = null;
    }

    public CloudflareCredentials(final String xAuthToken) {
        this.xAuthToken = Objects.requireNonNull(xAuthToken);
        this.xAuthMail = null;
        this.xAuthKey = null;
    }
}