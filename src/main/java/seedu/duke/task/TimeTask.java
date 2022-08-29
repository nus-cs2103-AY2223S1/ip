package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class TimeTask extends Task {
    private final LocalDate timeReference;

    TimeTask(String taskname, String time) {
        super(taskname);
        timeReference = LocalDate.parse(time.strip());
    }

    String toDisplayDate() {
        return timeReference.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String saveFileFormat() {
        return super.saveFileFormat() + "/" + timeReference;
    }
}
