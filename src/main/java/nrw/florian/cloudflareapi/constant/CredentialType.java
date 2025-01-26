package nrw.florian.cloudflareapi.constant;

/**
 * Type of cloudflare credential
 *
 * @author Florian J. Kleine-Vorholt
 */
public enum CredentialType {

    /**
     * Cloudflare configurable API-Token
     */
    TOKEN,

    /**
     * Cloudflare Global API-Token with Account-Mail
     */
    KEY_X_MAIL
}