package nrw.florian.cloudflareapi.net;

import com.google.gson.JsonObject;
import nrw.florian.cloudflareapi.exception.RestClientException;

/**
 * Interface for REST operations to the CF-API
 *
 * @author Florian J. Kleine-Vorholt
 */
public interface CloudflareRestClient {

    /**
     * Sends a {@code POST} request without a request body to the http(s)-endpoint
     *
     * @return  the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    default RestResponse<String> post() throws RestClientException
    {
        return post(null);
    }


    /**
     * Sends a {@code POST} request with an additional JSON request body to the http(s)-endpoint
     *
     * @param requestBody           the additional request body as JSON
     * @return                      the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    RestResponse<String> post(final JsonObject requestBody) throws RestClientException;


    /**
     * Sends a {@code GET} request without a request body to the http(s)-endpoint
     *
     * @return  the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    default RestResponse<String> get() throws RestClientException
    {
        return get(null);
    }


    /**
     * Sends a {@code GET} request with an additional JSON request body to the http(s)-endpoint
     *
     * @param requestBody           the additional request body as JSON
     * @return                      the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    RestResponse<String> get(final JsonObject requestBody) throws RestClientException;


    /**
     * Sends a {@code DELETE} request without a request body to the http(s)-endpoint
     *
     * @return  the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    default RestResponse<String> delete() throws RestClientException
    {
        return delete(null);
    }


    /**
     * Sends a {@code DELETE} request with an additional JSON request body to the http(s)-endpoint
     *
     * @param requestBody           the additional request body as JSON
     * @return                      the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    RestResponse<String> delete(final JsonObject requestBody) throws RestClientException;


    /**
     * Sends a {@code PATCH} request without a request body to the http(s)-endpoint
     *
     * @return  the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    default RestResponse<String> patch() throws RestClientException
    {
        return patch(null);
    }


    /**
     * Sends a {@code PATCH} request with an additional JSON request body to the http(s)-endpoint
     *
     * @param requestBody           the additional request body as JSON
     * @return                      the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    RestResponse<String> patch(final JsonObject requestBody) throws RestClientException;


    /**
     * Sends a {@code PUT} request without a request body to the http(s)-endpoint
     *
     * @return  the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    default RestResponse<String> put() throws RestClientException
    {
        return put(null);
    }


    /**
     * Sends a {@code PUT} request with an additional JSON request body to the http(s)-endpoint
     *
     * @param requestBody           the additional request body as JSON
     * @return                      the {@link RestResponse} containing the response body and additional information
     *
     * @throws RestClientException  if there was a communication error
     */
    RestResponse<String> put(final JsonObject requestBody) throws RestClientException;
}