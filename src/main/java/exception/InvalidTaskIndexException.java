package exception;

public class InvalidTaskIndexException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {"Blob cannot find that task..., maybe task no exist...?"};
    }
}
