package duke;

public class Deadline extends Event {

    Deadline(String description, String timeQualifier, String timeDescription) {
        super(description, timeQualifier, timeDescription);
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

}
