package duke;

import java.util.Scanner;

//Solution below adapted from https://github.com/nus-cs2103-AY2223S1/ip/commit/da76395fec6c4ab68f6c849a0431d7297e9fb97f

public class Parser {

    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    public String initialise(String input) {
        String[] splitInput = input.split(" ", 2);
        try {
            switch (splitInput[0]) {

            case "list": {
                return duke.showList();
            }

            case "mark": {
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.mark(index);
            }

            case "unmark": {
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.unmark(index);
            }

            case "todo": {
                String description = splitInput[1].trim();
                return duke.setToDo(description);
            }

            case "deadline": {
                String string = splitInput[1].trim();
                String description = string.substring(0, string.indexOf("/") - 1);
                String by = string.substring(string.indexOf("/") + 4);
                return duke.setDeadLine(description, by);
            }

            case "event": {
                String string = splitInput[1].trim();
                String description = string.substring(0, string.indexOf("/") - 1);
                String at = string.substring(string.indexOf("/") + 4);
                return duke.setEvent(description, at);
            }

            case "delete": {
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.delete(index);
            }

            case "find": {
                String string = splitInput[1].trim();
                return duke.find(string);
            }

            case "bye" : {
                return duke.sayBye();
            }

            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

}
