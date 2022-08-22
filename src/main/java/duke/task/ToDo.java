package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        return "T" + Task.STORAGE_DELIMITER
                + doneDescriptionString;
    }
}