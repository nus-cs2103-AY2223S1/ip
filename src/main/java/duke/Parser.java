package duke;

import java.util.Scanner;

//Solution below adapted from https://github.com/nus-cs2103-AY2223S1/ip/commit/da76395fec6c4ab68f6c849a0431d7297e9fb97f

/**
 * Represents a parser that handles input entered by the user.
 */
public class Parser {

    private Duke duke;

    /**
     * Class constructor.
     *
     * @param duke Duke object.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the different type of user input.
     *
     * @param input String representation of user's input.
     * @return String response corresponding to the user's input.
     */
    public String initialise(String input) {
        String[] splitInput = input.split(" ", 2);
        try {
            switch (splitInput[0]) {

            case "list": {
                return duke.showList();
            }

            case "mark": {
                checkForMissingDescription(splitInput);
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.mark(index);
            }

            case "unmark": {
                checkForMissingDescription(splitInput);
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.unmark(index);
            }

            case "todo": {
                checkForMissingDescription(splitInput);
                String description = splitInput[1].trim();
                return duke.setToDo(description);
            }

            case "deadline": {
                checkForMissingDescription(splitInput);
                String string = splitInput[1].trim();
                String description = string.substring(0, string.indexOf("/") - 1);
                String by = string.substring(string.indexOf("/") + 4);
                return duke.setDeadLine(description, by);
            }

            case "event": {
                checkForMissingDescription(splitInput);
                String string = splitInput[1].trim();
                String description = string.substring(0, string.indexOf("/") - 1);
                String at = string.substring(string.indexOf("/") + 4);
                return duke.setEvent(description, at);
            }

            case "delete": {
                checkForMissingDescription(splitInput);
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.delete(index);
            }

            case "find": {
                checkForMissingDescription(splitInput);
                String string = splitInput[1].trim();
                return duke.find(string);
            }

            case "bye": {
                return duke.sayBye();
            }

            case "help": {
                return duke.sendHelp();
            }

            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException exception) {
            Ui ui = new Ui();
            return ui.printErrorMessage(exception.getMessage());
        }
    }

    //Solution below adapted from https://github.com/24Donovan24/ip/blob/master/src/main/java/duke/Parser.java

    /**
     * Checks for missing arguments/description in the user's input.
     *
     * @param input Array of String of the user's input.
     * @throws DukeException
     */
    public void checkForMissingDescription(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Hey! What are you trying to do? " +
                    "Please refer to the user guide if you need help using the commands!");
        }
    }


}
