package duke;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    protected Ui() {
        scanner = new Scanner(System.in);
    }

    protected String getInput() {
        return scanner.nextLine();
    }

    protected ZoneId getTimeZone(ZoneId timeZone) {
        boolean isValidAnswer = false;

        System.out.println("You are currently in timezone: " + timeZone +
                "\nWould you like to change your timezone? Y/N");

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

    protected String getSaveFile(String saveFilePath) {
        boolean isValidAnswer = false;

        System.out.println("Your current save file is " + saveFilePath +
                "\nWould you like to change your save file? Y/N");

        if (scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("Y")) {
            while (!isValidAnswer) {
                System.out.println("What is the path of your save file?");
                saveFilePath = scanner.nextLine();
                try {
                    Paths.get(saveFilePath);
                    if (!saveFilePath.endsWith(".txt")) {
                        System.out.println("☹ OOPS!!! Please enter a valid path to a .txt file");
                        continue;
                    }
                    File saveFile = new File(saveFilePath);
                    if (saveFile.getParentFile() != null) {
                        saveFile.getParentFile().mkdirs();
                    }
                    saveFile.createNewFile();
                    if (saveFile.exists()) {
                        System.out.println("Your save file is now " + saveFile);
                        isValidAnswer = true;
                    } else {
                        System.out.println("☹ OOPS!!! Please enter a valid path to a .txt file.");
                    }
                } catch (InvalidPathException | IOException e) {
                    System.out.println("☹ OOPS!!! Please enter a valid path to a .txt file.");
                }
            }
        }

        return saveFilePath;
    }

    protected void printNewTaskMessage(Task task, int size) {
        System.out.printf("Got it. I've added this task:\n" +
                        "\t%s\n" +
                        "Now you have %d tasks in the list.",
                task, size);
    }

    protected void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    protected void printAllTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + taskList.get(i));
            }
        }
    }

    protected void shutdown() {
        scanner.close();
    }
}
