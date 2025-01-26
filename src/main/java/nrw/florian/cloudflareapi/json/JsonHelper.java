package nrw.florian.cloudflareapi.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import nrw.florian.cloudflareapi.net.CloudflareAccessor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * JSON-Utility class
 *
 * @author Florian J. Kleine-Vorholt
 */
public final class JsonHelper {

    private JsonHelper()
    {
        throw new AssertionError("This class cannot be instantiated");
    }


    /**
     * Converts a JSON-String to a {@link JsonElement} object
     *
     * @param json      the json string
     * @return          the json object
     */
    public static JsonElement toJsonElement(final String json)
    {
        return JsonParser.parseString(json);
    }


    /**
     * Maps a JSON-Array to a list of pojos
     *
     * @param jsonElements      the json data structure
     * @param clazz             the pojo class
     *
     * @return                  the list of pojo objects representing the json data
     *
     * @param <T>               the pojo class
     */
    @SuppressWarnings("all")
    public static <T> List<T> toListOfObjects(final JsonArray jsonElements, final Class<T> clazz)
    {
        Objects.requireNonNull(jsonElements);
        Objects.requireNonNull(clazz);

        return CloudflareAccessor.getGson().fromJson(jsonElements, new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        });
    }


    /**
     * Maps a JSON-Object to a pojo class
     *
     * @param jsonObject    the json data structure
     * @param clazz         the pojo class
     *
     * @return              the pojo object representing the json data
     *
     * @param <T>           the pojo class
     */
    public static <T> T toObject(final JsonObject jsonObject, final Class<T> clazz)
    {
        Objects.requireNonNull(jsonObject);
        Objects.requireNonNull(clazz);

        return CloudflareAccessor.getGson().fromJson(jsonObject, clazz);
    }
}
