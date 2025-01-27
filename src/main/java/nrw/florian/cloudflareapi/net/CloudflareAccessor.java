package nrw.florian.cloudflareapi.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import nrw.florian.cloudflareapi.constant.CredentialType;
import nrw.florian.cloudflareapi.credential.CloudflareCredentials;
import nrw.florian.cloudflareapi.net.impl.CloudflareRestClientImpl;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cloudflare accessor used for handling rest clients with credentials
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class CloudflareAccessor {

    /**
     * The default Cloudflare v4 API Url
     */
    private final static String API_URL= "https://api.cloudflare.com/client/v4";

    /**
     * The GSON instance for pojo JSON mapping
     */
    @Getter
    private static final Gson gson;
    static {
        gson = new GsonBuilder()
                .create();
    }

    /**
     * The credentials used for authentication
     */
    private final CloudflareCredentials credentials;

    /**
     * The rest client template with auth headers
     */
    private final HttpClient restClient;

    /**
     * The used API Url
     */
    @Getter
    private final String apiUrl;


    //////////////////////////////////////
    //////////////////////////////////////


    /**
     * Creates a new {@link CloudflareAccessor}
     */
    public CloudflareAccessor(final CloudflareCredentials credentials, String apiUrl)
    {
        this.credentials = Objects.requireNonNull(credentials);
        this.restClient = buildRestClient();
        this.apiUrl = apiUrl == null ? API_URL : apiUrl;
    }


    /**
     * Creates a new {@link CloudflareAccessor}
     */
    public CloudflareAccessor(final CloudflareCredentials credentials)
    {
        this(credentials, null);
    }


    //////////////////////////////////////
    //////////////////////////////////////


    /**
     * Gets the RestClient for a specific API-Component
     *
     * @param subApiUrl     the api component path (e.g. /zones)
     *
     * @return              RestClient for the specific API-Component
     */
    public CloudflareRestClient getRestClient(final String subApiUrl)
    {
        return getRestClient(subApiUrl, new String[]{});
    }


    /**
     *
     * Gets the RestClient for a specific API-Component and replaces identifier placeholders
     *
     * @param subApiUrl     the api component path (e.g. /zones)
     * @param identifiers   the identifiers used for replacing
     *
     * @return              RestClient for the specific API-Component
     */
    public CloudflareRestClient getRestClient(final String subApiUrl, String... identifiers)
    {
        return new CloudflareRestClientImpl(this.restClient,
                ApiUrlFormatter.format(API_URL, subApiUrl, identifiers));
    }


    //////////////////////////////////////
    //////////////////////////////////////


    private HttpClient buildRestClient()
    {
        final List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        if (credentials.getType().equals(CredentialType.TOKEN)) {
            headers.add(new BasicHeader("Authorization", "Bearer " + credentials.getXAuthToken()));
        } else {
            headers.add(new BasicHeader("X-Auth-Key", credentials.getXAuthKey()));
            headers.add(new BasicHeader("X-Auth-Email", credentials.getXAuthMail()));
        }

        return HttpClients.custom()
                .setDefaultHeaders(headers)
                .build();
    }
}