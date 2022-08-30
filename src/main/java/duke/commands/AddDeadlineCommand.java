package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

    private final String input;

    public AddDeadlineCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        if (!input.contains("/by")) throw new DukeException(" ☹ OOPS!!! Please enter in the format : \n" +
                "     deadline {task description} /by {day / date : YYYY-MM-DD / time}");
        this.input = input;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param taskList the destination task list for the deadline to be added
     * @param ui the ui to display message after the task is added
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String taskDesc = input.substring(9, input.indexOf('/') - 1);
        StringBuilder date = new StringBuilder(input.substring(input.indexOf('/') + 3));
        String[] dateArray = date.toString().split(" ");
        if (isDate(dateArray[0])) {
            LocalDateTime ld = LocalDateTime.parse(dateArray[0]);
            date = new StringBuilder(ld.format(DTF) + "  " + ld.getDayOfWeek());
            for (int i = 1; i < dateArray.length; i++) {
                date.append(" ").append(dateArray[i]);
            }
        }
        Deadline deadline = new Deadline(taskDesc, date.toString());
        taskList.addTask(deadline);
        ui.addTaskMessage(deadline, taskList.getSize());
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
