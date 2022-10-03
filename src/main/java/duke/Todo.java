package duke;
public class Todo extends Task {

    protected String at;

    public Todo(String description) {
        super(description);
    }

    /**
     * String format for text file.
     *
     * @return string to be written into the text file.
     */
    @Override
    public String toStringFileFormat() {
        return "T | " + super.toStringFileFormat();
    }

    /**
     * String representation of this class.
     *
     * @return string representation of this class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}