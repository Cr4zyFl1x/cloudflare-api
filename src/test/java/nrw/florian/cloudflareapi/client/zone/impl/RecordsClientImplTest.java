package nrw.florian.cloudflareapi.client.zone.impl;

import lombok.AccessLevel;
import lombok.Getter;
import nrw.florian.cloudflareapi.CloudflareClientTest;
import nrw.florian.cloudflareapi.constant.RecordType;
import nrw.florian.cloudflareapi.obj.dns.DNSRecord;
import nrw.florian.cloudflareapi.obj.zone.Zone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Florian J. Kleine-Vorholt
 */
public class RecordsClientImplTest extends CloudflareClientTest {

    @Getter(AccessLevel.PRIVATE)
    private static Zone zone;


    @BeforeAll
    public static void setUp()
    {
        zone = getClient().zone().findByName("blödtube.com");
    }



    @Test
    public void testGetAllRecords_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final List<DNSRecord> records = getClient().records(getZone()).getAll();
            assertNotNull(records);
            assertFalse(records.isEmpty());

            for (final DNSRecord record : records) {
                System.out.println(record);
            }
        });
    }

    @Test
    public void testGetRecordsByType_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final List<DNSRecord> aRecords = getClient().records(getZone()).findByType(RecordType.A);
            assertNotNull(aRecords);
            assertFalse(aRecords.isEmpty());

            final List<DNSRecord> caaRecords = getClient().records(getZone()).findByType(RecordType.CAA);
            assertNotNull(caaRecords);
            assertTrue(caaRecords.isEmpty());
        });
    }

    @Test
    public void testGetRecordsByName_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final List<DNSRecord> records = getClient().records(getZone()).findByName("xn--bldtube-b1a.com");
            assertNotNull(records);
            assertFalse(records.isEmpty());

            final List<DNSRecord> records1 = getClient().records(getZone()).findByName("www.xn--bldtube-b1a.com");
            assertFalse(records1.isEmpty());
            assertEquals(2, records1.size());
        });
    }

    @Test
    public void testGetRecordsByNameLikeAndType_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final List<DNSRecord> records = getClient().records(getZone())
                    .findByNameIsLikeAndType("www", RecordType.A);

            assertNotNull(records);
            assertEquals(1, records.size());

        });
    }

    @Test
    public void testGetRecordsByNameAndType_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final List<DNSRecord> records = getClient().records(getZone())
                    .findByNameAndType("xn--bldtube-b1a.com", RecordType.AAAA);

            assertNotNull(records);
            assertEquals(1, records.size());
        });
    }

    @Test
    public void testGetRecordsByValueIsLike_CFIntegTest()
    {
        assertDoesNotThrow(() -> {

            final List<DNSRecord> records = getClient().records(getZone()).findByValueIsLike("185.240.242");

            assertNotNull(records);
            assertEquals(2, records.size());

        });
    }

    @Test
    public void testSaveRecord_CFIntegTest()
    {
        final DNSRecord record = new DNSRecord();
        record.setName("google.xn--bldtube-b1a.com");
        record.setType(RecordType.A);
        record.setContent("185.240.242.29");
        record.setTtl(60);
        record.setProxied(false);

        DNSRecord created = getClient().records(getZone()).saveOrUpdate(record);
        assertNotNull(created);
        assertNotNull(created.getId());

        System.out.println(created);
    }

    @Test
    public void testUpdateRecord_CFIntegTest()
    {
        final DNSRecord record = getClient().records(getZone()).findByNameAndType("google.xn--bldtube-b1a.com",
                RecordType.A).getFirst();

        assertNotNull(record);

        record.setContent("185.240.242.254");
        record.setTtl(57600);
        record.setProxied(true);

        getClient().records(getZone()).saveOrUpdate(record);
    }

    @Test
    public void testBulkDeleteRecords_CFIntegTest()
    {
        for (int i = 0; i < 10; i++) {
            getClient().records(getZone()).saveOrUpdate(new DNSRecord("test-cf", RecordType.TXT, UUID.randomUUID().toString()));
        }

        final List<DNSRecord> records = getClient().records(getZone())
                .findByNameIsLikeAndType("test-cf", RecordType.TXT);

        getClient().records(getZone()).delete(records);
    }

    @Test
    public void testBulkCreateRecords_CFIntegTest()
    {
        final List<DNSRecord> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            records.add(new DNSRecord("test-cf", RecordType.TXT, UUID.randomUUID().toString()));
        }

        final List<DNSRecord> createdRecords = getClient().records(getZone()).create(records);
        assertNotNull(createdRecords);
        assertEquals(10, createdRecords.size());
    }
}