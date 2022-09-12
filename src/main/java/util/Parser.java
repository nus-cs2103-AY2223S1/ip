package util;

import alanExceptions.*;

// Enum for input types
enum InputType {
    mark,
    unmark,
    todo,
    deadline,
    event,
    delete,
    find,
    akw,
    rkw,
    help,
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
            case find:
            case help:
                result = singleInput(input);
                break;
            case akw:
                result = parseAkw(input);
                break;
            case rkw:
                result = parseRkw(input);
                break;
            default:
                //for now default is for mark and unmark
                result = parseListMod(input);
        }

        return result;
    }

    private ParsedData singleInput(String input) throws AlanException {
        String[] firstSplit = input.split(" ", 2);
        String command = firstSplit[0];
        String keyword;

        try {
            keyword = firstSplit[1];
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordException(command);
        }
        return new ParsedData(keyword);
    }

    private ParsedData parseListMod(String input) throws AlanException {
        int listIndex;
        String[] firstSplit = input.split(" ", 2);
        String command = firstSplit[0];

        try {
            listIndex = Integer.parseInt(firstSplit[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new NoValueException(command);
        }


        return new ParsedData(listIndex - 1);
    }

    private ParsedData parseTimedTask(String input) throws AlanException {
        String command, task, during, time;
        String[] firstSplit, secondSplit, thirdSplit;

        firstSplit = input.split(" ", 2);
        command = firstSplit[0];

        //if second split is null then no description was added
        try {
            secondSplit = firstSplit[1].split(" /", 2);
        } catch (IndexOutOfBoundsException e) {
            throw new NoDescriptionException(command);
        }
        task = secondSplit[0];

        //if third split is null then no time was added
        try {
            thirdSplit = secondSplit[1].split(" ", 2);
            during = thirdSplit[0];
            time = thirdSplit[1];
        } catch (IndexOutOfBoundsException e) {
            throw new NoTimeException(command);
        }

        return new ParsedData(task, during, time);
    }

    private ParsedData parseUntimedTask(String input) throws AlanException {
        String command, task;
        String[] firstSplit = input.split(" ", 2);
        command = firstSplit[0];

        try {
            String[] second = firstSplit[1].split(" /", 2);
            task = second[0];
        } catch (IndexOutOfBoundsException e) {
            throw new NoDescriptionException(command);
        }

        return new ParsedData(task);
    }

    private ParsedData parseAkw(String input) throws AlanException {
        String command, kw, commandkw;
        String[] firstSplit = input.split(" ", 2);
        command = firstSplit[0];

        try {
            String[] second = firstSplit[1].split(" ", 2);
            kw = second[0];
            commandkw = second[1];
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordException(command);
        }
        return new ParsedData(kw, commandkw);
    }

    private ParsedData parseRkw(String input) throws AlanException {
        String command, kw;
        String[] firstSplit = input.split(" ", 2);
        command = firstSplit[0];

        try {
            String[] second = firstSplit[1].split(" ", 2);
            kw = second[0];
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordException(command);
        }
        return new ParsedData(kw);
    }
}
