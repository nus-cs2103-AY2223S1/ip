package exception;

public class InvalidDateFormatException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {"Blob does not understand input datetime...",
            "USAGE: Dates have to be in the format yyyy-mm-dd"};
    }
}
