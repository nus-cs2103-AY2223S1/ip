package duke.events;

/**
 * Extension class from Task.
 * Does not require a date in its arguments to be initialized
 */
public class Todo extends Task {
    private static final String TYPE = "[T]";
    private static final int NUM_ELEMENTS_EXPECTED = 3;
    public Todo(String description) {

        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }


    public static Todo readTask(String[] values) {
        assert values.length == NUM_ELEMENTS_EXPECTED : "Save data was not parsed correctly, incorrect number of elements read";
        boolean isDone = values[1].equals("0");
        String description = values[2];
        return new Todo(isDone, description);
    }

    @Override
    public String savableString() {
        return "T" + super.savableString();
    }

    @Override
    public String toString() {
        return TYPE + super.toString();
    }
}
