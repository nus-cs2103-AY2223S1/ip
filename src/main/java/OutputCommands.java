/**
 * A class for the commands with just output.
 */
public class OutputCommands extends Command {

    /** The type of command. */
    private String type;

    /** Boolean indicating if the command is "bye". */
    private boolean isExit;

    /**
     * Constructor for the OutputCommands class.
     *
     * @param type   The type of command.
     * @param isExit Boolean indicating if the command is "bye".
     */
    public OutputCommands(String type, Boolean isExit) {
        this.type = type;
        this.isExit = isExit;
    }

    /**
     * Executes the output commands.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (isExit) {
            return;
        }
        switch (type) {
        case "list":
            ui.printList();
            break;
        }
    }

    /**
     * Returns true if this command is "bye", and false otherwise.
     *
     * @return True if this command is "bye", and false otherwise.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
