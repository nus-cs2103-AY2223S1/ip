package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Manages the adding of new tasks to a specified task list.
 */
public class AddCommand extends Command {

    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command and add new task to the specified task list.
     * Saves and updates the storage to include the new task.
     *
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Ui ui, Storage storage, TaskList taskList) {
        String commandWord = userInput.split(" ", 2)[0];
        Task newTask = null;
        if (commandWord.equals("todo")) {
            newTask = new ToDo();
        } else if (commandWord.equals("event")) {
            newTask = new Event();
        } else if (commandWord.equals("deadline")) {
            newTask = new DeadLine();
        }
        try {
            newTask.addName(userInput);
            String reply = taskList.add(newTask);
            storage.write(taskList.writeTasks());
            return reply;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
