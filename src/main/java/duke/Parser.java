package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        if (str.equals("list")) {
            return this.taskList.listTasks();
        } else if (str.equals("mark")) {

            int i = Integer.parseInt(strArray[1]) - 1;
            return this.taskList.markTaskDoneAt(i);

        } else if (str.equals("unmark")) {

            int i = Integer.parseInt(strArray[1]) - 1;
            return this.taskList.markTaskNotDoneAt(i);

        } else if (str.equals("delete")) {

            int i = Integer.parseInt(strArray[1]) - 1;
            return this.taskList.deleteTaskAt(i);

        } else if (str.equals("find")) {

            return this.taskList.findAllTasksWith(strArray[1]);

        } else if (str.equals("todo")) {

            String taskname = Arrays.stream(strArray)
                    .skip(1)
                    .reduce("", (acc, current) -> acc + " " + current);

            return this.taskList.addToDo(taskname);

        } else if (str.equals("deadline") || str.equals("event")) {

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

            LocalDate localDate;

            //LocalDateTime dateTime = null; //no time functionality for now

            try {
                localDate = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MMM-d"));
            } catch (DateTimeParseException e) {
                return "Please provide a sensible date in the yyyy-MMM-dd format!";
            }

            if (str.equals("deadline")) {
                return this.taskList.addDeadline(taskname, localDate);
            } else {
                return this.taskList.addEvent(taskname, localDate);
            }

        } else {
            return "Please enter a valid input";
        }
    }


}
