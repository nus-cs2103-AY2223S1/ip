import java.util.ArrayList;

/**
 * A class to represent the commands that the users can use.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Commands {
    /** Ui that prints statements for the bot. */
    protected static Ui ui = new Ui();

    /**
     * Marks a task as done.
     *
     * @param command The command that is inputted by the user.
     * @param taskList The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the Duke bot, thrown when
     * the user does not input a number, or inputs invalid characters after the
     * 'mark' command.
     */
    public static void mark(String command, ArrayList<Task> taskList) throws DukeException{
        try {
            String number = command.split(" ")[1];
            int num = Integer.parseInt(number);
            Task task = taskList.get(num - 1);
            task.markAsDone();
            ui.mark(task);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The number you are talking about does not exist. " +
                    "\nPerhaps it is not a number at all? Please check again!");
        }
    }

    /**
     * Unmarks a task as undone.
     *
     * @param command The command that is inputted by the user.
     * @param taskList The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the Duke bot, thrown when
     * the user does not input a number, or inputs invalid characters after the
     * 'unmark' command.
     */
    public static void unmark(String command, ArrayList<Task> taskList) throws DukeException{
        try {
            String number = command.split(" ")[1];
            int num = Integer.parseInt(number);
            Task task = taskList.get(num - 1);
            task.unmarkAsDone();
            ui.unmark(task);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The number you are talking about does not exist." +
                    "\nPerhaps it is not a number at all? Please check again!");
        }
    }

    /**
     * Adds a task with a deadline.
     *
     * @param command The command that is inputted by the user.
     * @param taskList The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the Duke bot, thrown when
     * the user does not include a proper description of the task.
     */
    public static void deadline(String command, ArrayList<Task> taskList) throws DukeException {
        try {
            String taskName = command.substring(command.indexOf(" ") + 1, command.indexOf("/") - 1);
            String by = command.substring(command.indexOf("/") + 5);
            Task newTask = new Deadline(taskName, by);
            taskList.add(newTask);
            int amountOfTasks = taskList.size();
            ui.addTask(newTask, amountOfTasks);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds a task that is classified as an event.
     *
     * @param command The command that is inputted by the user.
     * @param taskList The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the Duke bot, thrown when
     * the user does not include a proper description of the task.
     */
    public static void event(String command, ArrayList<Task> taskList) throws DukeException {
        try {
            String taskName = command.substring(command.indexOf(" ") + 1, command.indexOf("/") - 1);
            String at = command.substring(command.indexOf("/") + 5);
            Task newTask = new Event(taskName, at);
            taskList.add(newTask);
            int amountOfTasks = taskList.size();
            ui.addTask(newTask, amountOfTasks);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Function to add a task with that has to be done soon, without a description
     * of the specific place or time.
     *
     * @param command The command that is inputted by the user.
     * @param taskList The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the Duke bot, thrown when
     * the user does not include a proper description of the task.
     */
    public static void toDo(String command, ArrayList<Task> taskList) throws DukeException {
        try {
            if (command.split(" ")[1] != null) {
                String taskName = command.substring(command.indexOf(" ") + 1);
                Task newTask = new ToDo(taskName);
                taskList.add(newTask);
                int amountOfTasks = taskList.size();
                ui.addTask(newTask, amountOfTasks);
            }
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Please specify what you want to do!");
        }
    }

    /**
     * Function to delete any task that has been inputted.
     *
     * @param command The command that is inputted by the user.
     * @param taskList The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the Duke bot, thrown when
     * the user does not include a proper description of the task.
     */
    public static void delete(String command, ArrayList<Task> taskList) throws DukeException {
        try {
            String number = command.split(" ")[1];
            int num = Integer.parseInt(number);
            Task task = taskList.get(num - 1);
            taskList.remove(num - 1);
            int amountOfTasksLeft = taskList.size();
            ui.delete(task, amountOfTasksLeft);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Looks like the task you're looking for does not exist :-(");
        }
    }

}
