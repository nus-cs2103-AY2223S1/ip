import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contains input methods for user to interact with Chadbot
 */
public class Ui {
    /**
     * Greets user when program runs
     */
    public static void greet() {
        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?\n";
        startChat += "To see all available commands, type help";
        Utility.printText(startChat);
    }

    /**
     * Exit message when user close program
     */
    public static void closeChat() {
        String exitChat = "Bye. Hope to see you again soon!";
        Utility.printText(exitChat);
        System.exit(0);
    }

    /**
     * List all tasks in task list
     * @param tasks arraylist of tasks
     */
    public static void listTask(ArrayList<Task> tasks) {
        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputText.append(i + 1).append(".").append(task.toString()).append("\n");
        }
        Utility.printText(outputText.toString().trim());
    }

    /**
     * Marks the task at specific index as done
     * @param tasks arraylist of tasks
     * @param taskID index of task
     * @throws IOException throws error if file can't be open in Storage
     */
    public static void markTask(ArrayList<Task> tasks, int taskID) throws IOException {
        String outputText = "";
        outputText += "Nice! I've marked this task as done:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsDone();
        outputText +=  "  " + currentTask;
        Storage.toggleMarkTaskInFile(taskID);
        Utility.printText(outputText);
    }

    /**
     * Unmarks the task at specific index as done
     * @param tasks arraylist of tasks
     * @param taskID index of task
     * @throws IOException throws error if file can't be open in Storage
     */
    public static void unmarkTask(ArrayList<Task> tasks, int taskID) throws IOException {
        String outputText = "";
        outputText += "OK, I've marked this task as not done yet:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsUndone();
        outputText +=  "  " + currentTask;
        Storage.toggleMarkTaskInFile(taskID);
        Utility.printText(outputText);
    }

    /**
     * Prints out all the tasks that ends on the date specified by user
     * @param tasks arraylist of tasks
     * @param date date specified by user
     */
    public static void printTaskAtDate(ArrayList<Task> tasks, String date) {
        StringBuilder output = new StringBuilder();
        date = date.split(" ", 2)[1];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate theDate = LocalDate.parse(date, format);

        if(theDate == null) {
            return;
        }

        for (Task t: tasks) {
            if (t instanceof Deadline) {
                LocalDate d = ((Deadline) t).getDateTime().toLocalDate();
                if(d.compareTo(theDate) == 0) {
                    output.append(t);
                    output.append(System.getProperty("line.separator"));
                }
            } else if (t instanceof Event) {
                LocalDate d = ((Event) t).getDateTime().toLocalDate();
                if(d.compareTo(theDate) == 0) {
                    output.append(t);
                    output.append(System.getProperty("line.separator"));
                }
            }
        }
        String text = output.toString().trim();
        if(text.equals("")) {
            text = "No such record for " + theDate;
        }

        Utility.printText(text);
    }
}
