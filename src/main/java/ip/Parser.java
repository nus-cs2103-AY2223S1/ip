package ip;

import ip.command.*;
import ip.exception.InvalidCommand;

import java.util.Scanner;

public class Parser {
    private Scanner inputLine;

    public void load(Scanner s) {
        inputLine = s;
    }

    public void clear() {
        inputLine = new Scanner("");
    }

    public Command getCommand() throws InvalidCommand {
        if (inputLine.hasNext()) {
            String commandGiven = inputLine.next();
            switch (commandGiven) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(commandGiven, inputLine);
            case "delete":
            case "mark":
            case "unmark":
                return new EditCommand(commandGiven, inputLine);
            default:
                throw new InvalidCommand(commandGiven);
            }
        } else {
            System.out.println("No input detected. Terminating program...");
            return new ByeCommand();
        }
    }
}
