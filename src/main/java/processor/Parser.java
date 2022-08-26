package processor;

import duke.DukeException;
import executor.Executor;

/**
 * Represents a parser that process the input
 */
public class Parser {
    private final Executor executor;

    /**
     * Parser constructor with the specified executor
     *
     * @param executor
     */
    public Parser(Executor executor) {
        this.executor = executor;
    }

    /**
     * Returns a <code>String</code> indicating the result of the parsing
     *
     * @param input a <code>String</code> input by the user
     * @return <code>String</code>
     * @throws DukeException
     */
    public String parse(String input) throws DukeException {
        System.out.println(input);
        String command = input.split(" ")[0].toLowerCase();
        int inputLength = input.length();
        int commandLength = command.length();
        String commandDescription;

        if (inputLength == commandLength) {
            commandDescription = "";
        } else {
            commandDescription = input.substring(commandLength + 1).strip();
        }

        String[] taskDescriptionDatetime;
        String separator;
        int separatorIndex;

        switch (command) {
        case "list":
            return executor.showBrain(commandDescription);
        case "mark":
            return executor.markTask(commandDescription);
        case "unmark":
            return executor.unmarkTask(commandDescription);
        case "todo":
            taskDescriptionDatetime = new String[]{commandDescription};
            separatorIndex = -1;

            return executor.putInBrain(command, taskDescriptionDatetime, separatorIndex);
        case "deadline":
            separator = " /by ";
            taskDescriptionDatetime = commandDescription.split(separator);
            separatorIndex = commandDescription.indexOf(separator);

            return executor.putInBrain(command, taskDescriptionDatetime, separatorIndex);
        case "event":
            separator = " /at ";
            taskDescriptionDatetime = commandDescription.split(separator);
            separatorIndex = commandDescription.indexOf(separator);

            return executor.putInBrain(command, taskDescriptionDatetime, separatorIndex);
        case "delete":
            return executor.trashFromBrain(commandDescription);
        case "bye":
            return executor.hibernate();
        case "find":
            return executor.findInBrain(commandDescription);
        default:
            throw DukeException.commandNotRecognizedError(command);
        }
    }
}
