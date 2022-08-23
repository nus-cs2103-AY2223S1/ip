package ted.task;

/**
 * A class that encapsulate a todo task
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Encode to a string that can be stored in file
     * @return string that is store-able
     */
    @Override
    public String encode() {
        return String.format("T | %s", super.encode());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
