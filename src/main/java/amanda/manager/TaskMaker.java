package amanda.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import amanda.exception.AmandaException;
import amanda.exception.EmptyDateException;
import amanda.exception.InvalidDateFormatException;
import amanda.exception.InvalidDescriptionException;
import amanda.task.Deadline;
import amanda.task.Event;
import amanda.task.Task;
import amanda.task.Todo;


/**
 * TaskMaker creates tasks.
 */
public class TaskMaker {

    /**
     * Constructor for TaskMaker class.
     */
    public TaskMaker() {
    }

    /**
     * Check if the date input by the user for deadline and event is in the correct format.
     * @param input the date input by the user
     * @return if the date input is in the correct format
     */
    public boolean validateDate(String input) {
        DateFormat correctFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
        correctFormat.setLenient(false);
        try {
            correctFormat.parse(input);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Make a task from the user input.
     * @param input one line of the user input.
     * @return the task corresponding to the user input.
     * @throws AmandaException throws an exception when the user input is in the wrong format.
     */
    public Task make(String input) throws InvalidDescriptionException, EmptyDateException, InvalidDateFormatException {

        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken(); // type is the command word, first word that the user input at every line.
        String description = " ";
        String deadline = null;
        String curr;

        if (!tokens.hasMoreTokens()) {
            throw new InvalidDescriptionException();
        }
        while (tokens.hasMoreTokens()) {
            curr = tokens.nextToken();
            if (curr.equals("/by") | curr.equals("/at")) {
                if (!tokens.hasMoreTokens()) { // if user did not enter a date.
                    throw new EmptyDateException();
                } else {
                    DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
                    DateFormat outputFormat = new SimpleDateFormat(" EEE MMM dd yyyy hh:mm aa");
                    curr = tokens.nextToken();
                    try {
                        if (validateDate(curr)) { // validate the format of the date entered by the user.
                            Date date = inputFormat.parse(curr);
                            deadline = outputFormat.format(date);
                        } else {
                            throw new InvalidDateFormatException();
                        }
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }
                }
            } else {
                description += " " + curr; // extract all the content of the description of the task from the input.
            }
        }
        if (type.equals("todo")) {
            return new Todo(description);
        } else {
            if (type.equals("deadline")) {
                return new Deadline(description, deadline);
            } else {
                return new Event(description, deadline);
            }
        }
    }
}
