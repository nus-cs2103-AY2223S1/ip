public class InvalidEncodingException extends Exception {
    public InvalidEncodingException() {
        super("Error while decoding: invalid encoding format");
    }
}
