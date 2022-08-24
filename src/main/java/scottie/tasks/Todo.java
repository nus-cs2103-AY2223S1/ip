package scottie.tasks;

public class Todo extends Task {
    public Todo(String description) {
        this(description, false);
    }

    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    static Todo fromEncodedString(String encodedString) throws InvalidTaskDataException {
        String[] splitTaskData = encodedString.split("\\|");
        if (splitTaskData.length < 3) {
            throw new InvalidTaskDataException("The data for this to-do is not formatted correctly.");
        }
        String description = splitTaskData[2];
        boolean isDone = splitTaskData[1].equals("1");
        return new Todo(description, isDone);
    }

    @Override
    String toEncodedString() {
        return String.format("T|%s", super.toEncodedString());
    }

    @Override
    public String toString() {
        return "(T) " + super.toString();
    }
}
