package nrw.florian.cloudflareapi.net;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Florian J. Kleine-Vorholt
 */
public class ApiUrlFormatterTest {

    @Test
    public void testFormat()
    {
        final String baseUrl = "https://api.florian.nrw/v2";
        final String baseUrl2 = "https://api.florian.nrw/v2/";
        final String component = "/vserver";
        final String component2 = "/vserver/";

        Assertions.assertEquals("https://api.florian.nrw/v2/vserver",
                ApiUrlFormatter.format(baseUrl, component));
        Assertions.assertEquals("https://api.florian.nrw/v2/vserver",
                ApiUrlFormatter.format(baseUrl2, component2));
        Assertions.assertEquals("https://api.florian.nrw/v2/vserver",
                ApiUrlFormatter.format(baseUrl, component2));
        Assertions.assertEquals("https://api.florian.nrw/v2/vserver",
                ApiUrlFormatter.format(baseUrl2, component));
    }

    @Test
    public void testFormatWithIdentifiers()
    {
        final String baseUrl = "https://api.florian.nrw/v2";
        final String baseUrl2 = "https://api.florian.nrw/v2/";
        final String component = "/vserver";
        final String component2 = "/vserver/{0}/{1}/";


        Assertions.assertEquals("https://api.florian.nrw/v2/vserver",
                ApiUrlFormatter.format(baseUrl2, component, new String[]{}));
        Assertions.assertEquals("https://api.florian.nrw/v2/vserver/456as1d5/a",
                ApiUrlFormatter.format(baseUrl2, component2, "456as1d5", "a"));
    }

    @Test
    public void testGetQueryString()
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add("per_page", new JsonPrimitive(500000));
        jsonObject.add("type", new JsonPrimitive("A"));

        Assertions.assertEquals("?per_page=500000&type=A", ApiUrlFormatter.getQueryString(jsonObject));

        jsonObject.add("match", new JsonPrimitive("all"));
        Assertions.assertEquals("?per_page=500000&type=A&match=all", ApiUrlFormatter.getQueryString(jsonObject));
    }
}