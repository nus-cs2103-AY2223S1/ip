package duke.task;
import java.time.format.DateTimeFormatter;

/**
 * Class which manages tasks of type event.
 */
public class Event extends Task {

    /**
     * Creates an instance of event task.
     * @param desc description of task
     */
    public Event(String desc) {
        super(desc);

        String[] parts = desc.split("/at ");
        super.description = parts[0];
        super.isDone = false;
        super.type = "E";

        try {
            String dateInString = parts[1];
            DateFormatter newDate = new DateFormatter(dateInString);
            super.dateAndTime = newDate.formattedDate();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getTask() {
        String mark = " ";
        if (this.isDone) {
            mark = "X";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String formattedDate = dateAndTime.format(formatter);
        return "[" + type + "][" + mark + "]" + this.description + "(at: " + formattedDate + ")";
    }
}
