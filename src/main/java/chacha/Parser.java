package chacha;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chacha.commands.*;
import chacha.tasks.Deadline;
import chacha.tasks.Event;
import chacha.tasks.Todo;

/**
 * Parser to parse user input into Chacha commands.
 */
public class Parser {
    
    /** 
     * Parses user input into command.
     * 
     * @param userInput User input from user interaction with Chacha.
     * @return Chacha command.
     * @throws ChachaException If user input cannot be parsed.
     */
    public static Command parse(String userInput) throws ChachaException {
        assert userInput.length() > 0 : "userInput length should be more than 0";
        String[] inputArray = userInput.split(" ");
        String commandStr = inputArray[0];
        CommandEnums command = CommandEnums.ERROR;
        try {
            command = CommandEnums.valueOf(commandStr.toUpperCase());
        } catch (Exception e) {
            command = CommandEnums.ERROR;
        }
        switch (command) {
            case TODO:
                return todoCommand(userInput);
            case DEADLINE:
                return deadlineCommand(userInput);
            case EVENT:
                return eventCommand(userInput);
            case LIST:
                return listCommand(inputArray);
            case MARK:
                return markCommand(inputArray, userInput);
            case UNMARK:
                return unmarkCommand(inputArray, userInput);
            case DELETE:
                return deleteCommand(inputArray, userInput);
            case EXIT:
                return exitCommand(inputArray);
            case FIND:
                return findCommand(inputArray, userInput);
            case SORT:
                return sortCommand(inputArray);
            default:
                throw new ChachaException("Invalid input. Please try again!");


        }
        
    }

    private static Command todoCommand(String userInput) throws ChachaException {
        try {
            String description = userInput.substring(userInput.indexOf("todo ") + 5);
            description.trim();
            Todo todo = new Todo(description);
            return new AddCommand(todo);
        } catch(Exception e) {
            throw new ChachaException("The description of a todo cannot be empty.\n" + "Please try again with a description.");
        }
    }

    private static Command deadlineCommand(String userInput) throws ChachaException {
        try {
            String description = userInput.substring(0, userInput.indexOf("/") - 1);
            description = description.substring(userInput.indexOf("deadline ") + 9);
            description.trim();
            String date = userInput.substring(userInput.indexOf("/by ") + 4);
            date.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            Deadline deadline = new Deadline(description, dateTime);
            return new AddCommand(deadline);
        } catch(DateTimeParseException e) {
            throw new ChachaException("The date should be in this format: yyyy-MM-dd HH:mm");
        } catch(Exception e) {
            throw new ChachaException("The description of a deadline cannot be empty.\n" + "Please try again with a description.");
        }
    }

    private static Command eventCommand(String userInput) throws ChachaException {
        try {
            String description = userInput.substring(0, userInput.indexOf("/") - 1);
            description = description.substring(userInput.indexOf("event ") + 6);
            System.out.println(description + "here");
            description.trim();
            String date = userInput.substring(userInput.indexOf("/at ") + 4);
            date.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            Event event = new Event(description, dateTime);
            return new AddCommand(event);
        } catch(DateTimeParseException e) {
            throw new ChachaException("The date should be in this format: yyyy-MM-dd HH:mm");
        } catch(Exception e) {
            throw new ChachaException("The description of an event cannot be empty.\n" + "Please try again with a description.");
        }
    }

    private static Command listCommand(String[] inputArray) throws ChachaException {
        if (inputArray.length == 1) {
            return new ListCommand();
        } else {
            throw new ChachaException("Sorry, I don't recognise this command.");
        }
    }

    private static Command markCommand(String[] inputArray, String userInput) throws ChachaException {
        if (inputArray.length == 2) {
            String[] split = userInput.split("\\s+");
            int taskIndex = Integer.valueOf(split[1]) - 1;
            return new MarkCommand(taskIndex);
        } else {
            throw new ChachaException("Please enter valid task number to mark.");
        }
    }

    private static Command unmarkCommand(String[] inputArray, String userInput) throws ChachaException {
        if (inputArray.length == 2) {
            String[] split = userInput.split("\\s+");
            int taskIndex = Integer.valueOf(split[1]) - 1;
            return new UnmarkCommand(taskIndex);
        } else {
            throw new ChachaException("Please enter valid task number to unmark.");
        }
    }

    private static Command deleteCommand(String[] inputArray, String userInput) throws ChachaException {
        if (inputArray.length == 2) {
            String[] split = userInput.split("\\s+");
            int taskIndex = Integer.valueOf(split[1]) - 1;
            return new DeleteCommand(taskIndex);
        } else {
            throw new ChachaException("Please enter valid task number to delete.");
        }
    }

    private static Command exitCommand(String[] inputArray) throws ChachaException {
        if (inputArray.length == 1) {
            return new ExitCommand();
        } else {
            throw new ChachaException("Sorry, I don't recognise this command.");
        }
    }

    private static Command findCommand(String[] inputArray, String userInput) throws ChachaException {
        if (inputArray.length >= 2) {
            String keywordsStr = userInput.substring(5);
            String[] keywords = keywordsStr.split(", ");
            return new FindCommand(keywords);
        } else {
            throw new ChachaException("Please enter valid search keyword.");
        }
    }

    private static Command sortCommand(String[] inputArray) throws ChachaException {
        if (inputArray.length == 1) {
            return new SortCommand();
        } else {
            throw new ChachaException("Sorry, I don't recognise this command.");
        }
    }
    
}
