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
     * Processes the input and adds a To-Do task to the list of tasks
     *
     * @param input task details sent in after parsing
     * @throws DanException if the input format is not expected
     */
    public String addToDoTask(String input) throws DanException {
        String description = input.replace("todo", "").strip();
        if (input.isEmpty()) {
            throw DanException.missingDescriptionError("todo task");
        }
        return this.addTask(new ToDo(description));
    }

    /**
     * Processes the input and adds a Deadline task to the list of tasks
     *
     * @param input task details sent in after parsing
     * @throws DanException if the input format is not expected
     */
    public String addDeadlineTask(String input) throws DanException {
        String[] inputArr = input.replace("deadline", "").strip().split("/by");
        if (inputArr.length != 2) {
            throw DanException.incorrectFormatError("deadline <description> /by <datetime>");
        }
        String description = inputArr[0].strip();
        String dateString = inputArr[1].strip();
        if (description.isEmpty()) {
            throw DanException.missingDescriptionError("deadline");
        }
        return this.addTask(new Deadline(description, dateString));
    }

    /**
     * Processes the input and adds an Event task to the list of tasks
     *
     * @param input task details sent in after parsing
     * @throws DanException if the input format is not expected
     */
    public String addEventTask(String input) throws DanException {
        String[] inputArr = input.replace("event", "").strip().split("/at");
        if (inputArr.length != 2) {
            throw DanException.incorrectFormatError("event <description> /at <datetime>");
        }
        String description = inputArr[0].strip();
        String dateString = inputArr[1].strip();
        if (description.isEmpty()) {
            throw DanException.missingDescriptionError("event");
        }
        return this.addTask(new Event(description, dateString));
    }

    /**
     * Displays the the current tasks in the list
     *
     * @throws DanException if there are no available tasks
     */
    public String showTasks() throws DanException {
        if (tasks.isEmpty()) {
            throw DanException.emptyTaskListError();
        }
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");
        assert tasks != null && tasks.size() >= 1 : "Something is wrong with the TaskList!";
        result.append(this);
        return Ui.printIndent(result.toString());
    }

    /**
     * Marks the indicated task as completed
     *
     * @param indexes Task number (as displayed in `showTasks()`) of the task that is completed
     * @throws DanException if the given task number does not exist in the list
     */
    public String markTask(int... indexes) throws DanException {
        StringBuilder result = new StringBuilder();
        result.append("Good Job! Lets keep it going\n");
        for (int index : indexes) {
            if (indexIsValid(index)) {
                throw DanException.taskNotFoundError();
            }
            Task task = tasks.get(index - 1);
            task.setDone(true);
            result.append(task).append("\n");
        }
        return Ui.printIndent(result.toString());
    }

    /**
     * Marks the indicated task as uncompleted
     *
     * @param indexes Task number (as displayed in `showTasks()`) of the task that is completed
     * @throws DanException if the given task number does not exist in the list
     */
    public String unmarkTask(int... indexes) throws DanException {
        StringBuilder result = new StringBuilder();
        result.append("\"Ooops, did I mark them wrongly? Here ya go:\n");
        for (int index : indexes) {
            if (indexIsValid(index)) {
                throw DanException.taskNotFoundError();
            }
            Task task = tasks.get(index - 1);
            task.setDone(false);
            result.append(task).append("\n");
        }
        return Ui.printIndent(result.toString());
    }

    /**
     * Removes the indicated task from the list
     *
     * @param indexes Task number (as displayed in `showTasks()`) of the task that is completed
     * @throws DanException if the given task number does not exist in the list
     */
    public String deleteTask(int... indexes) throws DanException {
        StringBuilder result = new StringBuilder();
        result.append("Alright then, I'm removing these from your list:\n");
        for (int index : indexes) {
            if (indexIsValid(index)) {
                throw DanException.taskNotFoundError();
            }
            Task task = tasks.get(index - 1);
            result.append(task);
            tasks.remove(index - 1);
        }
        result.append(String.format("\nYou now have %d many tasks in your list", tasks.size()));
        return Ui.printIndent(result.toString());
    }

    /**
     * Searches the task list for all tasks' descriptions that contains the keyword
     *
     * @param keywords String to be searched for among the tasks in the list
     */
    public String findTask(String... keywords) {
        StringBuilder result = new StringBuilder();
        result.append("Alright! Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            if (task.hasKeyword(keywords)) {
                result.append(String.format("%d.%s\n", i, task));
                count += 1;
            }
        }
        result.append(
                result.toString().length() == 0
                ? "I couldn't find any task that matches your description"
                : String.format("There are a total of %d tasks that matches your keywords", count));
        return Ui.printIndent(result.toString());
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
            result.append(String.format("%d.%s\n", i, tasks.get(i - 1)));
        }
        return result.toString();
    }

    private boolean indexIsValid(int index) {
        return (index > tasks.size() || index < 0);
    }

    private String addTask(Task task) {
        assert tasks != null : "Tasklist is missing!";
        tasks.add(task);
        StringBuilder result = new StringBuilder();
        result.append(("Okay okay, I'll add this task then:\n"))
                .append(task.toString()).append("\n")
                .append(String.format("You now have %d many tasks in your list", tasks.size()));
        return Ui.printIndent(result.toString());
    }
}
