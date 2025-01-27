package nrw.florian.cloudflareapi.client.zone.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.AccessLevel;
import lombok.Getter;
import nrw.florian.cloudflareapi.CloudflareResponse;
import nrw.florian.cloudflareapi.client.zone.RecordsClient;
import nrw.florian.cloudflareapi.constant.Match;
import nrw.florian.cloudflareapi.constant.RecordType;
import nrw.florian.cloudflareapi.exception.UnsuccessfulApiRequestException;
import nrw.florian.cloudflareapi.json.JsonHelper;
import nrw.florian.cloudflareapi.net.CloudflareAccessor;
import nrw.florian.cloudflareapi.net.CloudflareRestClient;
import nrw.florian.cloudflareapi.net.ResponseFactory;
import nrw.florian.cloudflareapi.net.RestResponse;
import nrw.florian.cloudflareapi.obj.dns.DNSRecord;
import nrw.florian.cloudflareapi.obj.zone.Zone;

import java.util.List;
import java.util.Objects;

/**
 * The client implementation for managing dns records
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class RecordsClientImpl implements RecordsClient {

    /**
     * General dns record management endpoint
     */
    private static final String ZONE_RECORDS_ENDPOINT = "/zones/{0}/dns_records";

    /**
     * Endpoint for batch operations on dns records
     */
    private static final String ZONE_RECORDS_BATCH_ENDPOINT = "zones/{0}/dns_records/batch";

    /**
     * Endpoint to manage specific record
     */
    private static final String ZONE_SPECIFIC_RECORDS_ENDPOINT = "/zones/{0}/dns_records/{1}";


    /**
     * CF-API-Accessor
     */
    @Getter(AccessLevel.PRIVATE)
    private final CloudflareAccessor accessor;

    /**
     * The zone to manage the records for
     */
    @Getter(AccessLevel.PRIVATE)
    private final Zone zone;

    /**
     * The RestClient for managing the zones records
     */
    @Getter(AccessLevel.PRIVATE)
    private final CloudflareRestClient zoneRecordsRestClient;


    //////////////////////////////////////
    //////////////////////////////////////


    /**
     * Creates a new {@link RecordsClientImpl}
     *
     * @param accessor  the accessor object to communicate with API
     * @param zone      the zone to manage
     */
    public RecordsClientImpl(final CloudflareAccessor accessor, final Zone zone)
    {
        this.accessor = Objects.requireNonNull(accessor);
        this.zone = Objects.requireNonNull(zone);

        Objects.requireNonNull(zone.getId(), "Provided zone does not contain identifier!");

        // General RestClient for managing records in the bound zone
        this.zoneRecordsRestClient = getAccessor().getRestClient(ZONE_RECORDS_ENDPOINT, zone.getId());
    }


    //////////////////////////////////////
    //////////////////////////////////////


    /**
     * {@inheritDoc}
     */
    public List<DNSRecord> getAll() throws UnsuccessfulApiRequestException
    {
        // Send request
        final CloudflareResponse<List<DNSRecord>> respList = buildCFResponseList(
                getZoneRecordsRestClient().get(prepareFilter()));

        // Successful?
        if (!respList.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(respList);
        }

        return respList.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> findByType(final RecordType recordType) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(recordType, "Provided record type is null!");

        // Filter
        final JsonObject reqParams = prepareFilter();
        reqParams.add("type", new JsonPrimitive(recordType.toString()));

        // Send request
        final CloudflareResponse<List<DNSRecord>> response = buildCFResponseList(
                getZoneRecordsRestClient().get(reqParams));

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> findByName(final String name) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(name, "Provided name is null!");

        // Filter
        final JsonObject jsonObject = prepareFilter();
        jsonObject.add("name.exact", new JsonPrimitive(name.trim()));

        // Send request
        final CloudflareResponse<List<DNSRecord>> respList = buildCFResponseList(
                getZoneRecordsRestClient().get(jsonObject));

        // Is success?
        if (!respList.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(respList);
        }

        // Return Data
        return respList.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> findByNameIsLike(final String name) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(name, "Provided name is null!");

        // Filter
        final JsonObject reqParams = prepareFilter();
        reqParams.add("name.contains", new JsonPrimitive(name.trim()));

        // Send request
        final CloudflareResponse<List<DNSRecord>> respList = buildCFResponseList(
                getZoneRecordsRestClient().get(reqParams));

        // Is successful?
        if (!respList.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(respList);
        }

        return respList.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> findByNameAndType(String name, RecordType type) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(name, "Provided name is null!");
        Objects.requireNonNull(type, "Provided type is null!");

        // Filter
        final JsonObject reqParams = prepareFilter();
        reqParams.add("type", new JsonPrimitive(type.toString()));
        reqParams.add("name.exact", new JsonPrimitive(name.trim()));
        reqParams.add("match", new JsonPrimitive(Match.ALL.asLower()));

        // Send request
        final CloudflareResponse<List<DNSRecord>> response = buildCFResponseList(
                getZoneRecordsRestClient().get(reqParams));

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> findByNameIsLikeAndType(String name, RecordType type) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(name, "Provided name is null!");
        Objects.requireNonNull(type, "Provided type is null!");

        // Filter
        final JsonObject reqParams = prepareFilter();
        reqParams.add("type", new JsonPrimitive(type.toString()));
        reqParams.add("name.contains", new JsonPrimitive(name.trim()));
        reqParams.add("match", new JsonPrimitive(Match.ALL.asLower()));

        // Send request
        final CloudflareResponse<List<DNSRecord>> response = buildCFResponseList(
                getZoneRecordsRestClient().get(reqParams));

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> findByValueIsLike(String value) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(value, "Provided value is null!");

        // Filter
        final JsonObject reqParams = prepareFilter();
        reqParams.add("content.contains", new JsonPrimitive(value.trim()));

        // Send request
        final CloudflareResponse<List<DNSRecord>> response = buildCFResponseList(
                getZoneRecordsRestClient().get(reqParams));

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DNSRecord findById(String id) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(id, "Provided id is null!");

        // Create rest client for this record id
        final CloudflareRestClient restClient = getAccessor().getRestClient(ZONE_SPECIFIC_RECORDS_ENDPOINT,
                zone.getId(), id);

        // Send request
        final CloudflareResponse<DNSRecord> response = buildCFResponse(
                restClient.get(),
                DNSRecord.class
        );

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    public void delete(final String recordIdentifier) throws UnsuccessfulApiRequestException
    {
        // Get restclient for record
        final CloudflareRestClient restClient   = getAccessor().getRestClient(ZONE_SPECIFIC_RECORDS_ENDPOINT,
                zone.getId(), recordIdentifier);

        // Send request
        final CloudflareResponse<Void> response = buildCFResponse(restClient.delete(), Void.class);

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final List<DNSRecord> records) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(records, "Provided records is null!");
        final List<String> recordsToDelete = records.stream().map(DNSRecord::getId).
                filter(Objects::nonNull).toList();

        // Request parameter
        final JsonArray delIDs = new JsonArray();
        for (final String id : recordsToDelete) {
            final JsonObject idObj = new JsonObject();
            idObj.add("id", new JsonPrimitive(id));
            delIDs.add(idObj);
        }
        final JsonObject reqParams = new JsonObject();
        reqParams.add("deletes", delIDs);

        // Get REST-Client
        final CloudflareRestClient restClient = getAccessor().getRestClient(ZONE_RECORDS_BATCH_ENDPOINT,
                getZone().getId());

        // Send request
        final CloudflareResponse<Void> response = buildCFResponse(
                restClient.post(reqParams),
                Void.class
        );

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DNSRecord> create(final List<DNSRecord> records) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(records, "Provided records is null!");
        final List<DNSRecord> recordsList = records.stream().filter(j -> j.getId() == null).toList();

        // Parameters
        final JsonArray recordsArray = new JsonArray();
        for (final DNSRecord record : recordsList) {
            final JsonObject recordObj = CloudflareAccessor.getGson().toJsonTree(record).getAsJsonObject();
            recordsArray.add(recordObj);
        }
        final JsonObject reqParams = new JsonObject();
        reqParams.add("posts", recordsArray);

        // Get REST-Client
        final CloudflareRestClient restClient = getAccessor().getRestClient(ZONE_RECORDS_BATCH_ENDPOINT,
                getZone().getId());

        // Send request
        final CloudflareResponse<List<DNSRecord>> response = buildCFResponseList(
                restClient.post(reqParams),
                "posts");

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DNSRecord saveOrUpdate(final DNSRecord record) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(record, "Provided record is null!");

        // Creation payload
        final JsonObject reqParams = CloudflareAccessor.getGson().toJsonTree(record).getAsJsonObject();

        // Response
        final CloudflareResponse<DNSRecord> response;

        if (Objects.isNull(record.getId())) {
            // Send request
            response = buildCFResponse(
                    getZoneRecordsRestClient().post(reqParams),
                    DNSRecord.class
            );
        } else {
            final CloudflareRestClient restClient = getAccessor().getRestClient(ZONE_SPECIFIC_RECORDS_ENDPOINT,
                    zone.getId(), record.getId());

            response = buildCFResponse(
                    restClient.patch(reqParams),
                    DNSRecord.class
            );
        }

        // Is successful?
        if (!response.isSuccessful()) {
            throw new UnsuccessfulApiRequestException(response);
        }

        return response.getData();
    }


    //////////////////////////////////////
    //////////////////////////////////////


    private JsonObject prepareFilter()
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add("per_page", new JsonPrimitive(5_000_000));

        return jsonObject;
    }


    private <T> CloudflareResponse<T> buildCFResponse(final RestResponse<String> resp, Class<T> clazz)
    {
        Objects.requireNonNull(resp, "Provided response is null!");
        Objects.requireNonNull(clazz, "Provided clazz is null!");

        return ResponseFactory.getResponse(
                JsonHelper.toJsonElement(resp.getBody()),
                resp.getStatusCode(),
                clazz);
    }


    private CloudflareResponse<List<DNSRecord>> buildCFResponseList(final RestResponse<String> resp)
    {
        return buildCFResponseList(resp, null);
    }


    private CloudflareResponse<List<DNSRecord>> buildCFResponseList(final RestResponse<String> resp,
                                                                    final String subResult)
    {
        Objects.requireNonNull(resp, "Provided response is null!");
        return ResponseFactory.getResponseList(
                JsonHelper.toJsonElement(resp.getBody()),
                subResult,
                resp.getStatusCode(),
                DNSRecord.class
        );
    }
}