package duke;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user-facing component of MakiBot.
 *
 * @author Justin Peng
 */
public class Ui {
    /** Scanner */
    private final Scanner scanner;

    /**
     * Creates a new UI object.
     */
    protected Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the next line of user input.
     *
     * @return Next line of input from scanner.
     */
    protected String getInput() {
        return scanner.nextLine();
    }

    /**
     * Returns the user's intended timezone.
     *
     * @param timeZone The stored timezone.
     * @return The new timezone.
     */
    protected ZoneId getTimeZone(ZoneId timeZone) {
        boolean isValidAnswer = false;

        System.out.println("You are currently in timezone: " + timeZone
                + "\nWould you like to change your timezone? Y/N");

        if (scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("Y")) {
            while (!isValidAnswer) {
                System.out.println("What is your timezone relative to GMT? (+/-HH:mm)");
                try {
                    timeZone = ZoneId.of("GMT" + scanner.nextLine());
                    System.out.println("Your timezone is now " + timeZone);
                    isValidAnswer = true;
                } catch (DateTimeException e) {
                    System.out.println("☹ OOPS!!! I don't understand that timezone.");
                }
            }
        }

        return timeZone;
    }

    /**
     * Returns the user's intended save file path as a {@code String}.
     * The save file path will point to a {@code .txt} file.
     *
     * @param saveFilePath The stored save file path.
     * @return The new save file path.
     */
    protected String getSaveFile(String saveFilePath) {
        System.out.println("Your current save file is " + saveFilePath
                + "\nWould you like to change your save file? Y/N");

        if (scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("What is the path of your save file?");
            saveFilePath = scanner.nextLine();
        }

        return saveFilePath;
    }

    /**
     * Prints a message indicating that the specified new task has been added.
     *
     * @param task The new task that was added.
     * @param size The current size of the list.
     */
    protected String getNewTaskMessage(Task task, int size) {
        String intro = "Got it. I've added this task:";
        String content = String.format("\t%s", task);
        String outro = String.format("Now you have %d tasks in the list.", size);
        return combineMessages(intro, content, outro);
    }

    /**
     * Closes the scanner.
     */
    protected String close() {
        scanner.close();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all tasks in order from the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    protected String getAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment!";
        }

        String intro = "Here are the tasks in your list:";
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            items.append(String.format("\t%d. %s\n", i + 1, tasks.get(i)));
        }
        return combineMessages(intro, items.toString());
    }

    protected String combineMessages(String... messages) {
        StringBuilder output = new StringBuilder();
        for (String message : messages) {
            output.append(message).append("\n");
        }
        return output.toString();
    }
}
