package duke;

import duke.command.*;

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
        if (!input.contains(" ")) {
            return parseSingleWord(input);
        }
        String[] split = input.split(" ", 2); //Splits input into instruction word and information on task
        String instruction = split[0];
        String info = split[1];
        return parseMultipleWords(instruction, info);
    }

    private static Command parseSingleWord(String input) throws DukeException {
        Instructions instructionInput = convertToInstruction(input);
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
        case client:
            throw new DukeException("Input clients name, phone number and address");
        default:
            throw new DukeException("Please input a correct command");
        }
    }

    private static Command parseMultipleWords(String instruction, String info) throws DukeException {
        Instructions instructionInput = convertToInstruction(instruction);
        switch (instructionInput) {
        case delete:
            return parseDelete(info);
        case mark:
            return parseMark(info,true);
        case unmark:
            return parseMark(info, false);
        case todo:
            return parseToDo(info);
        case deadline:
            return parseDeadLine(info);
        case event:
            return parseEvent(info);
        case find:
            return parseFind(info);
        case client:
            return parseClient(info);
        default:
            throw new DukeException("Seems like that command is not in my programming :(");
        }
    }

    private static Instructions convertToInstruction(String instruction) throws DukeException {
        try {
            return Instructions.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry I do not understand what that means :(");
        }
    }

    private static Command parseDelete(String info) throws DukeException {
        try {
            if (info.contains("client ")) {
                String index = info.split("client ")[1];
                return new DeleteClientCommand(Integer.parseInt(index));
            }
            return new DeleteTaskCommand(Integer.parseInt(info) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Deleting requires an integer as index");
        }
    }

    private static Command parseMark(String indexString, boolean isMark) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Marking/Unmarking requires an integer as index");
        }

        if (isMark) {
            return new MarkCommand(index);
        } else {
            return new UnMarkCommand(index);
        }
    }

    private static Command parseToDo(String info) {
        return new AddTaskCommand(info);
    }

    private static Command parseDeadLine(String info) throws DukeException {
        if (info.contains(" /by ")) {
            String[] taskAndDeadline = info.split(" /by ", 2);
            String deadlineTask = taskAndDeadline[0];
            String deadlineTiming = taskAndDeadline[1];
            return new AddTaskCommand(deadlineTask, Instructions.deadline, deadlineTiming);
        } else {
            throw new DukeException("Deadline does not have proper format.");
        }
    }

    private static Command parseEvent(String info) throws DukeException {
        if (info.contains(" /at ")) {
            String[] taskAndTiming = info.split(" /at ", 2);
            String eventTask = taskAndTiming[0];
            String eventTiming = taskAndTiming[1];
            return new AddTaskCommand(eventTask, Instructions.event, eventTiming);
        } else {
            throw new DukeException("Event does not have proper format.");
        }
    }

    private static Command parseFind(String info) {
        return new FindCommand(info);
    }

    private static Command parseClient(String info) {
        String[] nameNumberAddress = info.split(" ", 3);
        String name = nameNumberAddress[0];
        int number = Integer.parseInt(nameNumberAddress[1]);
        String address = nameNumberAddress[2];
        return new AddClientCommand(name, number, address);
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
