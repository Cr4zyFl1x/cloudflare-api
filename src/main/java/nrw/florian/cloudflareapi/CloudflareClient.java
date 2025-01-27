package nrw.florian.cloudflareapi;

import nrw.florian.cloudflareapi.client.zone.RecordsClient;
import nrw.florian.cloudflareapi.client.zone.ZoneClient;
import nrw.florian.cloudflareapi.client.zone.impl.RecordsClientImpl;
import nrw.florian.cloudflareapi.client.zone.impl.ZoneClientImpl;
import nrw.florian.cloudflareapi.credential.CloudflareCredentials;
import nrw.florian.cloudflareapi.net.CloudflareAccessor;
import nrw.florian.cloudflareapi.obj.zone.Zone;

import java.util.Objects;

/**
 * The {@link CloudflareClient} can be used to communicate via OOP with the cloudflare api.
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class CloudflareClient {

    /**
     * The CF-API-Accessor
     */
    private final CloudflareAccessor accessor;


    /////////////////////////////////
    /////////////////////////////////


    /**
     * Creates a new {@link CloudflareClient}
     *
     * @param accessor  the {@code CloudflareAccessor} used as DTO for ApiUrl & RestClient
     */
    CloudflareClient(final CloudflareAccessor accessor)
    {
        this.accessor = Objects.requireNonNull(accessor);
    }


    /////////////////////////////////
    /////////////////////////////////


    /**
     * Gets the {@code ZoneClient} for managing cloudflare zones
     *
     * @return  the zone client
     */
    public ZoneClient zone()
    {
        return new ZoneClientImpl(accessor);
    }


    /**
     * Gets the {@code RecordsClient} for a specific {@code Zone}
     *
     * @param zone  the zone to get the RecordsClient for.
     *
     * @return      the respective Records client for the zone
     */
    public RecordsClient records(final Zone zone)
    {
        Objects.requireNonNull(zone);
        return new RecordsClientImpl(accessor, zone);
    }


    /////////////////////////////////
    /////////////////////////////////


    /**
     * Creates a new {@link CloudflareClient}
     *
     * @param credentials   the credentials used to authenticate against the cloudflare api
     *
     * @return  the instance of the cloudflare client
     */
    public static CloudflareClient of(final CloudflareCredentials credentials)
    {
        Objects.requireNonNull(credentials);
        return new CloudflareClientBuilder()
                .setCredentials(credentials)
                .build();
    }
}