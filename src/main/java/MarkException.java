public class MarkException extends DukeException {

    public MarkException() {
        super("mark exception");
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! Please input a valid task number!";
    }

}
