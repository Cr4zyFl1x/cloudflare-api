package nrw.florian.cloudflareapi.net;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Florian J. Kleine-Vorholt
 */
public final class ApiUrlFormatter {

    private ApiUrlFormatter() {
        throw new AssertionError("No instance for you!");
    }


    /**
     * Builds the API-URL
     *
     * @param baseApiUrl        the cloudflare base url
     * @param subApiUrl         the component url
     *
     * @return                  the fully qualified api url
     */
    public static String format(String baseApiUrl, String subApiUrl)
    {
        Objects.requireNonNull(baseApiUrl, "baseApiUrl must not be null!");
        Objects.requireNonNull(subApiUrl, "subApiUrl must not be null!");

        if (!baseApiUrl.endsWith("/")) {
            baseApiUrl = baseApiUrl + "/";
        }

        if (subApiUrl.endsWith("/")) {
            subApiUrl = subApiUrl.substring(0, subApiUrl.length()-1);
        }
        if (subApiUrl.startsWith("/")) {
            subApiUrl = subApiUrl.substring(1);
        }

        return baseApiUrl + subApiUrl;
    }


    /**
     * Builds the API-URL and replaces placeholders like {@code {0}} with specific identifiers
     *
     * @param baseApiUrl        the cloudflare base url
     * @param subApiUrl         the component url
     * @param identifiers       the identifiers used for replacing
     *
     * @return                  the fully qualified api url
     */
    public static String format(final String baseApiUrl, final String subApiUrl, final String... identifiers)
    {
        final String formatted = format(baseApiUrl, subApiUrl);

        Objects.requireNonNull(identifiers, "identifiers must not be null!");

        if (Arrays.stream(identifiers).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("identifiers must not contain null value!");
        }

        String ret = formatted;
        for (int i = 0; i < identifiers.length; i++) {
            ret = ret.replace("{" + i + "}", identifiers[i]);
        }

        return ret;
    }


    /**
     * Builds an HTTP-Query string from JSON-Object for GET-Requests
     *
     * @param jsonObject    the JSON-Object containing the query parameter
     *
     * @return              the query string
     */
    public static String getQueryString(final JsonObject jsonObject)
    {
        Objects.requireNonNull(jsonObject, "jsonObject containing the parameters must not be null!");

        final StringBuilder query = new StringBuilder("?");

        for (final String key : jsonObject.keySet()) {
            query.append(key).append("=").append(jsonObject.get(key).getAsString())
                    .append("&");
        }
        query.deleteCharAt(query.length()-1);

        return query.toString();
    }
}