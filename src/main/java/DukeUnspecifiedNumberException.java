public class DukeUnspecifiedNumberException extends DukeException {

    public DukeUnspecifiedNumberException(String command) {
        super(command + " command should be specified with a task number");
    }
}
