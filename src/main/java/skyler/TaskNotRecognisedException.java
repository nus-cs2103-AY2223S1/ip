package skyler;

public class TaskNotRecognisedException extends SkylerException {
    @Override
    public String getMessage() {
        return "Command not recognised";
    }
}
