package nrw.florian.cloudflareapi.client.zone;

import nrw.florian.cloudflareapi.constant.RecordType;
import nrw.florian.cloudflareapi.exception.UnsuccessfulApiRequestException;
import nrw.florian.cloudflareapi.obj.dns.DNSRecord;

import java.util.List;
import java.util.Objects;

/**
 * Client to manage cloudflare dns records
 *
 * @author Florian J. Kleine-Vorholt
 */
public interface RecordsClient {

    /**
     * Gets all records as List
     *
     * @return  a list of all dns records
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> getAll() throws UnsuccessfulApiRequestException;


    /**
     * Finds all DNS-Records by a specified {@link RecordType}
     *
     * @param recordType    the record type
     * @return              a list of records with the specified record type
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> findByType(RecordType recordType) throws UnsuccessfulApiRequestException;


    /**
     * Finds all DNS-Records by a specific name (EXACT match!)
     *
     * @param name          the name of the record(s)
     * @return              a list of records matching the provided name
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> findByName(String name) throws UnsuccessfulApiRequestException;


    /**
     * Finds all DNS-Records that contain the given string in their name
     *
     * @param name          the name to search for
     * @return              a list with all records that contain the search string in their name
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> findByNameIsLike(String name) throws UnsuccessfulApiRequestException;


    /**
     * Finds all DNS-Records with a specific name and record type
     *
     * @param name          the name of the record(s) (EXACT match!)
     * @param type          the type of the record(s)
     * @return              a list of records matching the provided name and type
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> findByNameAndType(String name, RecordType type) throws UnsuccessfulApiRequestException;


    /**
     * Finds all DNS-Records that contain a specific string and are of a specific type
     *
     * @param name          the name to search for
     * @param type          the type of record(s)
     * @return              a list of records matching the name and type
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> findByNameIsLikeAndType(String name, RecordType type) throws UnsuccessfulApiRequestException;


    /**
     * Finds all DNS-Records that contain a specific value
     *
     * @param value         the value to search for
     * @return              all records containing the value
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> findByValueIsLike(String value) throws UnsuccessfulApiRequestException;


    /**
     * Finds a record by its unique id
     *
     * @param id            the identifier of the record
     * @return              the record for the identifier
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    DNSRecord findById(String id) throws UnsuccessfulApiRequestException;


    /**
     * Deletes a record with by its unique identifier
     *
     * @param recordIdentifier  the identifier of the record to delete
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    void delete(String recordIdentifier) throws UnsuccessfulApiRequestException;


    /**
     * Deletes a record by its object representation
     *
     * @param record            the object of the record to delete
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    default void delete(final DNSRecord record) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(record, "Record cannot be null");
        Objects.requireNonNull(record.getId(), "DNS-Record-ID must not be null!");

        delete(record.getId());
    }


    /**
     * Deletes a set of specified records
     * <p>
     *     The deletion will stop on first error!
     * </p>
     *
     * @param records           the records to delete
     *
     * @throws UnsuccessfulApiRequestException if a record could not be deleted.
     */
    default void delete(final List<DNSRecord> records) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(records, "Records cannot be null");

        for (final DNSRecord record : records) {
            Objects.requireNonNull(record.getId(), "There is at least one DNS-Record without id in set!");
            delete(record.getId());
        }
    }


    /**
     * Creates a set of DNSRecords
     *
     * @param records       the list of records to create
     *
     * @return              the saved DNSRecord entities
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    List<DNSRecord> create(List<DNSRecord> records) throws UnsuccessfulApiRequestException;


    /**
     * Saves or updates a DNS-Record
     *
     * @param record                            the record to save if not existing, or to update if existing
     * @return                                  the saved DNSRecord entity
     *
     * @throws UnsuccessfulApiRequestException  if the request was not successful
     */
    DNSRecord saveOrUpdate(DNSRecord record) throws UnsuccessfulApiRequestException;
}