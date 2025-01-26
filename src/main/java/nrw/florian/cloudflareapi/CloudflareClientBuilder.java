package nrw.florian.cloudflareapi;

import nrw.florian.cloudflareapi.credential.CloudflareCredentials;
import nrw.florian.cloudflareapi.net.CloudflareAccessor;

/**
 * Builder for building the {@link CloudflareClient}
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class CloudflareClientBuilder {

    /**
     * CF-API credentials
     */
    private CloudflareCredentials credentials;


    /**
     * CF-API base url
     */
    private String apiUrl = null;



    /**
     * Creates a new {@link CloudflareClientBuilder}
     */
    public CloudflareClientBuilder()
    {}



    /**
     * Tries to build the {@link CloudflareClient}
     *
     * @return  a built instance of the {@code CloudflareClient}
     *
     * @throws IllegalStateException    if not all needed values are configured or a value is invalid.
     */
    public CloudflareClient build() throws IllegalStateException
    {
        // Check if builder state is valid
        this.checkState();

        // Build accessor
        final CloudflareAccessor accessor = new CloudflareAccessor(credentials, apiUrl);

        // Return
        return new CloudflareClient(accessor);
    }


    /**
     * Sets the credentials used to authenticate against the cloudflare api.
     *
     * @param credentials   the {@link CloudflareCredentials} object
     *
     * @return              the builder instance
     */
    public CloudflareClientBuilder setCredentials(final CloudflareCredentials credentials)
    {
        this.credentials = credentials;
        return this;
    }


    /**
     * Configures an alternative API-Url for accessing the Cloudflare-API
     *
     * @param apiUrl    the api url (e.g. {@code https://api-staging.cloduflare.com/v4})
     *
     * @return          the builder instance
     */
    public CloudflareClientBuilder overrideApiUrl(final String apiUrl)
    {
        this.apiUrl = apiUrl;
        return this;
    }


    ////////////////////////////////
    ////////////////////////////////


    /**
     * Checks if the builder is in a valid state
     *
     * @throws IllegalStateException    if builder is in an invalid state
     */
    private void checkState() throws IllegalStateException
    {
        if (credentials == null) {
            throw new IllegalStateException("No credentials provided");
        }
    }
}