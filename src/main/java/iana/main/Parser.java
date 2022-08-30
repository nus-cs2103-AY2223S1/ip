package iana.main;

import iana.command.Actions;
import iana.command.AddTaskCommand;
import iana.command.Command;
import iana.command.DeleteCommand;
import iana.command.ExitCommand;
import iana.command.ListCommand;
import iana.command.MarkCommand;
import iana.command.UnmarkCommand;
import iana.exception.IanaException;

/**
 * Parser that takes in command line arguments.
 */
public class Parser {

    /**
     * Parses full user input.
     * @param input user's command line input.
     * @return command that matches user's input.
     * @throws IanaException if user's input does not match correct format.
     */
    public static Command parse(String input) throws IanaException {
        String[] taskArray = input.split(" ", 2);
        String action = taskArray[0];

        try {
            switch(Actions.valueOf(action)) {
                case bye: 
                return new ExitCommand();

                case list:
                return new ListCommand();

                case delete:
                return new DeleteCommand(taskArray[1]);

                case mark:
                return new MarkCommand(taskArray[1]);

                case unmark:
                return new UnmarkCommand(taskArray[1]);

                case todo:

                case event:

                case deadline:
                return new AddTaskCommand(input);

                default:
                return new ExitCommand();
            }
        } catch (IllegalArgumentException e) {
            throw new IanaException("Oops, this action is invalid!! :C");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IanaException("Oops, this action is invalid!! :C");
        }
    }
}