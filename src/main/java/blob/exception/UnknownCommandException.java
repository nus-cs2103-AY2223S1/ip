package blob.exception;

public class UnknownCommandException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { "Sorry... blob.Blob no understand your command..." };
    }
}
