package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class TimeTask extends Task {
    private final LocalDate timeReference;
    private final String timeIndex;

    TimeTask(String taskname, String time) {
        super(taskname);
        time = time.strip();
        timeReference = LocalDate.parse(time);
        timeIndex = time;
    }

    String toDisplayDate() {
        return timeReference.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String saveFileFormat() {
        return super.saveFileFormat() + "/" + timeReference;
    }

    @Override
    String timeIndex() {
        return timeIndex;
    }
}
