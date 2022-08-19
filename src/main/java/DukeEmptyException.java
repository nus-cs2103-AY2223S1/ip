public class DukeEmptyException extends DukeException{

    public DukeEmptyException(String task) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.", task));
    }
}
