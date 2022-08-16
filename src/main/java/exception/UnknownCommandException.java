package exception;

public class UnknownCommandException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { "Sorry... Blob no understand your command..." };
    }
}
