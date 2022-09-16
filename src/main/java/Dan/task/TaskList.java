package dan.task;

import java.util.List;

import dan.exceptions.DanException;
import dan.ui.Ui;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks
     *
     * @param input task details sent in after parsing
     * @throws DanException if the input format is not expected
     */
    public String addTask(String input) throws DanException {
        assert tasks != null : "Tasklist is missing!";
        String description;
        String dateString;
        if (input.startsWith("todo")) {
            description = input.replace("todo", "").strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your todo item");
            }
            tasks.add(new ToDo(description));

        } else if (input.startsWith("deadline")) {
            String[] inputArr = input.replace("deadline", "").strip().split("/by");
            if (inputArr.length != 2) {
                throw new DanException("Please follow the following format:\n deadline <description> /by <due date>");
            }
            description = inputArr[0].strip();
            dateString = inputArr[1].strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your deadline");
            }
            tasks.add(new Deadline(description, dateString));

        } else if (input.startsWith("event")) {
            String[] inputArr = input.replace("event", "").strip().split("/at");
            if (inputArr.length != 2) {
                throw new DanException("Please follow the following format:\n event <description> /at <time/date>");
            }
            description = inputArr[0].strip();
            dateString = inputArr[1].strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your event");
            }
            tasks.add(new Event(description, dateString));
        }
        StringBuilder result = new StringBuilder();
        result.append(Ui.printIndent("Okay okay, I'll add this task then:\n"));
        result.append(Ui.printIndent(tasks.get(tasks.size() - 1).toString())).append("\n");
        result.append(Ui.printIndent(String.format("You now have %d many tasks in your list", tasks.size())));
        return result.toString();
    }

    /**
     * Displays the the current tasks in the list
     *
     * @throws DanException if there are no available tasks
     */
    public String showTasks() throws DanException {
        if (tasks.isEmpty()) {
            throw new DanException("Your list is empty!");
        }
        StringBuilder result = new StringBuilder();
        result.append(Ui.printIndent("Here are the tasks in your list:\n"));
        assert tasks != null && tasks.size() >= 1 : "Something is wrong with the TaskList!";
        result.append(Ui.printIndent(this));
        return result.toString();
    }

    /**
     * Marks the indicated task as completed
     *
     * @param index Task number (as displayed in `showTasks()`) of the task that is completed
     * @throws DanException if the given task number does not exist in the list
     */
    public String markTask(int index) throws DanException {
        if (index > tasks.size()) {
            throw new DanException("This task number doesn't exist!");
        }
        Task task = tasks.get(index - 1);
        task.setDone(true);
        StringBuilder result = new StringBuilder();
        result.append(Ui.printIndent(String.format("Hehe okay guess this is now done\n"
                + "  %s", task)));
        return result.toString();
    }

    /**
     * Marks the indicated task as uncompleted
     *
     * @param index Task number (as displayed in `showTasks()`) of the task that is completed
     * @throws DanException if the given task number does not exist in the list
     */
    public String unmarkTask(int index) throws DanException {
        StringBuilder result = new StringBuilder();
        if (index > tasks.size()) {
            throw new DanException("This task number doesn't exist!");
        }
        Task task = tasks.get(index - 1);
        task.setDone(false);
        result.append(Ui.printIndent(String.format("Ooops, you haven't done this yet? Here ya go:\n"
                + "  %s", task)));
        return result.toString();
    }

    /**
     * Removes the indicated task from the list
     *
     * @param index Task number (as displayed in `showTasks()`) of the task that is completed
     * @throws DanException if the given task number does not exist in the list
     */
    public String deleteTask(int index) throws DanException {
        if (index > tasks.size() && index >= 0) {
            throw new DanException("This task number doesn't exist!");
        }
        StringBuilder result = new StringBuilder();
        result.append(Ui.printIndent("Alright then, I'll remove this task from your list:"));
        result.append(Ui.printIndent(tasks.get(index - 1).toString()));
        tasks.remove(index - 1);
        result.append(Ui.printIndent(String.format("\nYou now have %d many tasks in your list", tasks.size())));
        return result.toString();
    }

    /**
     * Searches the task list for all tasks' descriptions that contains the keyword
     *
     * @param keyword String to be searched for among the tasks in the list
     */
    public String findTask(String keyword) {
        StringBuilder result = new StringBuilder();
        result.append(Ui.printIndent("Alright! Here are the matching tasks in your list:\n"));
        int count = 0;
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            if (task.description.contains(keyword)) {
                result.append(Ui.printIndent(i + "." + task + "\n"));
                count += 1;
            }
        }
        result.append(Ui.printIndent(
                result.toString().length() == 0
                ? "I couldn't find any task that matches your description"
                : String.format("There are a total of %d tasks that matches your keyword %s", count, keyword)));
        return result.toString();
    }

    public List<Task> getTasks() {
        assert tasks != null;
        return tasks;
    }

    /**
     * Displays the list of tasks in a 1-indexed numbered list.
     *
     * @return Text display of the tasks in the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            result.append(i + "." + tasks.get(i - 1) + "\n");
        }
        return result.toString();
    }

}
