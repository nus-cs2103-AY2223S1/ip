package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The Parser class parses user inputs.
 *
 */
public class Parser {

    private final TaskList taskList;

    /** Constructor for a Parser object */
    public Parser() {
        this.taskList = new TaskList();
    }

    private String markMultiple(String[] strArray) {

        StringBuilder output = new StringBuilder();

        for (int i = 1; i < strArray.length; i++) {

            int index = Integer.parseInt(strArray[i]) - 1;

            try {
                output.append(this.taskList.markTaskDoneAt(index));
            } catch (IOException e) {
                return "Unable to save to file";
            }
        }

        return output.toString();
    }

    private String unmarkMultiple(String[] strArray) {

        StringBuilder output = new StringBuilder();

        for (int i = 1; i < strArray.length; i++) {

            int index = Integer.parseInt(strArray[i]) - 1;

            try {
                output.append(this.taskList.markTaskNotDoneAt(index));
            } catch (IOException e) {
                return "Unable to save to file";
            }
        }

        return output.toString();
    }

    private String deleteMultiple(String[] strArray) {

        Integer[] sortedIntArray = Arrays.stream(strArray)
                .skip(1)
                .sorted()
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        StringBuilder output = new StringBuilder();
        int offset = 0;

        for (Integer i : sortedIntArray) {

            int index = i - 1 - offset;

            try {
                output.append(this.taskList.deleteTaskAt(index));
            } catch (IOException e) {
                return "Unable to save to file";
            }

            offset++;
        }

        return output.toString();
    }

    private String parseDeadlineOrEvent(String[] strArray) throws DukeException, IOException {

        String taskname = Arrays.stream(strArray)
                .skip(1)
                .takeWhile(current -> current.charAt(0) != '/')
                .reduce("", (acc, current) -> acc + " " + current);

        String date = Arrays.stream(strArray)
                .skip(1)
                .dropWhile(current -> current.charAt(0) != '/')
                .skip(1)
                .collect(Collectors.joining()); //no time functionality yet

        if (taskname.equals("")) {
            throw new DukeException("Name of task cannot be empty!");
        }

        if (date.equals("")) {
            throw new DukeException("Date/Time cannot be empty!");
        }

        LocalDate localDate; //no time functionality for now

        try {
            localDate = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MMM-d"));
        } catch (DateTimeParseException e) {
            return "Please provide a realistic date in the yyyy-MMM-dd format!";
        }

        if (strArray[0].equals("deadline")) {
            return this.taskList.addDeadline(taskname, localDate);
        } else {
            return this.taskList.addEvent(taskname, localDate);
        }
    }

    private String parseTodo(String[] strArray) throws IOException {

        String taskname = Arrays.stream(strArray)
                .skip(1)
                .reduce("", (acc, current) -> acc + " " + current);

        return this.taskList.addToDo(taskname);
    }


    /**
     * Calls the corresponding methods according to user input.
     *
     * @param strArray User input separated by spaces into a String array.
     * @return Duke's response.
     * @throws DukeException If user input format is incorrect.
     * @throws IOException If unable to save changes to file.
     */
    protected String parseCommand(String[] strArray) throws IOException, DukeException {

        String str = strArray[0];

        if (str.equals("deadline") || str.equals("event")) {
            return parseDeadlineOrEvent(strArray);
        }

        switch (str) {
        case "list":
            return this.taskList.listTasks();
        case "mark":
            return markMultiple(strArray);
        case "unmark":
            return unmarkMultiple(strArray);
        case "delete":
            return deleteMultiple(strArray);
        case "find":
            return this.taskList.findAllTasksWith(strArray[1]);
        case "todo":
            return parseTodo(strArray);
        default:
            return "Please enter a valid input";
        }
    }
}
