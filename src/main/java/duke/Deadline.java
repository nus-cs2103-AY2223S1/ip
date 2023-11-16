package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a kind of Task.
 * @author Lan Jingbo, Jerry
 */
class Deadline extends Task {

    protected LocalDate by;

    /**
     * The constructor of this kind of task.
     *
     * @param itself the descriptions of Task
     * @param by the deadline date of the task.
     */
    public Deadline(String itself, String by) {
        super(itself);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * To check if the task due in input date.
     *
     * @param localDate the input date
     * @return whether it is on the expected date
     */
    public boolean isOnDate(LocalDate localDate) {
        return by.equals(localDate);
    }

    /**
     * convert the task to saving file form.
     * @return the saving file form
     */
    @Override
    public String writeToFile() {
        return "D|" + super.writeToFile() + "|" + by;
    }

    /**
     * convert the file information to concrete deadline task.
     *
     * @param input the string format in the .txt file
     * @return the concrete deadline
     */
    public static Deadline fromFileDescription(String input) {
        String[] strArray = input.split("\\|");
        String info = strArray[2];
        String by = strArray[3];
        Deadline deadline = new Deadline(info, by);
        if (strArray[1].equals("Y")) {
            deadline.donelah();
        }
        return deadline;
    }

    /**
     * Creates a String to represent the task during listing.
     *
     * @return String to be displayed when listing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
