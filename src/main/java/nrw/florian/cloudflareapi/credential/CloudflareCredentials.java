package nrw.florian.cloudflareapi.credential;

import lombok.Getter;
import nrw.florian.cloudflareapi.constant.CredentialType;

import java.util.Objects;

/**
 * Class representing credential used for authentication against the CF-API
 *
 * @author Florian J. Kleine-Vorholt
 */
@Getter
public final class CloudflareCredentials {

    /**
     * Global API-Key
     */
    private final String xAuthKey;

    /**
     * Cloudflare account mail address
     */
    private final String xAuthMail;

    /**
     * Cloudflare configurable auth token
     */
    private final String xAuthToken;

    /**
     * Type of credential
     */
    private final CredentialType type;



    /**
     * Creates {@link CloudflareCredentials} by using the global API-Key
     *
     * @param xAuthKey      the global API-Key
     * @param xAuthMail     the account mail address
     */
    public CloudflareCredentials(final String xAuthKey, final String xAuthMail)
    {
        this.xAuthKey = Objects.requireNonNull(xAuthKey);
        this.xAuthMail = Objects.requireNonNull(xAuthMail);
        this.xAuthToken = null;
        this.type = CredentialType.KEY_X_MAIL;
    }


    /**
     * Creates {@link CloudflareCredentials} by using a configured API-Key
     *
     * @param xAuthToken    the created/configured api key
     */
    public CloudflareCredentials(final String xAuthToken)
    {
        this.xAuthToken = Objects.requireNonNull(xAuthToken);
        this.xAuthMail = null;
        this.xAuthKey = null;
        this.type = CredentialType.TOKEN;
    }
}