package cs2103t.ip.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    /** Returns a message to indicate that a task has been successfully added
     * that is shown to user when a task of type deadline is added to the list.
     * @return A message shown to user upon addition of task.
     */
    @Override
    public String addString(int i) {
        String line = "_______________________________\n";
        String gotIt = "Got it. I've added this task: \n";
        String task = this.toString() + "\n";
        String now = String.format("Now you have %d tasks in the list \n", i);
        return line + gotIt + task + now + line;
    }

    /** Returns a string representation of the task of type deadline.
     * @return String representation of the task of type deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    /** Returns a string representation of the task to be saved into the file.
     * @return String representation of the task.
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "D" + "X " + this.description + "/by" + this.by + "\n";
        } else {
            return "D  " + this.description + "/by" + this.by + "\n";
        }
    }
}
