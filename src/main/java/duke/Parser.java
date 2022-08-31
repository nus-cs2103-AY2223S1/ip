package duke;

import duke.command.AddSavedInputCommand;
import duke.command.AddUserCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;

/**
 * Represents the parser that processes user input from UI.
 */
public class Parser {

    /**
     * Returns specific command to execute from processing user input.
     *
     * @param input user input from UI.
     * @return specific command to execute.
     * @throws DukeException if user input is of wrong format or unknown instruction
     */
    public static Command parseInput(String input) throws DukeException {
        Instructions instructionInput;
        if (!input.contains(" ")) {
            try {
                instructionInput = Instructions.valueOf(input); //parse input to Instructions
            } catch (IllegalArgumentException e) {
                throw new DukeException("Sorry I do not understand what that means :(");
            }
            switch (instructionInput) {
            case bye:
                return ExitCommand.of();
            case list:
                return ListCommand.of();
            case mark:
            case unmark:
                throw new DukeException(String.format("Choose which index to %s.", input));
            case todo:
            case deadline:
            case event:
                throw new DukeException(String.format("The description of a %s cannot be empty.", input));
            case delete:
                throw new DukeException("Choose which index to delete.");
            case find:
                throw new DukeException("Input a keyword to find.");
            }
        }
        String[] split = input.split(" ", 2); //Splits input into instruction word and information on task
        String instruction = split[0];
        String info = split[1];
        try {
            instructionInput = Instructions.valueOf(instruction); //parse input to Instructions
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry I do not understand what that means :(");
        }
        switch (instructionInput) {
        case delete:
            try {
                return new DeleteCommand(Integer.parseInt(info) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Deleting requires an integer as index");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(String.format("Index %s does not exist on the list.", info));
            }
        case mark:
            try {
                return new MarkCommand(Integer.parseInt(info) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Marking requires an integer as index");
            }
        case unmark:
            try {
                return new UnMarkCommand(Integer.parseInt(info) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Unmarking requires an integer as index");
            }
        case todo:
            return new AddUserCommand(info);
        case deadline:
            if (info.contains(" /by ")) {
                String[] taskAndDeadline = info.split(" /by ", 2);
                String deadlineTask = taskAndDeadline[0];
                String deadlineTiming = taskAndDeadline[1];
                return new AddUserCommand(deadlineTask, Instructions.deadline, deadlineTiming);
            } else {
                throw new DukeException("Deadline does not have proper format.");
            }
        case event:
            if (info.contains(" /at ")) {
                String[] taskAndTiming = info.split(" /at ", 2);
                String eventTask = taskAndTiming[0];
                String eventTiming = taskAndTiming[1];
                return new AddUserCommand(eventTask, Instructions.event, eventTiming);
            } else {
                throw new DukeException("Event does not have proper format.");
            }
        case find:
            return new FindCommand(info);
        }
        throw new DukeException("Seems like that command is not in my programming :(");
    }

    /**
     * Interprets information from saved file and returns the command to add the task on the line.
     *
     * @param input saved file line of contents.
     * @return specific command to execute.
     * @throws DukeException If the file format is incorrect.
     */
    public static Command parseSavedInput(String input) throws DukeException {
        //Saved input is in the format: Instruction int(indicating mark) task etc.
        String[] inputSplit = input.split(" ", 2);
        String instruction = inputSplit[0];
        String information = inputSplit[1];
        String[] infoSplit = information.split(" ", 2);
        boolean done = infoSplit[0].equals("1");
        String task = infoSplit[1];
        switch (Instructions.valueOf(instruction)) {
        case todo:
            return new AddSavedInputCommand(task, done);
        case deadline:
            String[] taskAndBy = task.split(" ", 2);
            String deadlineTask = taskAndBy[0];
            String deadlineTiming = taskAndBy[1];
            return new AddSavedInputCommand(deadlineTask, Instructions.deadline, deadlineTiming, done);
        case event:
            String[] taskAndAt = task.split(" ", 2);
            String eventTask = taskAndAt[0];
            String eventTiming = taskAndAt[1];
            return new AddSavedInputCommand(eventTask, Instructions.event, eventTiming, done);
        default:
            throw new DukeException("Saved file input format incorrect");
        }
    }

}
