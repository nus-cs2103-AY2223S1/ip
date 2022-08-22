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
     * The class constructor for a duke.TaskList. It is initialized
     * with an empty ArrayList, but it is filled progressively, or
     * laoded from a previous session.
     */
    public TaskList() {
        this.inputList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the ArrayList. This is only used for when loading
     * from a previously saved list.
     * @param task to be added.
     */
    public void add(Task task) {
        this.inputList.add(task);
    }

    /**
     * Converts the ArrayList to a duke.Task array for easier manipulation, such as
     * outputting its string representation, or writing to the file.
     * @return duke.Task[]
     */
    public Task[] taskListToArray() {
        Task[] inputArray = inputList.toArray(new Task[inputList.size()]);
        return inputArray;
    }

    /**
     * Marks the task as completed via the index of the task on the
     * arraylist. The method throws a duke.DukeException if the
     * task is found to be non-existent.
     * @param parts
     * @throws DukeException thrown if there is no such task.
     */
    public void markTask(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. mark 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > this.inputList.size()) {
            throw new DukeException("There is no such task!");
        } else {
            int index = Integer.parseInt(parts[1]);
            Task task = inputList.get(index - 1);
            System.out.println(task.markAsDone());
        }
    }

    /**
     * Removes the task from the arraylist. The method throws a duke.DukeException if the
     * task is found to be non-existent.
     * @param parts
     * @throws DukeException thrown if there is no such task.
     */
    public void deleteTask(String[] parts) throws DukeException {
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
            System.out.println(output);
        }
    }

    /**
     * Adds the task to arraylist, and formats a String representation
     * that is to be printed along with the task representation and size of list.
     * @param task
     */
    public void taskAdd(Task task) {
        this.inputList.add(task);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(),
                inputList.size());
        System.out.println(output);
    }

    /**
     * Abstracts the creation of a todo object, with exception handling.
     * @param input
     * @return duke.Todo object
     * @throws DukeException thrown if there is no description.
     */
    public void createTodo(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(taskType[1]);
            taskAdd(todo);
        }
    }

    /**
     * Abstracts the creation of a duke.Deadline object, with exception handling.
     * @param input
     * @return duke.Deadline object
     * @throws DukeException thrown if there is no description or /by field.
     */
    public void createDeadline(String input) throws DukeException{
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (taskType[1].split("/by ", 2).length == 1) {
            throw new DukeException("The /by field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split("/by ", 2);
            Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
            taskAdd(deadline);
        }
    }

    /**
     * Abstracts the creation of a duke.Event object, with exception handling.
     * @param input
     * @return duke.Event object
     * @throws DukeException thrown if there is no description or /at field
     */
    public void createEvent(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (taskType[1].split("/at ", 2).length == 1) {
            throw new DukeException("The /at field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split("/at ", 2);
            Event event = new Event(taskBy[0], taskBy[1]);
            taskAdd(event);
        }
    }

}
