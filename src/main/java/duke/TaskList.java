package duke;

import java.util.ArrayList;

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
        this.inputList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the ArrayList. Only used for when loading
     * from a previously saved list.
     *
     * @param task to be added.
     */
    public void add(Task task) {
        this.inputList.add(task);
    }

    /**
     * Converts the ArrayList to a Task array for easier manipulation, such as
     * outputting its string representation.
     *
     * @return Task[] an array containing Deadlines, Events and Todos.
     */
    public Task[] taskListToArray() {
        Task[] inputArray = inputList.toArray(new Task[inputList.size()]);
        return inputArray;
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
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. mark 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > this.inputList.size()) {
            throw new DukeException("There is no such task!");
        } else {
            int index = Integer.parseInt(parts[1]);
            Task task = inputList.get(index - 1);
            return task.markAsDone();
        }
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
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. delete 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > inputList.size()) {
            throw new DukeException("There is no such task!");
        } else {
            int index = Integer.parseInt(parts[1]);
            Task task = inputList.get(index - 1);
            inputList.remove(index - 1);
            String output = String.format("Noted, I've removed this task:\n%s\nNow you have %s tasks in the list.",
                    task.toString(),
                    inputList.size());
            return output;
        }
    }

    /**
     * Adds task to arraylist, and formats a String representation
     * that is to be printed along with the task representation and size of list.
     *
     * @param task from the TaskList.
     * @return String confirming the addition of the task into the list.
     */
    public String taskAdd(Task task) {
        this.inputList.add(task);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(),
                inputList.size());
        return output;
    }

    /**
     * Abstracts the creation of a todo object, with exception handling.
     *
     * @param input from the Parser.
     * @return String representation of the Todo.
     * @throws DukeException thrown if there is no description.
     */
    public String createTodo(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(taskType[1]);
            return taskAdd(todo);
        }
    }

    /**
     * Abstracts the creation of a Deadline object, with exception handling.
     *
     * @param input from the Parser.
     * @return String representation of the Deadline.
     * @throws DukeException thrown if there is no description or /by field.
     */
    public String createDeadline(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (taskType[1].split(" /by ", 2).length == 1) {
            throw new DukeException("The /by field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split(" /by ", 2);
            Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
            return taskAdd(deadline);
        }
    }

    /**
     * Abstracts the creation of a Event object, with exception handling.
     *
     * @param input from the Parser.
     * @return String representation of the Event.
     * @throws DukeException thrown if there is no description or /at field
     */
    public String createEvent(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (taskType[1].split(" /at ", 2).length == 1) {
            throw new DukeException("The /at field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split(" /at ", 2);
            Event event = new Event(taskBy[0], taskBy[1]);
            return taskAdd(event);
        }
    }

    /**
     * Returns a single String with the string representation of all tasks
     * that contains the keyword provided by the user.
     *
     * @param parts sliced String input.
     * @return String output of all tasks found with the given keyword.
     * @throws DukeException thrown if no keyword is given.
     */
    public String findTasks(String[] parts) throws DukeException {
        String output = "Here are the matching tasks in your list:\n";
        if (parts.length == 1) {
            throw new DukeException("Please enter a keyword (i.e. find book).");
        } else {
            ArrayList<Task> match = new ArrayList<Task>();
            inputList.forEach(task -> {
                if (task.getDescription().contains(parts[1])) {
                    match.add(task);
                }
            });
            if (match.size() != 0) {
                for (int i = 0; i < match.size(); i++) {
                    String s = String.format("%s. %s\n", i + 1, match.get(i).toString());
                    output += s;
                }
            } else {
                output += "You do not have any tasks matching that keyword.";
            }
        }
        return output;
    }

    /**
     * Returns a single String with the string representation of all tasks
     * in the users' list concatenated together.
     *
     * @return String output of all tasks contained in the list.
     */
    public String showTasks() {
        String output = "Here are the tasks in your list:\n";
        if (inputList.size() != 0) {
            for (int i = 0; i < inputList.size(); i++) {
                String s = String.format("%s. %s\n", i + 1, inputList.get(i).toString());
                output += s;
            }
        } else {
            output += "You have no tasks.";
        }
        return output;
    }

}
