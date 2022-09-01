package clevernotbot;

/**
 * Represents a command to mark a task.
 */
public class MarkCommand extends Command {

    /**
     * Constructor for the MarkCommand.
     *
     * @param commandName Description of command.
     * @param exit Checking if program intends to exit.
     */
    public MarkCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    /**
     * Runs the mark command to mark the task as completed.
     *
     * @param tasks The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        //mark 3 -> 2, because number 3 is actually idx 2
        int number = Integer. parseInt(getCommandName().split(" ")[1]) - 1;
        Task taskToMark = tasks.getTask(number);
        if(taskToMark.checkMarked().equals(" ")) {
            Task markedTask = taskToMark.toggleCompleted();
            tasks.removeTask(taskToMark);
            tasks.addTaskByIdx(number, markedTask);
            textBox.chat(String.format("Nice! I've marked this task as done:\n  [%s] %s",
                    markedTask.checkMarked(),markedTask.getName()));
        } else{
            textBox.chat("Hey! This task is already marked!");
        }

    }
}
