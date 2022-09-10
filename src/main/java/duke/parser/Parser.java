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

    /**
     * TaskIndicator is an enumerator used to store
     * valid Task indicators
     * Each taskValue is encapsulated by a String
     */
    enum TaskIndicator {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline");

        private final String taskValue;
        TaskIndicator(String val) {
            taskValue = val;
        }
        public String getTask() {
            return taskValue;
        }

    }

    private static final int MINIMUM_DESCRIPTION_LENGTH = 1;
    private static final List<String> PERMISSIBLE_TASKS = new ArrayList<>(
            Arrays.asList("todo", "event", "deadline"));

    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    /**
     * parseData takes in a user's command as a string
     * and makes sense of the command by calling
     * TaskList's appropriate functionality
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
            return scheduleFormatter(taskList, input);

        case "help":
            return openHelpPage();
        //formulation of task
        default:
            return taskMessageGenerator(taskList, input);
        }
    }

    private static String openHelpPage() {
        try {
            Desktop.getDesktop().browse(new URI(Ui.displayHelpUrl()));

        } catch (MalformedURLException | URISyntaxException e) {
            return "This shouldn't happen, the server side URL is broken.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Here's your help page!";
    }

    /**
     * Helper method to instruct taskList to view available dates
     * @param taskList
     * @param input
     * @return
     */
    private static String scheduleFormatter(TaskList taskList, String input) {
        String[] splitArgsByWhitespace = input.split(" ", 2);
        String date = splitArgsByWhitespace[1];
        String decideValidDate = dateValidator(date);
        if (!(decideValidDate.equals("Success"))) { //an error occurred somewhere
            return decideValidDate;
        }
        return taskList.viewSchedule(date);
    }

    /**
     * Helper method to validate and generate a task creation input
     * @param input
     */
    private static String taskMessageGenerator(TaskList taskList, String input) {
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
                return message + "\n"
                        + Ui.displayMessage("todo requires at least a task description");
            } else {
                return message + "\n"
                        + Ui.displayMessage("Event & Deadline requires both a task description and a date");
            }
        }

        Task newTask = generateTask(input);
        return taskList.addTask(newTask);
    }

    /**
     * Helper method for input validation whenever an add task command is given
     * @param  input of type string
     * @throws InvalidCommandException if the command is not
     *          in our list of permissible tasks
     * @throws EmptyTaskException if the correct command is given
     *          but not enough information is provided
     */
    private static void taskValidator(String input) throws InvalidCommandException, EmptyTaskException {
        String taskIndicator = input.split(" ", 0)[0]; //splits into words
        String[] descriptionInformation = input.split(" ", 0);

        //taskIndicator is invalid
        if (!PERMISSIBLE_TASKS.contains(taskIndicator)) {
            throw new InvalidCommandException("I'm sorry, I don't understand what that means \n"
                    + "Please enter a valid response in the future");
        }

        if (descriptionInformation.length <= MINIMUM_DESCRIPTION_LENGTH) {
            throw new EmptyTaskException("The description of a task cannot be empty.");
        }

    }

    private static String dateValidator(String dateInput) {
        try {
            LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            return "Invalid date entered! Ensure you enter date in the format: "
                + "YYYY-MM-DD";
        }
        return "Success";
    }

    /**
     * Helper method to take in raw input string
     * And returns the relevant task
     * @param input
     * @return
     */
    public static Task generateTask(String input) {
        String taskIndicator = input.split(" ", 2)[0];
        String descriptionArgs = input.split(" ", 2)[1];
        //Logic for splitting based on TaskIndicator's Enums

        if (taskIndicator.equals(TaskIndicator.TODO.getTask())) {
            return new Todo(descriptionArgs);
        } else if (taskIndicator.equals(TaskIndicator.DEADLINE.getTask())) {
            int deliminatorSplitIndex = descriptionArgs.indexOf("/by");
            String eventName = descriptionArgs.substring(0, deliminatorSplitIndex);
            String date = descriptionArgs.substring(deliminatorSplitIndex + 4);
            return new Deadline(eventName, date);
        } else { //must be Event
            int deliminatorSplitIndex = descriptionArgs.indexOf("/at");
            String eventName = descriptionArgs.substring(0, deliminatorSplitIndex);
            String date = descriptionArgs.substring(deliminatorSplitIndex + 4);
            return new Event(eventName, date);
        }
    }
}
