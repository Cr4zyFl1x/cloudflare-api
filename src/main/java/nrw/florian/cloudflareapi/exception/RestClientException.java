package nrw.florian.cloudflareapi.exception;

import lombok.experimental.StandardException;

/**
 * Thrown on communication errors with the CF-Api
 *
 * @author Florian J. Kleine-Vorholt
 */
@StandardException
public class RestClientException extends RuntimeException {
}