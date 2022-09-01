package amanda.manager;

import amanda.exception.*;
import amanda.task.*;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;


public class TaskMaker {

    public TaskMaker() {
    }

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

    public Task make(String input) throws AmandaException {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken();
        String description = " " + tokens.nextToken();
        String deadline = null;
        while (tokens.hasMoreTokens()) {
            String curr = tokens.nextToken();
            if (curr.equals("/by") | curr.equals("/at")) {
                if (!tokens.hasMoreTokens() && type.equals("deadline")) {
                    throw new AmandaException("\nHow do you expect to meet a deadline if you don't even know when it is?\n");
                } else if (!tokens.hasMoreTokens() && type.equals("event")) {
                    throw new AmandaException("\nYou are gonna show up late to this event aren't you?\n");
                } else {
                    DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
                    DateFormat outputFormat = new SimpleDateFormat(" EEE MMM dd yyyy hh:mm aa");
                    curr = tokens.nextToken();
                    try{
                        if (validateDate(curr)) {
                            Date date = inputFormat.parse(curr);
                            deadline = outputFormat.format(date);
                        } else {
                            throw new AmandaException("\nPlease enter the date and time in the correct format, don't waste my time!\n");
                        }
                    } catch(ParseException pe) {
                        pe.printStackTrace();
                    }
                }
            } else {
                description += " " + curr;
            }
        }
        if (type.equals("todo")) {
            return new Todo(description);
        } else {
            if (deadline == null) {
                if (type.equals("deadline")) {
                    throw new AmandaException("\nHow do you expect to meet a deadline if you don't even know when it is?\n");
                } else if (type.equals("event")) {
                    throw new AmandaException("\nYou are gonna show up late to this event aren't you?\n");
                }
            }
            if (type.equals("deadline")) {
                return new Deadline(description, deadline);
            } else {
                return new Event(description, deadline);
            }
        }
    }
}
