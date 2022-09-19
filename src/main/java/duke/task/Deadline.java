package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Class which manages tasks of type deadline.
 */
public class Deadline extends Task {

    /**
     * Creates an instance of deadline task.
     * @param desc description of task
     */
    public Deadline(String desc) {
        super(desc);

        String[] parts = desc.split("/by ");
        super.description = parts[0];
        super.isDone = false;
        super.type = "D";

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
        return "[" + type + "][" + mark + "]" + this.description + "(by: " + formattedDate + ")";

    }
}
