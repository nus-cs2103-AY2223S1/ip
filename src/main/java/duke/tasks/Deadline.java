package duke.tasks;

/**
 * Deadline implements methods for Deadline objects.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class Deadline extends Task {

    private final String date;
    private final String taskDesc;

    /**
     * Creates a new Deadline object.
     *
     * @param taskDesc description of the deadline task
     * @param date deadline of the deadline task
     */
    public Deadline(String taskDesc, String date) {
        super(taskDesc);
        this.taskDesc = taskDesc;
        this.date = date;
    }

    /**
     * Fetches the type of task for identification during encoding and decoding for storage.
     *
     * @return a Character 'D' representing Deadline
     */
    @Override
    public char getTaskType() {
        return 'D';
    }

    /**
     * Fetches the description of the task.
     *
     * @return the description of the task
     */
    @Override
    public String getDesc() {
        return this.taskDesc + "|" + this.date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
