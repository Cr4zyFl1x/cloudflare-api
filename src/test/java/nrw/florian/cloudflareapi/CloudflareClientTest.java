package nrw.florian.cloudflareapi;

import lombok.AccessLevel;
import lombok.Getter;
import nrw.florian.cloudflareapi.credential.CloudflareCredentials;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Florian J. Kleine-Vorholt
 */
public class CloudflareClientTest {

    @Getter(AccessLevel.PROTECTED)
    private static CloudflareClient client;


    @BeforeAll
    public static void setUp()
    {
        final CloudflareCredentials cred = new CloudflareCredentials(
                System.getenv("CF_API_KEY"),
                System.getenv("CF_API_MAIL"));

        client = CloudflareClient.of(cred);
    }
}