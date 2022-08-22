package duke.task;

import duke.Duke;

public class EventTask extends Task {

    private static final String LABEL = "E";

    private String dateTime;

    protected EventTask(String taskTitle, String dateTime) {
        super(taskTitle, TaskType.EVENT);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return super.getStringRepresentation(LABEL, Duke.BY_DATE_DELIMITER + " " + dateTime);
    }
}
