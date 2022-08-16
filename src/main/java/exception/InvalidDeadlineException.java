package exception;

public class InvalidDeadlineException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {"Blob needs to know deadline of your task...",
                "USAGE: deadline <description> /by <deadline>" };
    }
}
