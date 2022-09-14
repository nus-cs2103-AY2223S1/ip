package duke;
import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Task;
import duke.TaskList;
public class Ui {
    public Ui() {

    }
    private void lineBreak() {
        System.out.println("\n");
    }

    /**
     * Shows the greeting message
     */
    protected String showGreeting() {
        return "Hello fellow crewmate \nWhat can i do for u today?";
    }

    /**
     * Shows the goodbye message
     */
    protected String showBye() {
        return "Duke was ejected...";
    }

    /**
     * Shows all the task
     * @param taskList the list of task
     */
    protected String showAllTask(TaskList taskList) {
        return String.format("Wow i am so useful\n%s", taskList);
    }

    /**
     * Shows the add task message
     * @param taskList the list of task
     * @param task the task that was added
     */
    protected String showAddTask(TaskList taskList, Task task) {
        return String.format("Oh god you have another task\n%s\nyou have %s tasks now...", task, taskList.length());
    }

    /**
     * Shows the remove task message
     * @param taskList the list of task
     * @param task the task to be removed
     */
    protected String showRemoveTask(TaskList taskList, Task task) {
        return String.format("Thats right tasks are just a concept\n%s\nYou have %s tasks now...", task, taskList.length());
    }

    /**
     * Shows the marked task message
     * @param task the task that was marked
     */

    protected String showMarkTask(Task task) {
        return String.format("Wow so effishun\n%s", task);
    }
    /**
     * Shows the unmarked task message
     * @param task the task that was unmarked
     */
    protected String showUnmarkTask(Task task) {
        return String.format("SUS you didnt finish the task\n%s", task);
    }

    protected String showFindTask(TaskList taskList) {
        return String.format("Scanning all files...\n%s\nWow i am the best", taskList);
    }
}
