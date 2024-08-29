package skyler;

import java.io.IOException;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Contains the task list
 */
public class TaskList {

    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object
     *
     * @param storage Storage object from which to retrieve previous tasks and save new tasks to.
     * @param tasks List of tasks.
     */
    public TaskList(Storage storage, ArrayList<Task> tasks) {
        this.storage = storage;
        this.tasks = tasks;
        assert this.tasks != null : "ArrayList of tasks should be initialised";
    }

    /**
     * Creates a TaskList object
     *
     * @param storage Storage object from which to retrieve previous tasks and save new tasks to.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        assert this.tasks != null : "ArrayList of tasks should be initialised";
    }

    // help() method below inspired by https://github.com/hsiaotingluv/ip
    /**
     * Prints permissible user commands and their corresponding formats (if any)
     *
     * @return User command list.
     */
    public String help() {
        StringBuilder response = new StringBuilder("Try entering one of the following commands...\n");
        response.append("1. todo <task>\n");
        response.append("2. deadline <item> /by <YYYY-MM-DD> <hhmm>\n");
        response.append("3. event <occasion> /at <YYYY-MM-DD> <hhmm>\n");
        response.append("4. reschedule <INDEX> <YYYY-MM-DD> <hhmm>\n");
        response.append("5. mark <INDEX>\n");
        response.append("6. unmark <INDEX>\n");
        response.append("7. delete <INDEX>\n");
        response.append("8. find <keyword>\n");
        response.append("9. list\n");
        response.append("10. help\n");
        response.append("11. bye\n");
        return response.toString();
    }

    /**
     * Prints the current list of tasks
     *
     * @return Description of current tasks.
     */
    public String list() {
        StringBuilder response = new StringBuilder("Tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String str = String.format("%d.%s\n", i + 1, tasks.get(i));
            response.append(str);
        }
        return response.toString();
    }

    /**
     * Marks a task as done
     *
     * @param itemID Index of task.
     * @return Description of the task marked as completed.
     */
    public String mark(int itemID) {
        Task currTask = tasks.get(itemID - 1);
        currTask.markAsDone();
        StringBuilder response = new StringBuilder("You have completed this task:\n");
        String completedTask = String.format("  %s", currTask);
        response.append(completedTask);

        try {
            // task list changes
            storage.saveTask(tasks);
            return response.toString();
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    /**
     * Marks a task as not done
     *
     * @param itemID Index of task.
     * @return Description of the task marked as not completed.
     */
    public String unmark(int itemID) {
        Task currTask = tasks.get(itemID - 1);
        currTask.markAsNotDone();
        StringBuilder response = new StringBuilder("OK, I've marked this task as not done yet:\n");
        String uncompletedTask = String.format("  %s", currTask);
        response.append(uncompletedTask);

        try {
            // task list changes
            storage.saveTask(tasks);
            return response.toString();
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    /**
     * Adds a new todo task to the list
     *
     * @param desc Task description.
     * @return Description of task added and task summary.
     */
    public String addTodo(String desc) {
        Todo newTodo = new Todo(desc);
        tasks.add(newTodo);

        try {
            // task list changes
            storage.saveTask(tasks);
            return printTask(newTodo, tasks.size());
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    /**
     * Adds a new deadline to the list
     *
     * @param descWithDate Task description with date.
     * @return Description of task added and task summary.
     */
    public String addDeadline(String descWithDate) {
        String[] deadlineComponents = descWithDate.split(" /by ", 2);

        // process date and time
        LocalDateTime dt = formatDateTime(deadlineComponents[1]);

        Deadline newDeadline = new Deadline(deadlineComponents[0], dt);
        tasks.add(newDeadline);

        try {
            // task list changes
            storage.saveTask(tasks);
            return printTask(newDeadline, tasks.size());
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    /**
     * Adds a new event to the list
     *
     * @param descWithDate Task description with date.
     * @return Description of task added and task summary.
     */
    public String addEvent(String descWithDate) {
        String[] eventComponents = descWithDate.split(" /at ", 2);

        // process date and time
        LocalDateTime dt = formatDateTime(eventComponents[1]);

        Event newEvent = new Event(eventComponents[0], dt);
        tasks.add(newEvent);

        try {
            // task list changes
            storage.saveTask(tasks);
            return printTask(newEvent, tasks.size());
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    /**
     * Deletes task from the list
     *
     * @param itemID Index of task.
     * @return Description of task deleted and task summary.
     */
    public String delete(int itemID) {
        Task currTask = tasks.get(itemID - 1);

        StringBuilder response = new StringBuilder("The following task will be removed:\n");
        String deletedTask = String.format("  %s\n", currTask);
        response.append(deletedTask);

        tasks.remove(itemID - 1);

        String summary = String.format("Total number of tasks: %d", tasks.size());
        response.append(summary);

        try {
            // task list changes
            storage.saveTask(tasks);
            return response.toString();
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    public String rescheduleTask(int itemID, String dateTime) {
        Task currTask = tasks.get(itemID - 1);
        LocalDateTime dt = formatDateTime(dateTime);

        currTask.changeDateTime(dt);

        StringBuilder response = new StringBuilder("The following task has been rescheduled:\n");
        String rescheduledTask = String.format("  %s\n", currTask);
        response.append(rescheduledTask);

        try {
            // task list changes
            storage.saveTask(tasks);
            return response.toString();
        } catch (IOException ie) {
            return ie.getMessage();
        }
    }

    /**
     * Prints tasks containing the given keyword
     *
     * @param keyword Keyword to search for.
     * @return List of tasks that contain the keyword.
     */
    public String findTask(String keyword) {
        ArrayList<Integer> taskIndices = storage.findTask(keyword);
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= taskIndices.size(); i++) {
            int index = taskIndices.get(i - 1);
            Task currTask = tasks.get(index);
            String task = String.format("%d.%s\n", i, currTask.toString());
            response.append(task);
        }
        return response.toString();
    }

    /**
     * Prints action taken and task summary
     *
     * @param task The task added.
     * @param num Current total number of tasks.
     * @return Description of task added and task summary.
     */
    public static String printTask(Task task, int num) {
        StringBuilder response = new StringBuilder("I've added the following task:\n");
        String addedTask = String.format("  %s\n", task);
        response.append(addedTask);
        String summary = String.format("Total number of tasks: %d", num);
        response.append(summary);
        return response.toString();
    }

    /**
     * Formats date and time information and returns a LocalDateTime object
     *
     * @param strDateTime String representation of date and time.
     * @return Corresponding LocalDateTime object.
     */
    public static LocalDateTime formatDateTime(String strDateTime) {
        String[] timeInfo = strDateTime.split(" ", 2);

        String unformattedTime = timeInfo[1];
        String hour = unformattedTime.substring(0, 2);
        String minute = unformattedTime.substring(2, 4);
        String formattedTime = String.format("%s:%s", hour, minute);

        LocalDate date = LocalDate.parse(timeInfo[0]);
        LocalTime time = LocalTime.parse(formattedTime);
        return LocalDateTime.of(date, time);
    }
}
