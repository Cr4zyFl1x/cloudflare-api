package nrw.florian.cloudflareapi.obj;

/**
 * Marks a pojo as uniquely identifiable
 *
 * @author Florian J. Kleine-Vorholt
 */
public interface Identifiable {

    /**
     * Unique identifier for this resource
     *
     * @return unique identifier
     */
    String getId();
}