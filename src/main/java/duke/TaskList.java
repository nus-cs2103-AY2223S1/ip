package duke;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class handles the manipulation of all tasks, such as marking,
 * adding, deleting.
 */
public class TaskList {
    /** The collection to maintain our tasks. */
    protected ArrayList<Task> inputList;

    /**
     * The class constructor for a TaskList. It is initialized
     * with an empty ArrayList, but it is filled progressively, or
     * loaded from a previous save.
     */
    public TaskList() {
        this.inputList = new ArrayList<>();
    }

    /**
     * Adds a task to the ArrayList. Only used for when loading
     * from a previously saved list.
     *
     * @param task to be added.
     */
    public void add(Task task) {
        assert(task != null);
        this.inputList.add(task);
    }

    /**
     * Checks if the arraylist is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return this.inputList.isEmpty();
    }

    /**
     * Converts the ArrayList to a Task array for easier manipulation, such as
     * outputting its string representation.
     *
     * @return Task[] an array containing Deadlines, Events and Todos.
     */
    public Task[] taskListToArray() {
        return inputList.toArray(new Task[inputList.size()]);
    }

    /**
     * Marks the task as completed via its index. DukeException is thrown
     * if the task is found to be non-existent.
     *
     * @param parts sliced String input.
     * @return String confirming the marking ot a task in the list.
     * @throws DukeException thrown if there is no such task.
     */
    public String markTask(String[] parts) throws DukeException {
        assert(parts.length != 0);
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. mark 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > this.inputList.size()) {
            throw new DukeException("There is no such task!");
        }
        int index = Integer.parseInt(parts[1]);
        Task task = inputList.get(index - 1);
        return task.markAsDone();
    }

    /**
     * Removes the task from the arraylist. DukeException is thrown
     * if the task is found to be non-existent.
     *
     * @param parts sliced String input.
     * @return String confirming the deletion of a task from the list.
     * @throws DukeException thrown if there is no such task.
     */
    public String deleteTask(String[] parts) throws DukeException {
        assert(parts.length != 0);
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. delete 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > inputList.size()) {
            throw new DukeException("There is no such task!");
        }
        int index = Integer.parseInt(parts[1]);
        Task task = inputList.get(index - 1);
        inputList.remove(index - 1);
        return printDeleteTask(task);
    }

    /**
     * Returns the String output for removing a task from the list.
     *
     * @param task that is removed.
     * @return String output detailing task removed and number of tasks remaining.
     */
    private String printDeleteTask(Task task) {
        return String.format("Noted, I've removed this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(),
                inputList.size());
    }

    /**
     * Adds task to arraylist, and formats a String representation
     * that is to be printed along with the task representation and size of list.
     *
     * @param task from the TaskList.
     * @return String confirming the addition of the task into the list.
     */
    public String taskAdd(Task task) {
        assert(task != null);
        this.inputList.add(task);
        return printAddTask(task);
    }

    /**
     * Returns the String output for adding a task to the list.
     *
     * @param task that is added.
     * @return String output detailing task added and number of tasks remaining.
     */
    private String printAddTask(Task task) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(),
                inputList.size());
    }

    /**
     * Abstracts the creation of a todo object, with exception handling.
     *
     * @param input from the Parser.
     * @return String representation of the Todo.
     * @throws DukeException thrown if there is no description.
     */
    public String createTodo(String input) throws DukeException {
        assert(input != null);
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return taskAdd(new Todo(taskType[1]));
    }

    /**
     * Abstracts the creation of a Deadline object, with exception handling.
     *
     * @param input from the Parser.
     * @return String representation of the Deadline.
     * @throws DukeException thrown if there is no description or /by field.
     */
    public String createDeadline(String input) throws DukeException {
        assert(input != null);
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (taskType[1].split(" /by ", 2).length == 1) {
            throw new DukeException("The /by field cannot be empty.");
        }
        String[] taskBy = taskType[1].split(" /by ", 2);
        return taskAdd(new Deadline(taskBy[0], taskBy[1]));
    }

    /**
     * Abstracts the creation of a Event object, with exception handling.
     *
     * @param input from the Parser.
     * @return String representation of the Event.
     * @throws DukeException thrown if there is no description or /at field
     */
    public String createEvent(String input) throws DukeException {
        assert(input != null);
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (taskType[1].split(" /at ", 2).length == 1) {
            throw new DukeException("The /at field cannot be empty.");
        }
        String[] taskBy = taskType[1].split(" /at ", 2);
        return taskAdd(new Event(taskBy[0], taskBy[1]));
    }

    /**
     * Returns a single String with the string representation of all tasks
     * that strictly contains the keyword provided by the user.
     *
     * @param parts sliced String input.
     * @return String output of all tasks found with the given keyword.
     * @throws DukeException thrown if no keyword is given.
     */
    public String findTasks(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please enter a keyword (i.e. find book).");
        }
        return printStrictMatchingTasks(parts);
    }

    /**
     * Returns the String output of all tasks found to be strictly matching with the given input.
     *
     * @param parts sliced String input.
     * @return String output of all tasks found with the given keyword.
     */
    private String printStrictMatchingTasks(String[] parts) {
        ArrayList<Task> match = fillWithStrictMatchingTasks(parts);
        String output = "Here are the matching tasks in your list:\n";

        if (match.size() == 0) {
            return output += "You do not have any tasks matching that keyword.";
        }
        for (int i = 0; i < match.size(); i++) {
            String s = String.format("%s. %s\n", i + 1, match.get(i).toString());
            output += s;
        }
        return output;
    }

    /**
     * Fills an ArrayList with all tasks founds to be strictly matching with the given input.
     *
     * @param parts sliced String input.
     * @return ArrayList with all matching tasks.
     */
    private ArrayList<Task> fillWithStrictMatchingTasks(String[] parts) {
        ArrayList<Task> match = new ArrayList<>();
        inputList.forEach(task -> {
            String[] words = task.getDescription().split(" ", -1);
            if (Arrays.asList(words).contains(parts[1])) {
                match.add(task);
            }
        });
        return match;
    }

    /**
     * Returns a single String with the string representation of all tasks
     * that partially contains the keyword provided by the user.
     *
     * @param parts sliced String input.
     * @return String output of all tasks found with the given keyword.
     * @throws DukeException thrown if no keyword is given.
     */
    public String findPartialMatchingTasks(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please enter a keyword (i.e. find book).");
        }
        return printPartialMatchingTasks(parts);
    }

    /**
     * Returns the String output of all tasks found to be partially matching with the given input.
     *
     * @param parts sliced String input.
     * @return String output of all tasks found with the given keyword.
     */
    private String printPartialMatchingTasks(String[] parts) {
        ArrayList<Task> match = fillWithPartialMatchingTasks(parts);
        String output = "Here are the matching tasks in your list:\n";

        if (match.size() == 0) {
            return output += "You do not have any tasks matching that keyword.";
        }
        for (int i = 0; i < match.size(); i++) {
            String s = String.format("%s. %s\n", i + 1, match.get(i).toString());
            output += s;
        }
        return output;
    }

    /**
     * Fills an ArrayList with all tasks founds to be partially matching with the given input.
     *
     * @param parts sliced String input.
     * @return ArrayList with all matching tasks.
     */
    private ArrayList<Task> fillWithPartialMatchingTasks(String[] parts) {
        ArrayList<Task> match = new ArrayList<>();
        inputList.forEach(task -> {
            if (task.getDescription().contains(parts[1])) {
                match.add(task);
            }
        });
        return match;
    }

    /**
     * Returns a single String with the string representation of all tasks
     * in the users' list concatenated together.
     *
     * @return String output of all tasks contained in the list.
     */
    public String showTasks() {
        String output = "Here are the tasks in your list:\n";
        if (inputList.size() == 0) {
            return output += "You have no tasks.";
        }
        for (int i = 0; i < inputList.size(); i++) {
            String s = String.format("%s. %s\n", i + 1, inputList.get(i).toString());
            output += s;
        }
        return output;
    }

}
