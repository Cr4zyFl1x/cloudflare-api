package nrw.florian.cloudflareapi.client.zone.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.AccessLevel;
import lombok.Getter;
import nrw.florian.cloudflareapi.CloudflareResponse;
import nrw.florian.cloudflareapi.client.zone.ZoneClient;
import nrw.florian.cloudflareapi.constant.ZoneType;
import nrw.florian.cloudflareapi.exception.UnsuccessfulApiRequestException;
import nrw.florian.cloudflareapi.json.JsonHelper;
import nrw.florian.cloudflareapi.net.CloudflareAccessor;
import nrw.florian.cloudflareapi.net.CloudflareRestClient;
import nrw.florian.cloudflareapi.net.ResponseFactory;
import nrw.florian.cloudflareapi.net.RestResponse;
import nrw.florian.cloudflareapi.obj.zone.Zone;

import java.util.List;
import java.util.Objects;

/**
 * The client implementation for managing cloudflare zones
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class ZoneClientImpl implements ZoneClient {

    /**
     * General zone management endpoint
     */
    private static final String ZONE_ENDPOINT = "/zones";

    /**
     * Endpoint to manage specific zone
     */
    private static final String ZONE_SPECIFIC_ENDPOINT = "/zones/{0}";


    /**
     * CF-API-Accessor
     */
    @Getter(AccessLevel.PRIVATE)
    private final CloudflareAccessor accessor;


    //////////////////////////////////////
    //////////////////////////////////////


    /**
     * Creates a new {@link ZoneClientImpl}
     *
     * @param accessor  the accessor object to communicate with API
     */
    public ZoneClientImpl(final CloudflareAccessor accessor)
    {
        this.accessor = Objects.requireNonNull(accessor);
    }


    //////////////////////////////////////
    //////////////////////////////////////


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Zone> getAll()
    {
        final CloudflareRestClient restClient   = getAccessor().getRestClient(ZONE_ENDPOINT);
        final RestResponse<String> restResponse = restClient.get();

        final CloudflareResponse<List<Zone>> response = ResponseFactory
                .getResponseList(
                        JsonHelper.toJsonElement(restResponse.getBody()),
                        restResponse.getStatusCode(),
                        Zone.class);

        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Zone findById(final String id) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(id);

        final CloudflareRestClient restClient   = getAccessor().getRestClient(ZONE_SPECIFIC_ENDPOINT, id);
        final RestResponse<String> restResponse = restClient.get();

        final CloudflareResponse<Zone> response = ResponseFactory
                .getResponse(
                        JsonHelper.toJsonElement(restResponse.getBody()),
                        restResponse.getStatusCode(),
                        Zone.class);

        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Zone findByName(final String name)
    {
        Objects.requireNonNull(name);

        final List<Zone> all = getAll();
        all.forEach(j -> j.setName(j.getName().toLowerCase()));

        final List<Zone> filtered = all.stream().filter(j -> {
            if (j == null) return false;
            if (j.getName() == null) return false;
            return j.getName().toLowerCase().trim().equals(name.toLowerCase().trim());
        }).toList();

        if (filtered.isEmpty()) {
            throw new IllegalArgumentException("No zone with name " + name + " found!");
        }
        if (filtered.size() > 1) {
            throw new IllegalStateException("Found more than one zone with name [" + name + "]");
        }
        return filtered.getFirst();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Zone create(final String name, final ZoneType type)
    {
        Objects.requireNonNull(name);

        final CloudflareRestClient restClient   = getAccessor().getRestClient(ZONE_ENDPOINT);

        final RestResponse<String> restResponse;
        final JsonObject body = new JsonObject();
        body.add("name", new JsonPrimitive(name));
        if (type != null) {
            body.add("type", new JsonPrimitive(type.toString()));
        }
        restResponse = restClient.post(body);

        final CloudflareResponse<Zone> zoneResp = ResponseFactory
                .getResponse(
                        JsonHelper.toJsonElement(restResponse.getBody()),
                        restResponse.getStatusCode(),
                        Zone.class);

        if (!zoneResp.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(zoneResp);
        }

        return zoneResp.getData();
    }


    /**
     * {@inheritDoc}
     */
    public void delete(final String id)
    {
        Objects.requireNonNull(id, "Zone id is mandatory!");

        final CloudflareRestClient restClient   = getAccessor().getRestClient(ZONE_SPECIFIC_ENDPOINT, id);
        final RestResponse<String> restResponse = restClient.delete();
        final CloudflareResponse<Void> response = ResponseFactory.getResponse(
                        JsonHelper.toJsonElement(restResponse.getBody()),
                        restResponse.getStatusCode(),
                        Void.class);

        if (!restResponse.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }
    }
}