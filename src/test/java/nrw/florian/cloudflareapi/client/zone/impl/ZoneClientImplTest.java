package nrw.florian.cloudflareapi.client.zone.impl;

import nrw.florian.cloudflareapi.CloudflareClientTest;
import nrw.florian.cloudflareapi.obj.zone.Zone;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Florian J. Kleine-Vorholt
 */
public class ZoneClientImplTest extends CloudflareClientTest {

    @Test
    public void testGetAllZones_CFIntegTest()
    {
        assertDoesNotThrow(() -> {
            final List<Zone> zones = getClient().zone().getAll();
            assertNotNull(zones);
            assertFalse(zones.isEmpty());

            for (Zone zone : zones) {
                System.out.println(zone);
            }
        });
    }

    @Test
    public void testCreateZoneAndDelete_CFIntegTest()
    {
        Zone[] zone = new Zone[1];
        assertDoesNotThrow(() -> {

            zone[0] = getClient().zone().create("cfapi-" + UUID.randomUUID() + ".com", null);
            assertNotNull(zone[0]);
            assertNotNull(zone[0].getId());

        }, "Unable to create zone!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        assertDoesNotThrow(() -> {

            getClient().zone().delete(zone[0]);

        }, "Unable to delete zone!");
    }

    @Test
    public void testGetZoneByName_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final Zone zone = getClient().zone().findByName("blödtube.com");
            assertNotNull(zone);
            assertNotNull(zone.getId());

            System.out.println(zone.getStatus());

        });
    }
}