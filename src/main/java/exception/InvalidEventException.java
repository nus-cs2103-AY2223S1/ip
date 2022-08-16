package exception;

public class InvalidEventException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {"Blob needs to know timeframe of your task...",
                "USAGE: event <description> /by <timeframe>" };
    }
}
