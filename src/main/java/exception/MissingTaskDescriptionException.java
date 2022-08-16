package exception;

public class MissingTaskDescriptionException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {"Blob needs to know details of task...",
                "USAGE: todo/event/deadline <description> (...)" };
    }
}
