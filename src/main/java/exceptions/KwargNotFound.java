package exceptions;

public class KwargNotFound extends HazellException {
    private String command;
    private String key;

    public KwargNotFound(String command, String key) {
        this.command = command;
        this.key = key;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The %s command requires a mandatory /%s option.", this.command, this.key);
    }
}
