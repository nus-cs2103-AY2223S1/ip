package jarvis;

import jarvis.Storage;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Stores the task list when the program is running
 */
public class TaskList {
    Task[] taskList = new Task[100];

    /**
     * The first index that is empty
     */
    private int firstEmptyIndex = 0;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public TaskList() {
        super();
    }

    /**
     * Add a task to the task list and write to the database
     * @param input Include the description (or with time) of the task
     * @param taskType Type of the task to add
     * @param isDone Whether the task is done initially
     * @throws IOException If something went wrong when storing task to database
     */
    public void addTask(String input, Task.TaskType taskType, boolean isDone) throws IOException {
        Task task;
        try {
            task = taskType == Task.TaskType.ToDo
                    ? new Todo(input, isDone)
                    : taskType == Task.TaskType.Event
                    ? new Event(input, isDone)
                    : new Deadline(input, isDone);
        } catch (DateTimeParseException e) {
            return;
        }

        taskList[firstEmptyIndex] = task;
        storage.saveAddedTask(task);

        String msg = "Got it. I've added this task:\n" + "  "
                + taskList[firstEmptyIndex].toString()
                + "\n" + "Now you have " + (firstEmptyIndex + 1) + " tasks in the list";
        System.out.println(msg);
        ++firstEmptyIndex;
    }


    /**
     * Append the loaded task from database to task list, used when initializing each run
     * @param task The task to append
     */
    public void appendLoadedTask(Task task) {
        taskList[firstEmptyIndex] = task;
        ++firstEmptyIndex;
    }

    public void markTaskAsDone(int taskNum) {
        if (taskNum >= firstEmptyIndex) {
            System.out.println("There is no task with index " + (taskNum + 1));
            return;
        }
        taskList[taskNum].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[taskNum].toString());
    }

    public void markTaskAsUnDone(int taskNum) {
        if (taskNum >= firstEmptyIndex) {
            System.out.println("There is no task with index " + (taskNum + 1));
            return;
        }
        taskList[taskNum].markAsUnDone();
        System.out.println("I've marked this task as not done:");
        System.out.println(taskList[taskNum].toString());
    }

    /**
     * Delete(remove) a task from the task list
     * @param index The position of the task to delete, 0-based
     */
    public void deleteTask(int index) {
        if (index >= firstEmptyIndex) {
            System.out.println("There is no task with index " + (index + 1));
            return;
        }
        System.out.println("Noted. I've removed this task:\n" +
                taskList[index].toString() + "\n" +
                "Now you have " + (firstEmptyIndex - 1) + " tasks in the list");
        taskList[index] = null;
        for (int i = index; i < firstEmptyIndex; i++) {
            taskList[index] = taskList[index + 1];
        }
        --firstEmptyIndex;
    }

    /**
     * Print all the task in the task list
     */
    public void printTasks() {
        if (firstEmptyIndex == 0) {
            System.out.println("There's nothing in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < firstEmptyIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
    }

    public Task getTask(int i) {
        return taskList[i];
    }

    /**
     * Get the number of task in the current list
     * @return The number of task
     */
    public int getTaskCount() {
        return firstEmptyIndex;
    }
}
