package nrw.florian.cloudflareapi.net;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nrw.florian.cloudflareapi.CloudflareResponse;
import nrw.florian.cloudflareapi.exception.InvalidApiResponseException;
import nrw.florian.cloudflareapi.json.JsonHelper;

import java.util.List;

/**
 * ResponseFactory for creating {@link CloudflareResponse} objects
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class ResponseFactory {

    private ResponseFactory()
    {
        throw new AssertionError("No instance for you!");
    }


    /**
     * Factorizes a {@link CloudflareResponse} with List of elements as data
     * <p>
     *     This method uses the {@code result} key info to build the pojo
     * </p>
     *
     * @param response          the json response got from cloudflare api
     * @param statusCode        the http status code
     * @param clazz             the class to map the result to
     *
     * @return                  the CloudflareResponse object containing all response objects in a List
     *
     * @param <T>               the pojo class
     *
     * @throws IllegalArgumentException     if the provided json response cannot be mapped to list of pojos
     * @throws InvalidApiResponseException  if the api response is unexpectedly different
     */
    public static <T> CloudflareResponse<List<T>> getResponseList(final JsonElement response,
                                                       final int statusCode,
                                                       final Class<T> clazz)
            throws IllegalArgumentException, InvalidApiResponseException
    {
        return getResponseList(response, null, statusCode, clazz);
    }


    /**
     * Factorizes a {@link CloudflareResponse} with List of elements as data
     *
     * @param response          the json response got from cloudflare api
     * @param subResult         the object under the {@code result} key that is used to create the pojo
     * @param statusCode        the http status code
     * @param clazz             the class to map the result to
     *
     * @return                  the CloudflareResponse object containing all response objects in a List
     *
     * @param <T>               the pojo class
     *
     * @throws IllegalArgumentException     if the provided json response cannot be mapped to list of pojos
     * @throws InvalidApiResponseException  if the api response is unexpectedly different
     */
    public static <T> CloudflareResponse<List<T>> getResponseList(final JsonElement response,
                                                                  final String subResult,
                                                                  final int statusCode,
                                                                  final Class<T> clazz)
        throws IllegalArgumentException, InvalidApiResponseException
    {
        // Check if is not array
        if (!response.isJsonObject()) {
            throw new InvalidApiResponseException("Response is not a JSON object");
        }

        JsonElement result = response.getAsJsonObject().get("result");
        if (result == null) {
            throw new InvalidApiResponseException("Result is empty!");
        }

        if (subResult != null) {
            result = result.getAsJsonObject().get(subResult);
            if (result == null || result.isJsonNull()) {
                throw new InvalidApiResponseException("SubResult " + subResult + " is empty!");
            }
        }

        if (result.isJsonArray()) {
            return new CloudflareResponse<>(
                    (JsonObject) response,
                    JsonHelper.toListOfObjects((JsonArray) result, clazz),
                    statusCode);
        }

        throw new IllegalArgumentException("Result is not a JSON array that can be mapped to list!");
    }


    /**
     * Factorizes the {@link CloudflareResponse} with a specific pojo holding the response data
     *
     * @param response                      the json response got from cloudflare api
     * @param statusCode                    the http status code
     * @param clazz                         the class to map the result to
     *
     * @return                              the CloudflareResponse object containing the response object pojo
     *
     * @param <T>                           the pojo class
     *
     * @throws IllegalArgumentException     if the provided json response cannot be mapped to a pojo
     * @throws InvalidApiResponseException  if the api response is unexpectedly different
     */
    public static <T> CloudflareResponse<T> getResponse(final JsonElement response,
                                                        final int statusCode,
                                                        final Class<T> clazz)
        throws IllegalArgumentException, InvalidApiResponseException
    {
        if (clazz == Void.class) {
            return new CloudflareResponse<>(
                    (JsonObject) response,
                    null,
                    statusCode
            );
        }

        if (!response.isJsonObject()) {
            throw new InvalidApiResponseException("Response is not a JSON object");
        }

        final JsonElement result = response.getAsJsonObject().get("result");

        // If is null
        if (result == null || result.isJsonNull()) {
            return new CloudflareResponse<>(
                    (JsonObject) response,
                    null,
                    statusCode
            );
        }

        // Check if JSON-Object is available with pojo info
        if (result.isJsonObject()) {
            return new CloudflareResponse<>(
                    (JsonObject) response,
                    JsonHelper.toObject((JsonObject) result, clazz),
                    statusCode
            );
        }

        throw new IllegalArgumentException("Result is not a JSON Object that can be mapped to Java-Object!");
    }
}