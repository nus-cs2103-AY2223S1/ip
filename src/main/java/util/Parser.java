package util;

import alanExceptions.AlanException;
import alanExceptions.NoDescriptionException;
import alanExceptions.NoTimeException;
import alanExceptions.NoValueException;

// Enum for input types
enum InputType {
    mark,
    unmark,
    todo,
    deadline,
    event,
    delete,
}

/**
 * This class is used to parse command line inputs.
 */
public class Parser {
    /**
     * Parses command line input to ParsedData.
     *
     * @param type Type of input.
     * @param input The input.
     * @return ParsedData.
     * @throws AlanException The exception in case of failure.
     */
    public ParsedData parse(InputType type, String input) throws AlanException {
        ParsedData result;
        switch (type) {

            case todo:
                result = parseUntimedTask(input);
                break;
            case deadline:
            case event:
                result = parseTimedTask(input);
                break;
            default:
                //for now default is for mark and unmark
                result = parseListMod(input);
        }

        return result;
    }

    private ParsedData parseListMod(String input) throws AlanException {
        int listIndex;
        String[] first = input.split(" ", 2);
        String command = first[0];

        try {
            listIndex = Integer.parseInt(first[1]);
        } catch (IndexOutOfBoundsException exception) {
            throw new NoValueException(command);
        }


        return new ParsedData(listIndex - 1);
    }

    private ParsedData parseTimedTask(String input) throws AlanException {
        String command, task, during, time;
        String[] first, second, third;

        first = input.split(" ", 2);
        command = first[0];

        //if second split is null then no description was added
        try {
            second = first[1].split(" /", 2);
        } catch (IndexOutOfBoundsException exception) {
            throw new NoDescriptionException(command);
        }
        task = second[0];

        //if third split is null then no time was added
        try {
            third = second[1].split(" ", 2);
            during = third[0];
            time = third[1];
        } catch (IndexOutOfBoundsException exception) {
            throw new NoTimeException(command);
        }

        return new ParsedData(task, during, time);
    }

    private ParsedData parseUntimedTask(String input) throws AlanException {
        String command, task;
        String[] first = input.split(" ", 2);
        command = first[0];

        try {
            String[] second = first[1].split(" /", 2);
            task = second[0];
        } catch (IndexOutOfBoundsException exception) {
            throw new NoDescriptionException(command);
        }

        return new ParsedData(task);
    }
}
