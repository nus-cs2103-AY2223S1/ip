package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public String eventTime;
    public LocalDate exactTime;

    public Event(String name, String eventTime) throws DateTimeParseException {
        super(name);
        this.eventTime = eventTime;
        this.exactTime = LocalDate.parse(eventTime);
    }

    @Override
    public boolean needToRemind() {
        if (isDone) {
            return false;
        } else {
            LocalDate now = LocalDate.now();
            Period period = Period.between(now, exactTime);
            return (period.getYears() == 0 && period.getMonths() == 0 && period.getDays() <= 7);
        }
    }

    @Override
    public String getOutput() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, name, eventTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + exactTime.getMonth() + " "
                + exactTime.getDayOfMonth() + " " + exactTime.getYear() + ")";
    }
}
