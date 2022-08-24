package duke.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "T" + Task.STORAGE_DELIMITER + taskString;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}




