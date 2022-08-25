import TaskTypes.Deadline;
import TaskTypes.Event;
import TaskTypes.Task;
import TaskTypes.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    List<String> inputArray;

    public Parser(String command) {
        this.inputArray = Arrays.asList(command.split(" "));
    }

    public Command getKeyword() throws DukeException {
        try {
            Command keyword = Command.valueOf(inputArray.get(0).toUpperCase());
            return keyword;
        } catch (Exception e) {
            throw new DukeException("You did not provide a valid command");
        }
    }

    public String getContent() throws DukeException {
        try {
            String content = String.join(" ", inputArray.subList(1, inputArray.size()));
            return content;
        } catch (Exception e) {
            throw new DukeException("Input given is incomplete");
        }
    }

    public String[] getContentForEvent() throws DukeException {
        String content = getContent();
        if (!content.contains("/at")) {
            throw new DukeException("An event must at least have a description and date!");
        }
        String[] commandArray = content.split(" /at ");
        return commandArray;
    }

    public String[] getContentForDeadline() throws DukeException {
        String content = getContent();
        if (!content.contains("/by")) {
            throw new DukeException("A deadline must at least have a description and date!");
        }
        String[] commandArray = content.split(" /by ");
        return commandArray;
    }

    static LocalDateTime stringToDateTime(String str) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date provided is invalid");
        }
    }

    static Task parseSave(String save) throws Exception {
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
            throw e;
        }
        return newTask;
    }
}
