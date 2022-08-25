package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String taskDescription, LocalDate at) throws DukeException {
        super(taskDescription);
        this.at = at;
    }

    public static Event EventFromFile(String taskFromFile, boolean isDone) throws DukeException {
        String[] eventDesc = taskFromFile.split("\\|", 2);
        if (eventDesc.length != 2 || eventDesc[0].equals("") || eventDesc[1].equals("")) {
            throw new DukeException("OOPS! An event must contain a description and a date.");
        }
        Event result = new Event(eventDesc[0], LocalDate.parse(eventDesc[1]));
        result.isDoneSetter(isDone);
        return result;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toFile() {
        return "E" + "|" + super.toFile() + "|" + at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
