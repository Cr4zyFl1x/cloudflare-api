package nrw.florian.cloudflareapi.net.impl;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import nrw.florian.cloudflareapi.exception.RestClientException;
import nrw.florian.cloudflareapi.net.ApiUrlFormatter;
import nrw.florian.cloudflareapi.net.CloudflareRestClient;
import nrw.florian.cloudflareapi.net.RestResponse;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

/**
 * @author Florian J. Kleine-Vorholt
 */
public final class CloudflareRestClientImpl implements CloudflareRestClient {

    /**
     * Preconfigured {@code HttpClient} with authentication and content-type header
     */
    @Getter(AccessLevel.PRIVATE)
    private final HttpClient httpClient;

    /**
     * HTTP(S) endpoint url to make the requests to
     */
    @Getter
    private final URI apiUrl;



    /**
     * Creates a new {@link CloudflareRestClientImpl}
     */
    public CloudflareRestClientImpl(final HttpClient httpClient, final String apiUrl)
    {
        this.httpClient = Objects.requireNonNull(httpClient);
        this.apiUrl = URI.create(Objects.requireNonNull(apiUrl));
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public RestResponse<String> post(final JsonObject requestBody) throws RestClientException
    {
        final HttpPost request = new HttpPost(apiUrl);

        if (Objects.nonNull(requestBody)) {
            request.setEntity(new StringEntity(requestBody.toString()));
        }

        try {
            return getResponse(request);
        } catch (IOException e) {
            throw new RestClientException("Unable to complete POST request!", e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public RestResponse<String> get(final JsonObject requestBody) throws RestClientException
    {

        final HttpGet request = new HttpGet(apiUrl);

        if (Objects.nonNull(requestBody)) {
            request.setUri(URI.create(apiUrl + ApiUrlFormatter.getQueryString(requestBody)));
        }


        try {
            return getResponse(request);
        } catch (IOException e) {
            throw new RestClientException("Unable to complete GET request!", e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public RestResponse<String> delete(final JsonObject requestBody) throws RestClientException
    {
        final HttpDelete request = new HttpDelete(apiUrl);

        if (Objects.nonNull(requestBody)) {
            request.setEntity(new StringEntity(requestBody.toString()));
        }

        try {
            return getResponse(request);
        } catch (IOException e) {
            throw new RestClientException("Unable to complete DELETE request!", e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public RestResponse<String> patch(final JsonObject requestBody) throws RestClientException
    {
        final HttpPatch request = new HttpPatch(apiUrl);

        if (Objects.nonNull(requestBody)) {
            request.setEntity(new StringEntity(requestBody.toString()));
        }

        try {
            return getResponse(request);
        } catch (IOException e) {
            throw new RestClientException("Unable to complete PATCH request!", e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public RestResponse<String> put(final JsonObject requestBody) throws RestClientException
    {
        final HttpPut request = new HttpPut(apiUrl);

        if (Objects.nonNull(requestBody)) {
            request.setEntity(new StringEntity(requestBody.toString()));
        }

        try {
            return getResponse(request);
        } catch (IOException e) {
            throw new RestClientException("Unable to complete PUT request!", e);
        }
    }


    ////////////////////////
    ////////////////////////


    private RestResponse<String> getResponse(final ClassicHttpRequest request) throws IOException
    {
        return getHttpClient().execute(request, response -> {
            final int status = response.getCode();
            final String body = EntityUtils.toString(response.getEntity());
            return new RestResponse<>(body, status);
        });
    }
}