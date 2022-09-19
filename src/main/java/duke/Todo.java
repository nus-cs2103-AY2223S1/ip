package duke;

public class Todo extends Task {
    private final char tag = 'T';

    public Todo(String description) {
        super(description.substring(5));
    }

    @Override
    public String printTask() {
        return String.format("[%s]%s",
                this.tag,
                super.printTask());
    }
}
