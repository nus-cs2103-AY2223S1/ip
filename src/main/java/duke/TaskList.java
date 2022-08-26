package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles logic dealing with the task list.
 */
public class TaskList {
    /* Contains the tasks inputted. */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Handles the addition of tasks.
     *
     * @param type        type of task added.
     * @param description description of the task.
     * @throws DukeException, IOException for invalid inputs.
     */
    public void addTask(Command type, String description) throws DukeException, IOException {
        Task task;
        if (type == Command.TODO) {
            task = new ToDo(description);
        } else if (type == Command.DEADLINE) {
            task = Deadline.createDeadline(description);
        } else {
            task = Event.createEvent(description);
        }
        UI.printAddTaskMessage(task);
        tasks.add(task);
        Storage.Save(tasks, false);
    }

    /**
     * Handles Printing of the task list.
     */
    public void printTaskList() {
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i - 1).toString());
            }
            System.out.println("");
        } else {
            System.out.println("No tasks have been added!\n");
        }
    }

    /**
     * Marks/Unmarks Tasks.
     *
     * @param markStatus       input to mark/unmark a task.
     * @param userInstructions input of the user.
     */
    public void taskMarker(Command markStatus, String userInstructions) {
        try {
            int taskNo = Integer.parseInt(userInstructions);
            Task currTask = tasks.get(taskNo - 1);
            if (markStatus == Command.MARK) {
                currTask.markDone();
                System.out.println("Task successfully marked!");
            } else {
                currTask.markUndone();
                System.out.println("Task successfully unmarked!");
            }
            System.out.println(currTask + "\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input, please input an available integer index!\n");
        }
    }

    /**
     * Deletes the task with the specified index.
     *
     * @param inputIndex index to be deleted.
     */
    public void deleteTask(String inputIndex) {
        try {
            int taskNo = Integer.parseInt(inputIndex);
            Task task = tasks.get(taskNo - 1);
            System.out.println("Ok, I've removed this task:");
            System.out.println(task);
            tasks.remove(taskNo - 1);
            Storage.Save(tasks, true);
            System.out.println("You have " + tasks.size() + " tasks left currently.\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please input the index of an available task!\n");
        }
    }
}
