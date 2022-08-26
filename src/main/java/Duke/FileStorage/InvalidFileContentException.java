package Duke.FileStorage;

public class InvalidFileContentException extends Exception {
    public InvalidFileContentException(String errMsg) {
        super(errMsg);
    }
}
