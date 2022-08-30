package duke.tasks;

public class Deadline extends Task {

    private final String date;
    private final String taskDesc;

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
