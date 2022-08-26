package Sakura;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents a list for all tasks similar to array lists.
 */
public class TaskList {
    public final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for TaskList.
     *
     * @param tasks tasks varargs of tasks to be added to the list
     */
    public TaskList(Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
    }

    /**
     * Obtain the number of items in the list.
     *
     * @return the length of the list of tasks.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Obtain the task in the list at the specific index.
     *
     * @param taskIndex the index of the target task.
     * @return the target task found at the specific index.
     */
    public Task get(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    /**
     * Adds a task to the task list via the addDescrpition method.
     *
     * @param input string command given by user.
     */
    public void addTask(String input) {
        try {
            if (input.toLowerCase().startsWith("todo")) {
                if (input.split(" ").length == 1) {
                    SakuraException.invalidTodo();
                } else {
                    Todo todo = new Todo(input.substring("todo ".length()));
                    Ui.addDescription(this.tasks, todo);
                }
            } else if (input.toLowerCase().startsWith("deadline")) {
                try {
                    String[] strArr = input.split(" /by ", 2);
                    Deadline deadline = new Deadline(strArr[0].substring("deadline ".length()), strArr[1]);
                    Ui.addDescription(this.tasks, deadline);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                    SakuraException.invalidDeadline();
                }
            } else if (input.toLowerCase().startsWith("event")) {
                try {
                    String[] strArr = input.split(" /at ", 2);
                    Event event  = new Event(strArr[0].substring("event ".length()), strArr[1]);
                    Ui.addDescription(this.tasks, event);
                } catch (ArrayIndexOutOfBoundsException  | StringIndexOutOfBoundsException e) {
                    SakuraException.invalidEvent();
                }
            }
        } catch (DateTimeParseException e) {
            SakuraException.dateError();
        }

    }

    /**
     * Marks a task in the task list as completed.
     *
     * @param input string command given by user.
     */
    public void markTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                SakuraException.invalidMark();
            } else {
                int taskIndex = Integer.parseInt(input.substring("mark ".length()));
                Task task = tasks.get(taskIndex - 1);
                task.markDone();
                System.out.println("\tAlright solid work! You've completed this task: \n\t  " + task);
            }
        } catch (IndexOutOfBoundsException e) {
            SakuraException.noSuchTask();
        }
    }

    /**
     * Unmarks a task in the task list.
     *
     * @param input string command given by user.
     */
    public void unmarkTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                SakuraException.invalidMark();
            } else {
                int taskIndex = Integer.parseInt(input.substring("unmark ".length()));
                Task task = tasks.get(taskIndex - 1);
                task.markUndone();
                System.out.println("\tHey this is not done yet? Remember to finish it soon: \n\t  " + task);
            }
        } catch (IndexOutOfBoundsException e) {
            SakuraException.noSuchTask();
        }
    }

    /**
     * Deletes a task in task list.
     *
     * @param input string command given by user.
     */
    public void deleteTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                SakuraException.invalidCommand();
            } else {
                int taskIndex = Integer.parseInt(input.substring("delete ".length()));
                Task task = tasks.get(taskIndex - 1);
                tasks.remove(task);
                Ui.deleteDescription(this.tasks, task);
            }
        } catch (IndexOutOfBoundsException e) {
            SakuraException.noSuchTask();
        }
    }

}
