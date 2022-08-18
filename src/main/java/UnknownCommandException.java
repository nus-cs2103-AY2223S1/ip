public class UnknownCommandException extends DukeException{

    /**
     * A string of all the recognized commands.
     */
    private String commands = "\ttodo - adds the task to the list\n" +
            "\tdeadline - adds the task with a deadline, e.g. deadline x /by Sunday\n" +
            "\tevent - adds the task that happens at a specific time, e.g. event x /at Mon 2-4pm\n" +
            "\tmark - marks task number x as completed, e.g. mark x\n" +
            "\tunmark - marks task number x as uncompleted, e.g. unmark x" +
            "\tlist - lists out all your current tasks\n" +
            "\tbye - exits the program:(\n";

    /**
     * The command that threw the exception.
     */
    private String cmd;

    /**
     * Constructor for an UnknownCommandException.
     * @param cmd The command that threw the exception.
     */
    public UnknownCommandException(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return super.toString() + " \"" + this.cmd + "\" is not a valid command. Here are the list of commands:\n\n" +
                this.commands;
    }
}
