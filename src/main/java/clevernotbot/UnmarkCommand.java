package clevernotbot;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructor for the unMarkCommand.
     *
     * @param commandName description of command.
     * @param exit        checking if program intends to exit.
     */
    public UnmarkCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the mark command to unmark the task as completed.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        try {
            //unmark 3 -> 2, because number 3 is actually idx 2
            int number = Integer.parseInt(getCommandName().split(" ")[1]) - 1;
            Task taskToUnmark = tasks.getTask(number);
            if (taskToUnmark.checkMarked().equals("X")) {
                Task unmarkedTask = taskToUnmark.toggleCompleted();
                tasks.removeTask(taskToUnmark);
                tasks.addTaskByIdx(number, unmarkedTask);
                textBox.chat(String.format("OK, I've marked this task as not done yet:\n  [%s] %s",
                        unmarkedTask.checkMarked(), unmarkedTask.getName()));
            } else {
                textBox.chat("Hey! This task is already unmarked!");
            }
        } catch (IndexOutOfBoundsException ex) {
            String exceptionErrorMessage = String.format(
                    "Invalid number. Please input the number from 1 - %d", tasks.getSize());
            throw new CleverNotBotException(exceptionErrorMessage, textBox);
        } catch(NumberFormatException ex) {
            throw new CleverNotBotException("Input a valid number!",textBox);
        }
    }
}
