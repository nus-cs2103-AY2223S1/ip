package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * AddEventCommand implements method for adding Event to task list.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class AddEventCommand extends Command {

    private final String input;

    /**
     * Creates new AddEventCommand object.
     *
     * @param input the input string from the user
     * @throws DukeException to handle if the input string is invalid.
     */
    public AddEventCommand(String input) throws DukeException {
        if (!checkValid(input)) {
            throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
        }
        if (!input.contains("/at")) {
            throw new DukeException(" ☹ OOPS!!! Please enter in the format : \n"
                    + "     event {task description} /at {day / date : YYYY-MM-DD / time : no spaces}");
        }
        this.input = input;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param taskList the destination task list for the event to be added
     * @param ui the ui to display message after the task is added
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String taskDesc = input.substring(6, input.indexOf('/') - 1);
        StringBuilder deadline = new StringBuilder(input.substring(input.indexOf('/') + 3));
        String[] deadlineArray = deadline.toString().split(" ");
        if (isDate(deadlineArray[0])) {
            LocalDateTime ld = LocalDateTime.parse(deadlineArray[0]);
            deadline = new StringBuilder(ld.format(DTF) + "  " + ld.getDayOfWeek());
            for (int i = 1; i < deadlineArray.length; i++) {
                deadline.append(" ").append(deadlineArray[i]);
            }
        }
        Event event = new Event(taskDesc, deadline.toString());
        taskList.addTask(event);
        ui.addTaskMessage(event, taskList.getSize());
        storage.store(taskList);
    }

    /**
     * Prevents the program from terminating in Duke.run().
     *
     * @return False as this is not the 'exit' command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
