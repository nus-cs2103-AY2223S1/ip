
public class ToDo extends Task {

    private static final String TODO_LETTER = "T";

    // Constructors
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    

    @Override
    public ToDo markAsDone() {
        return new ToDo(this.description, true);
    }

    @Override
    public ToDo markAsUndone() {
        return new ToDo(this.description, false);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TODO_LETTER, super.toString());
    }

}
