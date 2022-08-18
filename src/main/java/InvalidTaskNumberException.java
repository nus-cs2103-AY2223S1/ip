public class InvalidTaskNumberException extends DukeException {

    public InvalidTaskNumberException() {
        super("InvalidTaskNumber exception");
    }

    @Override
    public String toString() {
        return "OOPS!!! Please input a valid task number!";
    }

}
