package clevernotbot;

/**
 * Represents a command to show what is in the list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for the ListCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public ListCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the list command to list out every task in list.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        if (tasks.getSize() < 1) {
            textBox.chat("There is no task currently assigned.");
        } else {
            int counter = 1;
            StringBuilder op = new StringBuilder();
            for (Task task : tasks.getTaskList()) {
                op.append(counter++);
                op.append(".");
                op.append(task.toString());
                op.append("\n");
            }
            textBox.chat(op.toString());
        }
    }
}
