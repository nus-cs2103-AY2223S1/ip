package kohaku.commands;
import kohaku.datastruct.TaskList;
import kohaku.daveexceptions.DaveException;
import kohaku.daveexceptions.DaveNoTasksException;

public class RemoveTaskCommand extends Command {

    private TaskList tasks;
    private int index;

    /**
     * Initialises a remove task command with a provided tasklist and
     * index of task to be removed
     *
     * @param tasks Tasklist containing the task to be removed
     * @param index Index of the Task to be removed
     */
    public RemoveTaskCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Initialises a remove task command with a provided tasklist and
     * index of task to be removed in String format
     *
     * @param tasks Tasklist containing the task to be removed
     * @param input Index of the Task to be removed in String format
     */
    public RemoveTaskCommand(TaskList tasks, String input) throws DaveException {
        try {
            if (input.equals("")) {
                throw new DaveException("Oh no!!! Please tell me which task to remove!");
            }
            this.index = Integer.parseInt(input);
            this.tasks = tasks;
        } catch (NumberFormatException e) {
            throw new DaveException(
                    String.format(
                            "Oh no!!! Please give me a valid task to remove between 1 and %d!", tasks.size()
                    )
            );
        }
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() throws DaveException {
        try{
            return this.tasks.remove(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DaveException(
                    String.format(
                            "Oh no!!! Please give me a valid task to remove between 1 and %d!", tasks.size()
                    )
            );
        } catch (DaveNoTasksException de) {
            throw de;
        }
    }

}
