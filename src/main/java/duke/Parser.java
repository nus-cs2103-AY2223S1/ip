package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class parses user inputs.
 *
 */
public class Parser {

    private final TaskList taskList;

    public Parser() {
        this.taskList = new TaskList();
    }

    /**
     * Calls the corresponding methods according to user input.
     *
     * @param strArray User input separated by spaces into a String array.
     * @throws DukeException If user input format is incorrect.
     * @throws IOException If unable to save changes to file.
     */
    protected void parseCommand(String[] strArray) throws DukeException, IOException {

        String str = strArray[0];

        if (str.equals("list")) {
            this.taskList.ListTasks();
        } else if (str.equals("mark")) {
            int i = Integer.parseInt(strArray[1]) - 1;
            this.taskList.MarkTaskDoneAt(i);

        } else if (str.equals("unmark")) {
            int i = Integer.parseInt(strArray[1]) - 1;
            this.taskList.MarkTaskNotDoneAt(i);

        } else if (str.equals("delete")) {
            int i = Integer.parseInt(strArray[1]) - 1;
            this.taskList.DeleteTaskAt(i);

        } else if (str.equals("find")) {
            this.taskList.FindAllTasksWith(strArray[1]);

        } else if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {

            String taskname = "";
            String date = "";
            int i = 1;

            while (i < strArray.length && strArray[i].charAt(0) != '/') {
                taskname += strArray[i] + " ";
                i++;
            }

            while (i < strArray.length) { //means now strArray[i] == /by or /at

                if (strArray[i].charAt(0) != '/') {
                    date += strArray[i] + " ";
                }

                i++;
            }

            if (taskname.equals("")) {
                throw new DukeException("Name of task cannot be empty!");
            }

            if (str.equals("todo")) {
                this.taskList.AddToDo(taskname);
            } else {
                if (date.equals("")) {
                    throw new DukeException("Date/Time cannot be empty!");
                }

                String[] tArray = date.trim().split(" ");
                String time = "";
                if (tArray.length > 2) {
                    throw new DukeException("Date and time has too many spaces!");
                } else if (tArray.length == 2) {
                    time = tArray[1];
                }

                date = tArray[0];

                //matches the yyyy-MMM-d format
                if (!date.trim().matches("[0-9]{4}-" +
                        "((Jan)|(Feb)|(Mar)|(Apr)|(May)|(Jun)|(Jul)|(Aug)|(Sep)|(Oct)|(Nov)|(Dec))" +
                        "-[0-9]{1,2}")) {
                    throw new DukeException("Date/Time must match the YYYY-MMM-DD format!");
                }

                LocalDate localDate = null;
                LocalDateTime dateTime = null; //no time functionality for now

                try {
                    localDate = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MMM-d"));
                } catch (DateTimeParseException e) {
                    System.out.println("Please provide a sensible date and time! Exiting...");
                    return;
                }

                if (str.equals("deadline")) {
                    this.taskList.AddDeadline(taskname, localDate);
                } else {
                    this.taskList.AddEvent(taskname, localDate);
                }
            }

        } else {
            throw new DukeException("Please enter a valid input");
        }
    }


}
