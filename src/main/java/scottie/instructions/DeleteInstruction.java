package scottie.instructions;

import java.util.Map;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the delete instruction which is used to
 * delete a task from the task list.
 */
class DeleteInstruction extends Instruction {
    /**
     * Constructs a DeleteInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    DeleteInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Deletes a task from the given TaskList.
     * The main argument of this instruction is interpreted as
     * the index of the Task to delete.
     *
     * @param taskList The TaskList to delete the task from.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (this.hasMainArgument()) {
            int taskId = Integer.parseInt(this.getMainArgument()) - 1;
            Task task = taskList.getTask(taskId);
            ui.showMessages("Ok, I've deleted this task:", task.toString());
            taskList.deleteTask(taskId);
            if (taskList.isEmpty()) {
                ui.showMessages("You have no more tasks left in your list!");
            } else {
                ui.showFormattedMessage("You have %d task(s) left in your list.%n", taskList.size());
            }
        } else {
            ui.showMessages("Sorry, you need to tell me which task to delete.");
        }
    }
}
