package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends TimeTask {

    public EventTask(String taskname, String eventTime) {
        super(taskname, eventTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), super.toDisplayDate());
    }

    @Override
    public String saveFileFormat() {
        return "E###" + super.saveFileFormat();
    }

    @Override
    public boolean isClash(Task task) {
        return this.timeIndex().equals(task.timeIndex());
    }
}
