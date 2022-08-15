public class MissingArgumentException extends DukeException{
    public MissingArgumentException(Command command) {
        super("OOOOF! You are missing an/some argument(s) for the [" + command + "] command!\nUsage: " + command.getFormat());
    }
}