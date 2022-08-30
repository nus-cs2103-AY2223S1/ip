package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Mark or Unmark Task in TaskList depending on the relevant index given.
 */
public class MarkCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final int markIndex;
    public final String command;

    /**
     * Constructs a MarkCommand instance with the indication of Command of either
     * Mark or Unmark and the corresponding index of Task to be executed on.
     *
     * @param command mark or unmark.
     * @param markIndex the index of the Task to be marked or unmarked.
     */
    public MarkCommand(String command, int markIndex) {
        this.markIndex = markIndex;
        this.command = command;
    }

    /**
     * Decide whether to mark or unmark Task from the taskList, then modify the TaskList.
     *
     * @param taskList the TaskList where a Task to be marked or unmarked.
     * @param ui the Ui provides method to convert Task to String in a representable format.
     * @param storage the Storage to write modified Task into file.
     * @throws ArrayIndexOutOfBoundsException if the markIndex is not provided by the user.
     * @throws IndexOutOfBoundsException if the markIndex exceeded the current existing number of Tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (command.equals("mark")) {
                Task markedTask = taskList.getList().get(this.markIndex).mark();
                taskList.getList().set(this.markIndex, markedTask);

                String list = "";
                for (Task t : taskList.getList()) {
                    list += t.toString();
                }
                storage.write(list);

                return "Nice! I've marked this task as done:\n" + ui.beautyWrapTask(markedTask) + "\n";

            } else {
                Task unmarkedTask = taskList.getList().get(this.markIndex).unmark();
                taskList.getList().set(this.markIndex, unmarkedTask);

                String list = "";
                for (Task t : taskList.getList()) {
                    list += t.toString();
                }
                storage.write(list);

                return "OK, I've marked this task as not done yet:\n"
                        + ui.beautyWrapTask(unmarkedTask) + "\n";
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return "☹ OOPS!!! You did not specify which task to be marked/unmarked.\n";
        } catch (IndexOutOfBoundsException ex) {
            return "☹ OOPS!!! Your list only has " + taskList.getList().size() + " tasks.\n";
        }
    }

    /**
     * Returns false as Mark is not a terminating Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }
}
