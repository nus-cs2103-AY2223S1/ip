package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

/**
 * Represents UI of application
 */
public class CommandOutputs {

    /**
     * Returns String of a welcome message for the user.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\n" + "Peter's personal chatbot";
    }

    /**
     * Returns String of a goodbye message for the user.
     */
    public String showGoodbye() {
        return "Bye Bye See You Next Time!";
    }

    /**
     * Returns String of list of the tasks in Duke.
     *
     * @param taskList list of tasks.
     * @throws DukeException thrown if list is empty.
     */
    public String showListOut(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            output = output + String.format("%d.%s\n", i + 1, taskList.get(i).toString());
        }
        return output;
    }

    /**
     * Returns String of deleted task and updated number of tasks.
     *
     * @param taskList list of tasks.
     * @param deletedTask deleted task
     */
    public String showDelete(TaskList taskList, Task deletedTask) {
        String output = String.format("Noted. I've removed this task:\n%s\n", deletedTask.toString());
        return taskList.size() == 1 ? output + "Now you have 1 task in the list\n"
                                    : output + String.format("Now you have %d tasks in the list\n", taskList.size());
    }

    /**
     * Returns String of added task and updated number of tasks.
     *
     * @param taskList list of tasks.
     * @param newTask new task added.
     */
    public String showAdd(TaskList taskList, Task newTask) {
        String output = String.format("Nice! Added this task:\n%s\n", newTask.toString());
        return taskList.size() == 1 ? output + "Now you have 1 task in the list\n"
                : output + String.format("Now you have %d tasks in the list\n", taskList.size());
    }

    /**
     * Returns String of marked task
     *
     * @param taskList list of tasks.
     * @param index index of marked task in list of tasks.
     */
    public String showMark(TaskList taskList, int index) {
        return String.format("Weeeee! I've marked this task as done:\n%s\n", taskList.get(index).toString());
    }

    /**
     * Returns String of unmarked task.
     *
     * @param taskList list of tasks.
     * @param index index of unmarked task in list of tasks.
     */
    public String showUnmark(TaskList taskList, int index) {
        return String.format("Aw Mans... I've unmarked this task:\n%s\n", taskList.get(index).toString());
    }

    /**
     * Returns String of list of tasks that contains the inputted keyword.
     *
     * @param taskList list of tasks that contains the inputted keyword.
     * @throws DukeException if no tasks contains the keyword.
     */
    public String showKeywordList(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list that contains the keyword");
        }
        String output = "Here are matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            output = output + String.format("%d.%s\n", i + 1, taskList.get(i).toString());
        }
        return output;
    }
}
