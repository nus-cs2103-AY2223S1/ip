package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    List<Task> tasks;
    final Storage storage = new Storage();

    Duke() {
        tasks = storage.readDataFile();
    }

    /**
     * Marks a task as complete or incomplete.
     * @param input user command
     * @param done whether task is completed or not
     * @return message
     * @throws DukeException
     */
    private String markTask(String input, boolean done) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task");
        }
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.get(taskIdx - 1);
            if (done) {
                task.mark();
            } else {
                task.unmark();
            }
            String successMessage = done
                    ? "Nice! I've marked this task as done:\n  %s\n"
                    : "OK, I've marked this task as not done yet:\n  %s\n";
            storage.syncTasksToFile(tasks);
            return String.format(successMessage, task);
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    /**
     * Deletes a task.
     * @param input user command
     * @return message
     * @throws DukeException
     */
    private String deleteTask(String input) throws DukeException {
        String[] splitInput = input.split("delete ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task to delete");
        }
        assert splitInput.length >= 2;
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.remove(taskIdx - 1);
            storage.syncTasksToFile(tasks);
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n",
                    task.toString(), tasks.size());
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    /**
     * Prints list of tasks.
     * @return message containing list of tasks
     */
    private String printTasks() {
        if (tasks.size() == 0) {
            return "You currently have no tasks. ";
        } else {
            ArrayList<String> result = new ArrayList<>();
            result.add("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                result.add(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            return String.join("\n", result);
        }
    }

    /**
     * Process user {@code input} and returns Duke's response.
     * @param input user command
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            if (input.equals("list")) {
                return printTasks();
            } else if (input.startsWith("mark ")) {
                return markTask(input, true);
            } else if (input.startsWith("unmark ")) {
                return markTask(input, false);
            } else if (input.startsWith("delete ")) {
                return deleteTask(input);
            } else if (input.startsWith(TaskType.TODO.command)) {
                return addTask(input, TaskType.TODO);
            } else if (input.startsWith(TaskType.DEADLINE.command)) {
                return addTask(input, TaskType.DEADLINE);
            } else if (input.startsWith(TaskType.EVENT.command)) {
                return addTask(input, TaskType.EVENT);
            } else if (input.startsWith("find ")) {
                return searchTask(input);
            } else {
                throw new DukeException("what");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    /**
     * Adds a task to the list.
     * @param input user command
     * @param type type of task
     * @return message
     * @throws DukeException
     */
    private String addTask(String input, TaskType type) throws DukeException {
        String[] splitInput = input.split(type.command);
        String errorMessage = type == TaskType.TODO
                ? "Please add a description for the %s"
                : "Please add a description and date for the %s";
        if (splitInput.length < 2) {
            throw new DukeException(String.format(errorMessage, type.name));
        }
        Task task;
        if (type == TaskType.TODO) {
            String desc = splitInput[1];
            task = new Todo(desc);
        } else {
            splitInput = splitInput[1].split(type == TaskType.DEADLINE ? " /by " : " /at ");
            if (splitInput.length < 2) {
                throw new DukeException(String.format(errorMessage, type.name));
            }
            LocalDate date = LocalDate.parse(splitInput[1]);
            task = type == TaskType.DEADLINE
                    ? new Deadline(splitInput[0], date)
                    : new Event(splitInput[0], date);
        }
        tasks.add(task);
        storage.syncTasksToFile(tasks);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                task, tasks.size());
    }

    /**
     * Searches for tasks that match a given search string.
     * @param input user command
     * @return message containing list of matching tasks
     */
    private String searchTask(String input) {
        String searchString = input.split("find ")[1];
        ArrayList<String> result = new ArrayList<>();
        result.add("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.description.contains(searchString)) {
                result.add(task.toString());
            }
        }
        return String.join("\n", result);
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
