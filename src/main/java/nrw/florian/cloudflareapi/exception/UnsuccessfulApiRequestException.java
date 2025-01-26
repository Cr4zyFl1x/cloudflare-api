package nrw.florian.cloudflareapi.exception;

import nrw.florian.cloudflareapi.CloudflareResponse;

import java.util.Objects;
import java.util.Optional;

/**
 * Exception is thrown when a request to the cloudflare was not successful.
 * <p>
 *     E.g. because of:
 *     <ul>
 *         <li>Resource nopt found</li>
 *         <li>Unable to update</li>
 *         <li>Syntax error</li>
 *         <li>...</li>
 *     </ul>
 * </p>
 * <p>
 *     The error messages are available in the {@code response} attribute
 * </p>
 *
 * @see UnsuccessfulApiRequestException#getResponseData()
 *
 * @author Florian J. Kleine-Vorholt
 */
public class UnsuccessfulApiRequestException extends RuntimeException {

    private final CloudflareResponse<?> response;


    public UnsuccessfulApiRequestException(final String message, final Throwable cause)
    {
        super(message, cause);
        this.response = null;
    }

    public UnsuccessfulApiRequestException(final String message)
    {
        super(message);
        this.response = null;
    }

    public UnsuccessfulApiRequestException(final Throwable cause)
    {
        super(cause);
        this.response = null;
    }

    public UnsuccessfulApiRequestException(CloudflareResponse<?> response, Throwable cause)
    {
        super(cause);
        this.response = Objects.requireNonNull(response);
    }

    public UnsuccessfulApiRequestException(final CloudflareResponse<?> response)
    {
        super();
        this.response = Objects.requireNonNull(response);
    }


    /////////////////////////////
    /////////////////////////////


    /**
     * Gets the response data held by this exception instance.
     * <p>
     *     The response data may contain additional error information.
     * </p>
     *
     * @return  the response data
     */
    public Optional<CloudflareResponse<?>> getResponseData()
    {
        return Optional.ofNullable(response);
    }


    /////////////////////////////
    /////////////////////////////


    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage()
    {
        if (getResponseData().isPresent()) {

            final CloudflareResponse<?> responseData = getResponseData().get();

            return "[Request failed | %s] - %s"
                    .formatted(
                            responseData.getStatusCode(),
                            responseData.getErrors().values().stream().toList().getFirst());
        }

        // Otherwise standard output
        return super.getMessage();
    }
}