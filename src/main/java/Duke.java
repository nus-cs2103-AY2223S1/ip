import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Duke {
    private static final String saveDirectoryPath = "../data";
    private static final String saveFilePath = "../data/duke.txt";
    private static final String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String listMessage = "Here are the tasks in your list: \n";
    private static final String markText = "Nice! I've marked this task as done: \n";
    private static final String unmarkText = "OK, I've marked this task as not done yet: \n";
    private static final String addTask = "Got it. I've added this task: \n";
    private static final String deleteTask = "Noted. I've removed this task: \n";

    private static TaskList tasklist = new TaskList();
    private static Storage storage = new Storage(saveDirectoryPath, saveFilePath);

    private static void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    private static void taskWrapper() {
        String content = tasklist.getLastTask();
        content = addTask + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        wrapText(content);
    }

    private static void wrapDelete(String content) {
        content = deleteTask + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        wrapText(content);
    }

    private static String generateList() {
        String listInString = listMessage;
        listInString += tasklist.listOfTaskForDisplay();
        return listInString;
    }

    private static String generateMark(String taskNumber) {
        String markInText = markText;
        markInText += tasklist.markSpecificTask(taskNumber);
        return markInText;
    }

    private static String generateUnmark(String taskNumber) {
        String unmarkInText = unmarkText;
        unmarkInText += tasklist.unmarkSpecificTask(taskNumber);
        return unmarkInText;
    }

    private static void handleUserInputs(Scanner scanner) {
        try {
            storage.checkExistsOrCreateNewFile(tasklist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while(scanner.hasNext()) {
            String inputString = scanner.nextLine();
            String[] inputs = inputString.split(" ");
            String input = inputs[0];

            try {
                if (input.equals("bye")) {
                    wrapText(byeMessage);
                    break;
                } else if (input.equals("list")) {
                    wrapText(generateList());
                } else if (input.equals("mark")) {
                    wrapText(generateMark(inputs[1]));
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("unmark")) {
                    wrapText(generateUnmark(inputs[1]));
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("todo")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasklist.appendToDo(inputString);
                    taskWrapper();
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("deadline")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineDescription = inputString.split("/");
                    tasklist.appendDeadline(deadlineDescription[0], deadlineDescription[1]);
                    taskWrapper();
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("event")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] eventDescription = inputString.split("/");
                    tasklist.appendEvent(eventDescription[0], eventDescription[1]);
                    taskWrapper();
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else if (input.equals("delete")) {
                    String taskMessage = tasklist.removeTask(inputs[1]);
                    wrapDelete(taskMessage);
                    storage.writeToFile(tasklist.listOfTasksForSaving());
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                wrapText(e.getMessage());
            } catch (IOException e) {
                wrapText(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        wrapText(greetMessage);
        Scanner scanner = new Scanner(System.in);
        handleUserInputs(scanner);
    }
}
