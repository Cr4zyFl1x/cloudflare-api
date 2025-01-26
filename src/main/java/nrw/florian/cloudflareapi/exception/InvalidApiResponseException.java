package nrw.florian.cloudflareapi.exception;

import lombok.experimental.StandardException;

/**
 * Thrown if the API response is different from expected
 *
 * @author Florian J. Kleine-Vorholt
 */
@StandardException
public class InvalidApiResponseException extends RuntimeException {
}