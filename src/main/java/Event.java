import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = LocalDate.parse(at);
    }

    public static Event EventFromData(String taskFromData, boolean isDone) throws DukeException {
        String[] eventDesc = taskFromData.split("\\|", 2);
        if (eventDesc.length != 2 || eventDesc[0].equals("") || eventDesc[1].equals("")) {
            throw new DukeException("OOPS! An event must contain a description and a date.");
        }
        Event result = new Event(eventDesc[0], eventDesc[1]);
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
