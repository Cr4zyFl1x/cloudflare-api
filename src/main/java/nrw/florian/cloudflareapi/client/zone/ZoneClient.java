package nrw.florian.cloudflareapi.client.zone;

import nrw.florian.cloudflareapi.constant.ZoneType;
import nrw.florian.cloudflareapi.exception.UnsuccessfulApiRequestException;
import nrw.florian.cloudflareapi.obj.zone.Zone;

import java.util.List;
import java.util.Objects;

/**
 * Client to manage cloudflare zones
 *
 * @author Florian J. Kleine-Vorholt
 */
public interface ZoneClient {

    /**
     * Finds all zones
     *
     * @return          the zones in a list
     *
     * @throws UnsuccessfulApiRequestException if the request was not successful
     */
    List<Zone> getAll() throws UnsuccessfulApiRequestException;


    /**
     * Finds a zone by its identifier
     *
     * @param id        the identifier of the zone
     * @return          the respective zone object
     *
     * @throws UnsuccessfulApiRequestException if the request was not successful
     */
    Zone findById(String id) throws UnsuccessfulApiRequestException;


    /**
     * Finds a zone by its name (e.g. google.com)
     *
     * @param name      the name of the zone
     * @return          the respective zone object
     *
     * @throws UnsuccessfulApiRequestException if the request was not successful
     */
    Zone findByName(String name) throws UnsuccessfulApiRequestException;


    /**
     * Creates a new Zone
     *
     * @param name  the name of the zone (e.g. google.de)
     * @param type  the zone type (may be null)
     *
     * @return      the object of the created zone
     *
     * @throws UnsuccessfulApiRequestException if the request was not successful
     */
    Zone create(final String name, final ZoneType type) throws UnsuccessfulApiRequestException;


    /**
     * Deletes a zone by its identifier
     *
     * @param id        the identifier of the zone to delete
     *
     * @throws UnsuccessfulApiRequestException if the request was not successful (e.g. not existing...)
     */
    void delete(String id) throws UnsuccessfulApiRequestException;


    /**
     * Deletes a zone by its zone object
     *
     * @param zone      the zone object
     *
     * @throws UnsuccessfulApiRequestException if the request was not successful (e.g. not existing...)
     */
    default void delete(final Zone zone) throws UnsuccessfulApiRequestException
    {
        Objects.requireNonNull(zone);
        Objects.requireNonNull(zone.getId(), "Zone identifier is mandatory!");

        delete(zone.getId());
    }
}