package ip.exception;

public class NoTaskFound extends DukeException {
    private int index;

    public NoTaskFound(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "No task was found at index: " + ++index;
    }
}
