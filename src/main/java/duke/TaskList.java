package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.gui.UI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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

    /**
     * Gets the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Handles the addition of tasks.
     *
     * @param type        Type of task added.
     * @param description Description of the task.
     * @throws DukeException For invalid inputs.
     * @throws IOException   For invalid inputs.
     */
    public String addTask(Command type, String description) throws DukeException, IOException {
        Task task;
        if (type == Command.TODO) {
            task = ToDo.createToDo(description);
        } else if (type == Command.DEADLINE) {
            task = Deadline.createDeadline(description);
        } else {
            task = Event.createEvent(description);
        }
        tasks.add(task);
        Storage.save(tasks, false);
        return UI.printAddTaskMessage(task);
    }

    /**
     * Handles Printing of the task list.
     */
    public String printTaskList() {
        String output = "";
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                output = output.concat(i + ". " + tasks.get(i - 1).toString() + "\n");
            }
            return output;
        } else {
            return UI.printTaskListEmpty();
        }
    }

    /**
     * Marks/Unmarks Tasks.
     *
     * @param markStatus       Input to mark/unmark a task.
     * @param userInstructions Input of the user.
     */
    public String taskMarker(Command markStatus, String userInstructions) {
        try {
            int taskNo = Integer.parseInt(userInstructions);
            Task currTask = tasks.get(taskNo - 1);
            if (markStatus == Command.MARK) {
                currTask.markDone();
                Storage.save(tasks, true);
            } else {
                currTask.markUndone();
                Storage.save(tasks, true);
            }
            return UI.printMarkedTaskMessage(currTask, markStatus);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid input, please input an available integer index!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes the task with the specified index.
     *
     * @param inputIndex Index to be deleted.
     */
    public String deleteTask(String inputIndex) {
        try {
            int taskNo = Integer.parseInt(inputIndex);
            Task task = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            Storage.save(tasks, true);
            return String.format("Ok, I've removed this task: %s\n You have %d tasks left currently.\n",
                    task.toString(), tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ("Invalid index, please input the index of an available task!\n");
        }
    }

    /**
     * Finds tasks with a matching description to the user's input.
     *
     * @param match Input by the user to be found by the method.
     */
    public String findTask(String match) {
        if (!tasks.isEmpty()) {
            String output = (String.format("Here's all I could find with \"%s\"!\n", match));
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                if (task.getDescription().contains(match)) {
                    output = output.concat(i + ". " + task + "\n");
                }
            }
            return output;
        } else {
            return "No tasks have been added!";
        }
    }
}
