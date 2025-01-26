package nrw.florian.cloudflareapi.constant;

/**
 * The different record type cloudflare can manage
 *
 * @author Florian J. Kleine-Vorholt
 */
public enum RecordType {

    /**
     * {@code A} record
     */
    A,

    /**
     * {@code AAAA} record
     */
    AAAA,

    /**
     * {@code CAA} record
     */
    CAA,

    /**
     * {@code CNAME} record
     */
    CNAME,

    /**
     * {@code LOC} record
     */
    LOC,

    /**
     * {@code MX} record
     */
    MX,

    /**
     * {@code NS} record
     */
    NS,

    /**
     * {@code SRV} record
     */
    SRV,

    /**
     * {@code TXT} record
     */
    TXT
}