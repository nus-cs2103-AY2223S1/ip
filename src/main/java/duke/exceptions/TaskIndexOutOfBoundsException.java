package duke.exceptions;

public class TaskIndexOutOfBoundsException extends RuntimeException {
    public TaskIndexOutOfBoundsException() {
        super("Index is out of range");
    }

    public TaskIndexOutOfBoundsException(String s) {
        super(s);
    }

    public TaskIndexOutOfBoundsException(int index) {
        super("Index out of range: " + index);
    }

    public TaskIndexOutOfBoundsException(long index) {
        super("Index out of range: " + index);
    }

}
