package duke;

import duke.command.AddClientCommand;
import duke.command.AddSavedClientCommand;
import duke.command.AddSavedTaskCommand;
import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteClientCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListClientCommand;
import duke.command.ListTaskCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;

/**
 * Represents the parser that processes user input from UI.
 */
public class Parser {

    /**
     * Returns specific command to execute from processing user input.
     *
     * @param input user input.
     * @return specific command to execute.
     * @throws DukeException if user input is of wrong format or unknown instruction.
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
            throw new DukeException("List out clients or tasks?");
        case mark:
        case unmark:
            throw new DukeException(String.format("Choose which index to %s.", input));
        case todo:
        case deadline:
        case event:
            throw new DukeException(String.format("The description of a %s cannot be empty.", input));
        case delete:
            throw new DukeException("Choose which index in task list to delete or phone number of client to delete.");
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
        case list:
            return parseList(info);
        case delete:
            return parseDelete(info);
        case mark:
            return parseMark(info, true);
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
                String phoneNumber = info.split("client ")[1];
                return new DeleteClientCommand(Integer.parseInt(phoneNumber));
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

    private static Command parseClient(String info) throws DukeException {
        String[] clientInformationList = info.split(" ", 3);
        if (clientInformationList.length != 3) { //Guard clause
            throw new DukeException("client needs to have a name, phone number and address");
        }
        String name = clientInformationList[0];
        int number = Integer.parseInt(clientInformationList[1]);
        String address = clientInformationList[2];
        return new AddClientCommand(name, number, address);
    }

    private static Command parseList(String info) throws DukeException {
        if (info.equals("task")) {
            return ListTaskCommand.of();
        } else if (info.equals("client")) {
            return ListClientCommand.of();
        }
        throw new DukeException("We only can list tasks or clients");
    }

    /**
     * Interprets information from saved file and returns the command to add the task on the line.
     *
     * @param input saved file line of tasks.
     * @return specific command to execute.
     * @throws DukeException If the file format is incorrect.
     */
    public static Command parseSavedTaskList(String input) throws DukeException {
        //Saved input is in the format: Instruction integer(indicating mark) task etc.
        String[] inputSplit = input.split(" ", 3);
        String instruction = inputSplit[0];
        boolean done = inputSplit[1].equals("1");
        String task = inputSplit[2];
        switch (Instructions.valueOf(instruction)) {
        case todo:
            return new AddSavedTaskCommand(task, done);
        case deadline:
            String[] taskAndBy = task.split(" ", 2);
            String deadlineTask = taskAndBy[0];
            String deadlineTiming = taskAndBy[1];
            return new AddSavedTaskCommand(deadlineTask, Instructions.deadline, deadlineTiming, done);
        case event:
            String[] taskAndAt = task.split(" ", 2);
            String eventTask = taskAndAt[0];
            String eventTiming = taskAndAt[1];
            return new AddSavedTaskCommand(eventTask, Instructions.event, eventTiming, done);
        default:
            throw new DukeException("task list saved file input format incorrect");
        }
    }

    /**
     * Interprets information from saved file and returns the command to add the client on the line
     *
     * @param input saved file line of clients.
     * @return AddSavedClientCommand
     * @throws DukeException if client list saved file is in wrong format
     */
    public static Command parseSavedClientList(String input) throws DukeException {
        String[] inputSplit = input.split(" ", 3);
        String name = inputSplit[0];
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(inputSplit[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Client List saved file is in wrong format.");
        }
        String address = inputSplit[2];
        return new AddSavedClientCommand(name, phoneNumber, address);
    }
}
