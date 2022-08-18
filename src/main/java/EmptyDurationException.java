public class EmptyDurationException extends DukeException{
    /**
     * The type of Task that threw the exception.
     */
    private String task;

    /**
     * The command used.
     */
    private String cmd;

    /**
     * Constructor for an EmptyDurationException.
     * @param task The type of Task that threw the exception.
     * @param cmd The command used.
     */
    public EmptyDurationException(String task, String cmd) {
        this.task = task;
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return super.toString() + " The time/date for the task " + this.task + " after the command " + this.cmd +
                " is empty! Please add the\n  task time/date:)";
    }
}
