package duke.task;

public class Deadline extends Event {
    public Deadline(String description, String timeQualifier, String timeDescription) {
        super(description, timeQualifier, timeDescription);
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

}
