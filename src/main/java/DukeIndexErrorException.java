public class DukeIndexErrorException extends DukeException{
    public DukeIndexErrorException(int size) {
        super(String.format("☹ OOPS!!! Choose a task number from 1-%d.", size));
    }

    public DukeIndexErrorException() {
        super(String.format("☹ OOPS!!! Please add a task to use this command."));
    }
}
