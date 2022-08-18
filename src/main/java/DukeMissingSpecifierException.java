public class DukeMissingSpecifierException extends DukeException {
    public DukeMissingSpecifierException(String command, String specifier) {
        super("It seems like you are missing a specifier for command " + command + ". Please try again with" +
                specifier);
    }
}
