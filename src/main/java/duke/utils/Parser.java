package duke.utils;

import duke.Command;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Parse the commands that have been inputted into Duke.
 */
public class Parser {
    List<String> inputArray;

    public Parser(String command) {
        this.inputArray = Arrays.asList(command.split(" "));
    }

    /**
     * Returns the first word in the input command.
     *
     * @return the keyword as a Command.
     * @throws DukeException if the keyword is not recognised.
     */
    public Command getKeyword() throws DukeException {
        try {
            Command keyword = Command.valueOf(inputArray.get(0).toUpperCase());
            return keyword;
        } catch (Exception e) {
            throw new DukeException("You did not provide a valid command");
        }
    }

    /**
     * Returns everything except the first keyword of the input command.
     *
     * @return the main content of the input.
     * @throws DukeException if the input is incomplete.
     */
    public String getContent() throws DukeException {
        try {
            String content = String.join(" ", inputArray.subList(1, inputArray.size()));
            return content;
        } catch (Exception e) {
            throw new DukeException("Input given is incomplete");
        }
    }

    /**
     * Returns content from an event command.
     *
     * @return the main content of the input.
     * @throws DukeException if the input is incomplete.
     */
    public String[] getContentForEvent() throws DukeException {
        String content = getContent();
        if (!content.contains("/at")) {
            throw new DukeException("An event must at least have a description and date!");
        }
        String[] commandArray = content.split(" /at ");
        return commandArray;
    }

    /**
     * Returns content from a deadline command.
     *
     * @return the main content of the input.
     * @throws DukeException if the input is incomplete.
     */
    public String[] getContentForDeadline() throws DukeException {
        String content = getContent();
        if (!content.contains("/by")) {
            throw new DukeException("A deadline must at least have a description and date!");
        }
        String[] commandArray = content.split(" /by ");
        return commandArray;
    }

    static public LocalDateTime stringToDateTime(String str) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date provided is invalid");
        }
    }

    /**
     * Parsed a saved string and returns the corresponding Task.
     *
     * @param save String containing the line in the storage to be parsed.
     * @return the parsed Task.
     * @throws DukeException if the saved string does not match any patterns.
     */
    static public Task parseSave(String save) throws DukeException {
        String[] taskElements = save.split("\\|");
        Task newTask;
        try {
            boolean isDone = taskElements[1].equals("0");
            switch (taskElements[0]) {
            case "T": {
                newTask = new ToDo(taskElements[2], isDone);
                break;
            }
            case "D": {
                String dateString = taskElements[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                newTask = new Deadline(taskElements[2], dateTime);
                break;
            }
            case "E": {
                String dateString = taskElements[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                newTask = new Event(taskElements[2], dateTime);
                break;
            }
            default: {
                throw new DukeException("Task type cannot be parsed");
            }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return newTask;
    }
}
