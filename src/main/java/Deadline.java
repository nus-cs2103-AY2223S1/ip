/**
 * This class represents tasks that need to be done before a specific date/time.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for creating a deadline.
     * @param description Task description from user input.
     * @param by Date/time as deadline from user input.
     */
    //@@author carriezhengjr-reused
    // Reused code under subsection "Extension: A-Inheritance" of the
    // section "Duke Level-4: ToDo, Event, Deadline"
    // from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html#ip
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks date/time set as deadline by the user.
     * @return Date/time in string representation.
     */
    public String getDeadline() {
        return this.by;
    }

    /**
     * Displays the task type of deadline as D.
     * @return D.
     */
    @Override
    public String taskType() {
        return "D";
    }

    /**
     * Displays the deadline with its type, status (done or undone), description and date/time.
     * @return Task type, status, description and date/time.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.by);
    }

}
