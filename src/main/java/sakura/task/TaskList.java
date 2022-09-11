package sakura.task;
import sakura.SakuraException;
import sakura.Ui;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

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
    public String addTask(String input) {
        try {
            if (input.toLowerCase().startsWith("todo")) {
                if (input.split(" ").length == 1) {
                    return SakuraException.invalidTodo();
                } else {
                    Todo todo = new Todo(input.substring("todo ".length()));
                    return Ui.addDescription(this.tasks, todo);
                }
            } else if (input.toLowerCase().startsWith("deadline")) {
                try {
                    String[] strArr = input.split(" /by ", 2);
                    Deadline deadline = new Deadline(strArr[0].substring("deadline ".length()), strArr[1]);
                    return Ui.addDescription(this.tasks, deadline);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                    return SakuraException.invalidDeadline();
                }
            } else if (input.toLowerCase().startsWith("event")) {
                try {
                    String[] strArr = input.split(" /at ", 2);
                    Event event  = new Event(strArr[0].substring("event ".length()), strArr[1]);
                    return Ui.addDescription(this.tasks, event);
                } catch (ArrayIndexOutOfBoundsException  | StringIndexOutOfBoundsException e) {
                    return SakuraException.invalidEvent();
                }
            } else {
                return SakuraException.genericTask();
            }
        } catch (DateTimeParseException e) {
            return SakuraException.dateError();
        }
    }

    /**
     * Marks a task in the task list as completed.
     *
     * @param input string command given by user.
     */
    public String markTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                return SakuraException.invalidMark();
            } else {
                int taskIndex = Integer.parseInt(input.substring("mark ".length()));
                Task task = tasks.get(taskIndex - 1);
                task.markDone();
                return "\tAlright solid work! You've completed this task: \n\t  " + task;
            }
        } catch (IndexOutOfBoundsException e) {
            return SakuraException.noSuchTask();
        }
    }

    /**
     * Un-marks a task in the task list.
     *
     * @param input string command given by user.
     */
    public String unmarkTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                return SakuraException.invalidMark();
            } else {
                int taskIndex = Integer.parseInt(input.substring("unmark ".length()));
                Task task = tasks.get(taskIndex - 1);
                task.markUndone();
                return "\tHey this is not done yet? Remember to finish it soon: \n\t  " + task;
            }
        } catch (IndexOutOfBoundsException e) {
            return SakuraException.noSuchTask();
        }
    }

    /**
     * Deletes a task in task list.
     *
     * @param input string command given by user.
     */
    public String deleteTask(String input) {
        try {
            if (input.split(" ").length != 2) {
                return SakuraException.invalidCommand();
            } else {
                int taskIndex = Integer.parseInt(input.substring("delete ".length()));
                Task task = tasks.get(taskIndex - 1);
                int initialSize = tasks.size();
                tasks.remove(task);
                assert tasks.size() == initialSize - 1 : "Size of tasks list is incorrect after deleting task.";
                return Ui.deleteDescription(this.tasks, task);
            }
        } catch (IndexOutOfBoundsException e) {
            return SakuraException.noSuchTask();
        }
    }

    public String searchTask(String input) {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length != 2) {
            return SakuraException.invalidCommand();
        } else {
            String keyword = inputSplit[1];
            ArrayList<Task> matchList = new ArrayList<>();
            for (Task task : this.tasks) {
                if(task.getDescription().contains(keyword)) {
                    matchList.add(task);
                }
            }
            return Ui.searchTaskDescription(matchList);
        }
    }

    public String sortTask() {
        Collections.sort(tasks);
        return Ui.showSortedTasks(tasks);
    }

}
