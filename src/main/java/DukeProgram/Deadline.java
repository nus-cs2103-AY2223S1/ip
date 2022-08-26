package DukeProgram;

/**
 * A Deadline task with a date that describes when the task is due
 */
public class Deadline extends DatedJob {

    /**
     * Creates a Deadline class with the given name
     * and a date string that it is due by
     * @param name the name of this task
     * @param dueString the due date of this string
     */
    public Deadline(String name, String dueString) {
        super(name, dueString, "by");
    }


    /**
     * Creates a string tagged with the Deadline tag
     * @return a string that in the format "[D][status] task_name /by date_format"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
