package nrw.florian.cloudflareapi.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Florian J. Kleine-Vorholt
 */
@AllArgsConstructor
@Getter
public final class RestResponse<T> {

    /**
     * The response body
     */
    final T body;

    /**
     * The HTTP response code
     */
    final int statusCode;


    /**
     * Checks if the response indicates a successful request.
     *
     * @return  true, if request was successful
     */
    public boolean isSuccessful()
    {
        return this.statusCode >= 200 && this.statusCode < 300 || this.statusCode == 304;
    }
}