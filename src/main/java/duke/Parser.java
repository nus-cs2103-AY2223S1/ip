
package duke;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Platform;

/**
 * Utility class to convert user input into a Command.
 */
public class Parser {
    static ArrayList<Task> listOfTasks = new ArrayList<>(100);
    static DukeOperations ops = new DukeOperations();
    static TaskList tl = new TaskList();

    /**
     * Returns a response to the users input.
     *
     * @return return the String representation of the task that the user has asked to happen.
     */
    public static String startParse(String input) {
        String output = input;
        String arr[] = output.split(" ", 2);
        String firstWord = arr[0];

        switch (firstWord) {
        case "bye":
            Platform.exit();
            assert firstWord.equals("bye") : "command should be bye";
        case "list":
            assert firstWord.equals("list") : "command should be list";
            return ops.displayList(listOfTasks);
        case "todo":
            return todoCommand(output);
        case "deadline":
            return deadlineCommand(output);
        case "event":
            return eventCommand(output);
        case "mark":
            int num = Integer.parseInt(arr[1]);
            return ops.markTaskAsDone(listOfTasks, num);
        case "unmark":
            int number = Integer.parseInt(arr[1]);
            return ops.unMarkTask(listOfTasks, number);
        case "delete":
            int taskToDelete = Integer.parseInt(arr[1]);
            return tl.delete(listOfTasks, taskToDelete);
        case "find":
            String str = arr[1];
            return ops.findWord(str, listOfTasks);
        default:
            return new DukeException("Invalid command").toString();
        }
    }

    /**
     * Returns a response to the users adding of a todo task.
     *
     * @param output command that has been input by the user.
     * @return The string representation of the task that the user wants to add.
     */
    public static String todoCommand(String output) {
        String words[] = output.split(" ", 2);
        String firstWord = words[0];
        assert firstWord.equals("todo") : "command should be todo";
        try {
            return tl.todo(listOfTasks, words);
        } catch (DukeException e1) {
            return (e1.toString());
        }
    }

    /**
     * Returns a response to the users adding of a deadline task.
     *
     * @param output command that has been input by the user.
     * @return The string representation of the deadline task that the user wants to add.
     */
    public static String deadlineCommand(String output) {
        String words[] = output.split(" ", 2);
        String firstWord = words[0];
        if (output.contains("/by")) {
            String arr2[] = words[1].split("/by ", 2);
            String arr3[] = arr2[1].split(" ", 2);
            if (arr2[0].equals("") || arr2[0].strip().equals("")) {
                return new DukeException("Your deadline cannot be empty").toString();
            }
            else {
                return tl.deadline(listOfTasks, arr2[0], arr3[0], arr3[1]);
            }
        } else {
            return new DukeException("Please format your deadline properly").toString();
        }
    }

    /**
     * Returns a response to the users adding of a event task.
     *
     * @param output command that has been input by the user.
     * @return The string representation of the event task that the user wants to add.
     */
    public static String eventCommand(String output) {
        String words[] = output.split(" ", 2);
        String firstWord = words[0];
        if (output.contains("/at")) {
            String arr2[] = words[1].split("/at ", 2);
            String arr3[] = arr2[1].split(" ", 2);
            if (arr2[0].equals("") || arr2[0].strip().equals("")) {
                return new DukeException("Your event cannot be empty").toString();
            }
            else {
                return tl.event(listOfTasks, arr2[0], arr3[0], arr3[1]);
            }
        } else {
            return new DukeException("Please format your event properly").toString();
        }
    }
}



