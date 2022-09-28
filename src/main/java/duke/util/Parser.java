package duke.util;

import duke.exceptions.DukeException;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents class that makes sense of the user input so that Duke can understand the command
 */
public class Parser {
    /**
     * Makes sense of the user input and parse it so that Duke can process the commands
     *
     * @param userInput made by the user
     * @return ArrayList of Strings for Commands to execute
     * @throws DukeException
     */

    public ArrayList<String> parse(String userInput) throws DukeException {
        String[] temp = userInput.trim().split(" ", 2);
        ArrayList<String> res = new ArrayList<>();
        boolean descriptionIsEmpty = temp.length < 2;
        if (temp[0].equals("bye") || temp[0].equals("list") || temp[0].equals("help")) {
            res.add(temp[0]);
            return res;
        }
        if (descriptionIsEmpty) {
            throw new DukeException("Oops! Your input is invalid!");
        } else if (temp[0].equals("todo")) {
            res.addAll(Arrays.asList(temp));
        } else if (temp[0].equals("deadline")) {
            String[] details = temp[1].split(" /by ", 2);
            String[] parsedDetails = parseDetails(details);
            res.add("deadline");
            res.addAll(Arrays.asList(parsedDetails));
        } else if (temp[0].equals("event")) {
            String[] details = temp[1].split(" /at ", 2);
            String[] parsedDetails = parseDetails(details);
            res.add("event");
            res.addAll(Arrays.asList(parsedDetails));
        } else if (temp[0].equals("/help")) {
            res.add("help");
            return res;
        } else {
            res.addAll(Arrays.asList(temp));
        }
        return res;
    }

    /**
     * Separate parser to parse the descriptions and date of user input
     *
     * @param details the String that follows the first command word
     * @return Array of String
     * @throws DukeException
     */
    private String[] parseDetails(String[] details) throws DukeException {
        if (details.length < 2) {
            throw new DukeException("The description or date for this task is missing!"
            + " Make sure you include /by or /at after the description and the correct date format!");
        }
        String[] timeDetails = details[1].split(" ", 2);
        details[1] = timeDetails.length < 2 ? timeDetails[0] + " 00:00" :  details[1];
        return details;
    }
}
