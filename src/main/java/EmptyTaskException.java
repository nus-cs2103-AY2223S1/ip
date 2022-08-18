public class EmptyTaskException extends DukeException{
    /**
     * The command that threw the exception.
     */
    private String cmd;

    /**
     * Constructor for an EmptyTaskException.
     * @param cmd The command that threw the exception.
     */
    public EmptyTaskException(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return super.toString() + " The task " + this.cmd + " is empty! Please add the task description:)";
    }
}
