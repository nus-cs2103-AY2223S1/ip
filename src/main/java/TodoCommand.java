/**
 * A class for the todo command.
 */
public class TodoCommand extends Command {

    /** The description of the deadline task as input by the user. */
    private final String description;

    /**
     * Constructor for the TodoCommand class.
     *
     * @param description The description of the deadline task as input by the user.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the "todo" command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws EmptyTaskException If the followup text after the command is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyTaskException {
        if (this.description.trim().equals("")) {
            throw new EmptyTaskException("todo");
        }

        taskList.add(new Todo(this.description));
        System.out.println("  Added task todo: \n\t" + taskList.getLast());
        ui.printListCount();
    }
}
