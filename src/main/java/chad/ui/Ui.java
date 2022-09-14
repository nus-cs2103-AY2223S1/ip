package chad.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import chad.exception.ChadException;
import chad.storage.Storage;
import chad.task.Deadline;
import chad.task.Event;
import chad.task.Task;
import chad.utils.Utility;
/**
 * Contains input methods for user to interact with Chadbot
 *
 */
public class Ui {
    /**
     * Greets user when program runs
     *
     */
    public static String greet() {
        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?\n";
        startChat += "To see all available commands, type help";
        return Utility.printText(startChat);
    }

    /**
     * Exit message when user close program
     *
     */
    public static String closeChat() {
        String exitChat = "Bye. Hope to see you again soon!";
        return Utility.printText(exitChat);
    }

    /**
     * Shows all available commands
     *
     * @return available commands
     */
    public static String helpCommands() {
        String output = "";
        output += "list - List all tasks in task list\n";
        output += "bye - Close chat\n";
        output += "mark 'task' - Mark a task as done\n";
        output += "unmark 'task' - Unmark a task\n";
        output += "todo 'task' - Add a todo to task list\n";
        output += "delete 'task' - Delete a task from task list\n";
        output += "find 'keyword' - Find a task by matching keywords\n";
        return Utility.printText(output);
    }

    /**
     * List all tasks in task list
     *
     * @param tasks arraylist of tasks
     */
    public static String listTask(ArrayList<Task> tasks) {
        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputText.append(i + 1).append(".").append(task.toString()).append("\n");
        }
        return Utility.printText(outputText.toString().trim());
    }

    /**
     * Marks the task at specific index as done
     *
     * @param tasks arraylist of tasks
     * @param taskID index of task
     * @throws ChadException if file can't be open in chad.storage.Storage
     */
    public static String markTask(ArrayList<Task> tasks, int taskID) throws ChadException {
        try {
            String outputText = "";
            outputText += "Nice! I've marked this task as done:\n";
            Task currentTask = tasks.get(taskID);
            currentTask.markAsDone();
            outputText += " " + currentTask;
            Storage.toggleMarkTaskInFile(taskID);
            return Utility.printText(outputText);
        } catch (Exception e) {
            throw new ChadException(e.getMessage());
        }

    }

    /**
     * Unmarks the task at specific index as done
     *
     * @param tasks arraylist of tasks
     * @param taskID index of task
     * @throws ChadException if file can't be open in chad.storage.Storage
     */
    public static String unmarkTask(ArrayList<Task> tasks, int taskID) throws ChadException {
        String outputText = "";
        outputText += "OK, I've marked this task as not done yet:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsUndone();
        outputText += " " + currentTask;
        Storage.toggleMarkTaskInFile(taskID);
        return Utility.printText(outputText);
    }

    /**
     * Search for task by keywords
     *
     * @param tasks arraylist of task
     * @param userInput keyword
     * @return all matching tasks
     */
    public static String searchTaskByKeyword(ArrayList<Task> tasks, String userInput) {
        String keyword = userInput.replaceFirst("find", "").trim();
        String baseText = "Here are the matching tasks in your list:\n";
        String output = baseText;
        int count = 1;

        for (Task t: tasks) {
            String desc = t.getDescription();
            if (desc.contains(keyword)) {
                output += count + "." + t.toString() + "\n";
                count += 1;
            }
        }

        if (output.equals(baseText)) {
            return Utility.printText("No matching tasks were found");
        } else {
            return Utility.printText(output.trim());
        }
    }

    /**
     * Prints out all the tasks that ends on the date specified by user
     *
     * @param tasks arraylist of tasks
     * @param date date specified by user
     */
    public static String printTaskAtDate(ArrayList<Task> tasks, String date) {
        StringBuilder output = new StringBuilder();
        date = date.split(" ", 2)[1];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate theDate = LocalDate.parse(date, format);

        if (theDate == null) {
            return Utility.printText("");
        }

        for (Task t: tasks) {
            if (t instanceof Deadline) {
                LocalDate d = ((Deadline) t).getDateTime().toLocalDate();
                if (d.compareTo(theDate) == 0) {
                    output.append(t);
                    output.append(System.getProperty("line.separator"));
                }
            } else if (t instanceof Event) {
                LocalDate d = ((Event) t).getDateTime().toLocalDate();
                if (d.compareTo(theDate) == 0) {
                    output.append(t);
                    output.append(System.getProperty("line.separator"));
                }
            }
        }
        String text = output.toString().trim();
        if (text.equals("")) {
            text = "No such record for " + theDate;
        }
        return Utility.printText(text);
    }
}
