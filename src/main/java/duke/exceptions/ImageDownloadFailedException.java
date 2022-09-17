package duke.exceptions;

/**
 * Exception thrown when the image failed to download
 */
public class ImageDownloadFailedException extends DukeException {
    private static final String DESCRIPTION = "I wasn't able to find a replacement for %s. Try again later";

    public ImageDownloadFailedException(String target) {
        super(String.format(DESCRIPTION, target));
    }
}
