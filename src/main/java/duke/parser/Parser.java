package duke.parser;

import duke.events.Task;
import duke.TaskList;
import duke.events.Todo;
import duke.events.Deadline;
import duke.events.Event;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.InvalidCommandException;
import duke.ui.Ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles the main logic of parsing raw input
 */
public class Parser {

    
    private static final List<String> PERMISSIBLE_TASKS = new ArrayList<>(
            Arrays.asList("todo", "event", "deadline"));

    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    /**
     * parseData takes in a user's command as a string
     * and makes sense of the command by calling TaskList's appropriate functionality
     * @param input a string of inputs
     * @param taskList the TaskList's current state
     */
    public static String parseData(String input, TaskList taskList) {

        String firstLetter = input.split(" ", 0)[0];
        switch (firstLetter) {
        case "bye":
            return Ui.displayMessage(ENDING_MESSAGE);

        case "mark":
            int taskIndex = Integer.parseInt(input.split(" ", 0)[1]) - 1;
            return taskList.markTask(taskIndex);

        case "unmark":
            int unmarkIndex = Integer.parseInt(input.split(" ", 0)[1]) - 1;
            return taskList.unmarkTask(unmarkIndex);

        case "list":
            return taskList.list();

        case "delete":
            int deleteIndex = Integer.parseInt(input.split(" ", 0)[1]) - 1;
            return taskList.deleteTask(deleteIndex);

        case "find":
            String[] keywords = input.split(" ", 2); //split into 2
            String keyword = keywords[1]; //the remainder of the input minus whitespace
            return taskList.findTask(keyword);

        case "viewSchedule":
            String[] inputTempArr = input.split(" ", 2);
            String date = inputTempArr[1];
            String decideValidDate = dateValidator(date);
            if (!(decideValidDate.equals("Success"))) { //an error occurred somewhere
                return decideValidDate;
            }
            return taskList.viewSchedule(date);

        case "help":
            try {
                Desktop.getDesktop().browse(new URI(Ui.displayHelpURL()));

            } catch (MalformedURLException | URISyntaxException e) {
                return "This shouldn't happen, the server side URL is broken.";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "Here's your help page!";

        //formulation of task
        default:

            try {
                taskValidator(input);
            } catch (InvalidCommandException ice) {
                String message = "";
                message += Ui.displayException(ice) + '\n';
                message += Ui.displayMessage("This was your invalid command: " + input) + "\n";
                return message;
            } catch (EmptyTaskException ete) {
                String message = "";
                message += Ui.displayException(ete);
                String[] taskArr = input.split(" ", 0);
                if (taskArr[0].equals("todo")) {
                    return message + "\n" +
                            Ui.displayMessage("todo requires at least a task description");
                } else {
                    return message + "\n" +
                            Ui.displayMessage("Event & Deadline requires both a task description and a date");
                }
            }

            Task newTask = generateTask(input);
            return taskList.addTask(newTask);
        }
    }
    /**
     * Helper method for input validation whenever an add task command is given
     * @param  input of type string
     * @throws InvalidCommandException if the command is not
     * in our list of permissible tasks
     * @throws EmptyTaskException if the correct command is given
     * but not enough information is provided
     */
    private static void taskValidator (String input) throws InvalidCommandException, EmptyTaskException {
        String[] tempArr = input.split(" ", 0); //splits into words

        //first word is invalid
        if (! PERMISSIBLE_TASKS.contains(tempArr[0])) {
            throw new InvalidCommandException("I'm sorry, I don't understand what that means \n"
                    + "Please enter a valid response in the future");
        }

        if (tempArr.length <= 1) {
            throw new EmptyTaskException("The description of a task cannot be empty.");
        }

    }

    private static String dateValidator (String dateInput) {
        try {
            LocalDate.parse(dateInput);
        } catch (DateTimeParseException e){
            return "Invalid date entered! Ensure you enter date in the format: " +
                    "YYYY-MM-DD";
        }
        return "Success";
    }

    //changed to public for testing, TODO: change private after validation
    public static Task generateTask(String input) {
        String[] tempArr = input.split(" ", 2);
        if (input.startsWith("todo")) {
            return new Todo(tempArr[1]);
        } else if (input.startsWith("deadline")) {
            int firstSplit = tempArr[1].indexOf("/by");
            String eventName = tempArr[1].substring(0, firstSplit);
            String date = tempArr[1].substring(firstSplit + 4);
            return new Deadline(eventName, date);
        } else { //must be Event
            int firstSplit = tempArr[1].indexOf("/at");
            String eventName = tempArr[1].substring(0, firstSplit);
            String date = tempArr[1].substring(firstSplit + 4);
            return new Event(eventName, date);
        }
    }
}
