package drivers;

import exceptions.GeneralException;
import exceptions.TumuException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Extracts the information from the file into
 * the TaskList class, and format each task stored
 * within the local txt file into a Task
 * class.
 */
public class TaskFormatter {
    private static final String DELIMITER = " # ";

    private String taskType;
    private String statusIcon;
    private String description;
    private final String timing;

    /**
     * Constructor for the TaskFormatter class.
     * @param task Task format given from the local txt file.
     */
    public TaskFormatter(String task) {
        //Parse the task from the text format.
        String[] parsedInfo = task.split(DELIMITER);
        if (parsedInfo.length >= 3) {
            //correct amount of information.
            taskType = parsedInfo[0];
            statusIcon = parsedInfo[1];
            description = parsedInfo[2];
        }
        if (parsedInfo.length == 4) {
            //Timing available. Remove T from toString of LocalDateTime class.
            timing = parsedInfo[3].replace("T", "")
                    .replace(":", "");
        } else {
            timing = "";
        }
    }

    /**
     * Formats the task such that it is converted back into
     * its appropriate task to be stored in TaskList.
     * @return A task that can be stored in TaskList.
     * @throws TumuException Parent exception for the program.
     */
    public Task getTask() throws TumuException {
        //taskType can only be E, D, T
        switch (taskType) {
        case "E":
            Task event = new Event(description, timing);
            markTask(event);
            return event;
        case "D":
            Task deadline = new Deadline(description, timing);
            markTask(deadline);
            return deadline;
        case "T":
            Task todo = new Todo(description);
            markTask(todo);
            return todo;
        default:
            throw new GeneralException("Task type is not found!");
        }
    }

    /**
     * Marks task if there is an appropriate status icon.
     * @param task Task to be marked.
     */
    private void markTask(Task task) {
        if (statusIcon.equals("X")) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }
}
