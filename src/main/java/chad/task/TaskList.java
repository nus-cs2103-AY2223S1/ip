package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import chad.exception.ChadException;
import chad.storage.Storage;

/**
 * Manipulates list of tasks
 *
 */
public class TaskList {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(
            "d/MM/yyyy HHmm");
    /**
     * Deletes task at specific index
     *
     * @param tasks arraylist of tasks
     * @param taskID specified index
     * @throws ChadException  from file reading
     */
    public static String deleteTask(ArrayList<Task> tasks, int taskID) throws ChadException {
        Task currentTask = tasks.get(taskID);
        tasks.remove(taskID);
        String outputText = "Noted. I've removed this task:\n";
        outputText += " " + currentTask.toString() + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        Storage.deleteTaskInFile(taskID);
        return outputText;

    }

    /**
     * Add todo task into task list
     *
     * @param tasks arraylist of tasks
     * @param userInput todo task added by user
     * @throws ChadException if description is invalid
     */
    public static String addTodoTask(ArrayList<Task> tasks, String userInput) throws ChadException {
        String taskDescription = userInput.replaceFirst("todo", "").strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a todo cannot be empty.");
        }
        Todo newTask = new Todo(taskDescription);
        tasks.add(newTask);

        String strIsDone = newTask.getDone() ? "1" : "0";
        Storage.writeToFile("T | " + strIsDone + " | " + newTask.getDescription());

        String outputText = "Got it. I've added this task:\n";
        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        return outputText;
    }

    /**
     * Add deadline task into task list
     *
     * @param tasks tasks arraylist of tasks
     * @param userInput deadline task added by user
     * @throws ChadException if description or date time is invalid
     */
    public static String addDeadlineTask(ArrayList<Task> tasks, String userInput) throws ChadException {
        String outputText = "Got it. I've added this task:\n";

        String[] temp = userInput.replaceFirst("deadline", "").strip().split("/by");
        String taskDescription = temp[0].strip();
        String dateTimeString = temp[1].strip();
        LocalDateTime dateTime;
        dateTime = LocalDateTime.parse(dateTimeString, FORMAT);
        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a deadline cannot be empty.");
        }

        Deadline newTask = new Deadline(taskDescription, dateTime);
        tasks.add(newTask);

        String strIsDone = newTask.getDone() ? "1" : "0";
        Storage.writeToFile("D | " + strIsDone + " | " + newTask.getDescription()
                + " | " + newTask.getDateTime());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        return outputText;
    }

    /**
     * Add event task into task list
     *
     * @param tasks tasks arraylist of tasks
     * @param userInput event task added by user
     * @throws ChadException thrown if description or date time is invalid
     */
    public static String addEventTask(ArrayList<Task> tasks, String userInput) throws ChadException {
        String outputText = "Got it. I've added this task:\n";
        String[] temp = userInput.replaceFirst("event", "").strip().split("/at");
        String taskDescription = temp[0].strip();
        String dateTimeString = temp[1].strip();
        LocalDateTime dateTime;
        dateTime = LocalDateTime.parse(dateTimeString, FORMAT);

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a event cannot be empty.");
        }

        Event newTask = new Event(taskDescription, dateTime);
        tasks.add(newTask);

        String strIsDone = newTask.getDone() ? "1" : "0";
        Storage.writeToFile("E | " + strIsDone + " | " + newTask.getDescription()
                + " | " + newTask.getDateTime());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        return outputText;
    }

    public static String archiveTasks(ArrayList<Task> tasks) throws ChadException {
        String str = "Cleared list";
        Storage.archiveToFile();
        tasks.clear();
        return str;
    }
}
