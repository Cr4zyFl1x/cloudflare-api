package nrw.florian.cloudflareapi.constant;

/**
 * Matcher for request filters
 *
 * @author Florian J. Kleine-Vorholt
 */
public enum Match {

    /**
     * All filter components must match
     */
    ALL,

    /**
     * Any filter component must match
     */
    ANY;


    /**
     * Gets the enum value in lowercase
     *
     * @return  the enum value in lowercase
     */
    public String asLower()
    {
        return name().toLowerCase();
    }
}
