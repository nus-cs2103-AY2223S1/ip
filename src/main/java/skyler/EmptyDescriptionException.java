package skyler;

public class EmptyDescriptionException extends SkylerException {
    @Override
    public String getMessage() {
        return "No input task";
    }
}
