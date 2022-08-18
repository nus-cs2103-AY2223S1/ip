public class InvalidArgumentException extends DukeException{
    /**
     * The type of Task that threw the exception.
     */
    private String task;

    /**
     * The correct command to be used.
     */
    private String cmd;

    /**
     * Constructor for an InvalidArgumentException.
     * @param task The type of Task that threw the exception.
     * @param cmd The correct command to be used.
     */
    public InvalidArgumentException(String task, String cmd) {
        this.task = task;
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return super.toString() + " The task " + this.task + " requires the command " + this.cmd +
                " after the description of the task!";
    }
}
