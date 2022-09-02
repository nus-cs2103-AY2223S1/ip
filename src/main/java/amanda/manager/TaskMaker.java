package main.java.amanda.manager;

import main.java.amanda.exception.AmandaException;
import main.java.amanda.task.Task;
import main.java.amanda.task.Todo;
import main.java.amanda.task.Deadline;
import main.java.amanda.task.Event;

import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

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
        try
        {
            Date date = correctFormat.parse(input);
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
    public Task make(String input) throws AmandaException {

        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken(); // type is the command word/ first word that the user input at every line.
        String description = " " + tokens.nextToken();
        String deadline = null;
        String curr;

        while (tokens.hasMoreTokens()) {
            curr = tokens.nextToken();
            if (curr.equals("/by") | curr.equals("/at")) {
                if (!tokens.hasMoreTokens() && type.equals("deadline")) { // if user did not enter a date.
                    throw new AmandaException("\nHow do you expect to meet a deadline " +
                            "if you don't even know when it is?\n");
                } else if (!tokens.hasMoreTokens() && type.equals("event")) {  // if user did not enter a date.
                    throw new AmandaException("\nYou are gonna show up late to this event aren't you?\n");
                } else {
                    DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
                    DateFormat outputFormat = new SimpleDateFormat(" EEE MMM dd yyyy hh:mm aa");
                    curr = tokens.nextToken();
                    try{
                        if (validateDate(curr)) { // validate the format of the date entered by the user.
                            Date date = inputFormat.parse(curr);
                            deadline = outputFormat.format(date);
                        } else {
                            throw new AmandaException("\nPlease enter the date and time in the correct format, " +
                                    "don't waste my time!\n");
                        }
                    } catch(ParseException pe) {
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
