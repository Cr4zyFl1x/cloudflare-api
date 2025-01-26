package nrw.florian.cloudflareapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import nrw.florian.cloudflareapi.net.CloudflareAccessor;
import nrw.florian.cloudflareapi.obj.ResultInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This DTO represents the response got from the Cloudflare api as JSON.
 *
 * @author Florian J. Kleine-Vorholt
 */
@Getter
public final class CloudflareResponse<T> {

    /**
     * The JSON got as response from cloudflare
     */
    private final JsonObject response;

    /**
     * The object represented by the {@code result} json object
     */
    private final T data;

    /**
     * Indicates if cloudflare answered the request as successful
     */
    private final boolean successful;

    /**
     * HTTP-ResponseCode
     */
    private final int statusCode;

    /**
     * Messages with codes got from cloudflare api
     */
    private final Map<Integer, String> messages;

    /**
     * Errors with codes got from cloudflare api
     */
    private final Map<Integer, String> errors;

    /**
     * Additional result information
     */
    private final ResultInfo resultInfo;


    /**
     * Builds a new {@link CloudflareResponse}
     *
     * @param response      the response json got from api
     * @param data          the result json represented as pojo
     * @param statusCode    the http response code
     */
    public CloudflareResponse(JsonObject response, T data, int statusCode)
    {
        this.response = Objects.requireNonNull(response);
        this.data = data;
        this.statusCode = statusCode;

        // Success?
        if (response.has("success")) {
            successful = response.getAsJsonPrimitive("success").getAsBoolean();
        } else {
            successful = this.statusCode >= 200 && this.statusCode < 300 || this.statusCode == 304;
        }

        // Errors
        if (getResponse().has("errors")) {
            final JsonArray errorArray = getResponse().getAsJsonArray("errors");
            this.errors = new HashMap<>(errorArray.size());
            for (JsonElement e : errorArray) {
                JsonObject error = e.getAsJsonObject();
                errors.put(error.get("code").getAsInt(), error.get("message").getAsString());
            }
        } else if (getResponse().has("code") && getResponse().has("error")) {
            this.errors = new HashMap<>(1);
            this.errors.put(getResponse().getAsJsonPrimitive("code").getAsInt(),
                    getResponse().getAsJsonPrimitive("error").getAsString());
        } else {
            this.errors = null;
        }

        // Messages
        if (response.has("messages")) {
            final JsonArray messagesArray = getResponse().getAsJsonArray("messages");
            this.messages = new HashMap<>(messagesArray.size());
            for (JsonElement e : messagesArray) {
                JsonObject message = e.getAsJsonObject();
                this.messages.put(message.get("code").getAsInt(), message.get("message").getAsString());
            }
        } else {
            this.messages = null;
        }

        // Pagination information
        if (getResponse().has("result_info")) {
            resultInfo = CloudflareAccessor.getGson()
                    .fromJson(getResponse().getAsJsonObject("result_info"), ResultInfo.class);
        } else {
            resultInfo = null;
        }
    }
}