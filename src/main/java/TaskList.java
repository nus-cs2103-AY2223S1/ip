import java.io.IOException;
import java.util.ArrayList;

/**
 * Manipulates list of tasks
 */
public class TaskList {
    /**
     * Deletes task at specific index
     * @param tasks arraylist of tasks
     * @param taskID specified index
     * @throws IOException error thrown from file reading
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskID) throws IOException {
        String outputText = "Noted. I've removed this task:\n";
        Task currentTask = tasks.get(taskID);
        outputText += " " + currentTask.toString() + "\n";
        tasks.remove(taskID);
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        Storage.deleteTaskInFile(taskID);
        Utility.printText(outputText);
    }
    public static void addTodoTask(ArrayList<Task> tasks, String userInput) throws ChadException, IOException {
        String outputText = "Got it. I've added this task:\n";
        String taskDescription = userInput.replaceFirst("todo","").strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a todo cannot be empty.");
        }
        Todo newTask = new Todo(taskDescription);
        tasks.add(newTask);

        String strIsDone = newTask.getDone() ? "1" : "0";
        Storage.writeToFile("T | " + strIsDone + " | " + newTask.getDescription());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        Utility.printText(outputText);

    }

    public static void addDeadlineTask(ArrayList<Task> tasks, String userInput) throws ChadException, IOException {
        String outputText = "Got it. I've added this task:\n";

        String[] temp = userInput.replaceFirst("deadline","").strip().split("/by");
        String taskDescription = temp[0].strip();
        String byDate = temp[1].strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a deadline cannot be empty.");
        }

        if (byDate.isEmpty()) {
            throw new ChadException("The date of a deadline cannot be empty.");
        }

        Deadline newTask = new Deadline(taskDescription, byDate);
        tasks.add(newTask);

        String strIsDone = newTask.getDone() ? "1" : "0";
        Storage.writeToFile("D | " + strIsDone + " | " + newTask.getDescription()
                + " | " + newTask.getDateTime());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        Utility.printText(outputText);
    }

    public static void addEventTask(ArrayList<Task> tasks, String userInput) throws ChadException, IOException {
        String outputText = "Got it. I've added this task:\n";
        String[] temp = userInput.replaceFirst("event","").strip().split("/at");
        String taskDescription = temp[0].strip();
        String byDateTime = temp[1].strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a event cannot be empty.");
        }

        if (byDateTime.isEmpty()) {
            throw new ChadException("The datetime of a event cannot be empty.");
        }

        Event newTask = new Event(taskDescription, byDateTime);
        tasks.add(newTask);

        String strIsDone = newTask.getDone() ? "1" : "0";
        Storage.writeToFile("E | " + strIsDone + " | " + newTask.getDescription()
                + " | " + newTask.getDateTime());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        Utility.printText(outputText);
    }
}
