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
        String command = inputArray[0];
        switch (command) {
            case "todo":
                try {
                    String description = userInput.substring(userInput.indexOf("todo ") + 5);
                    description.trim();
                    Todo todo = new Todo(description);
                    return new AddCommand(todo);
                } catch(Exception e) {
                    throw new ChachaException("The description of a todo cannot be empty.\n" + "Please enter again with a description.");
                }
            case "deadline":
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
                    throw new ChachaException("Date should be in this format: yyyy-MM-dd HH:mm");
                } catch(Exception e) {
                    throw new ChachaException("The description of a deadline cannot be empty.\n" + "Please enter again with a description.");
                }
            case "event":
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
                    throw new ChachaException("Date should be in this format: yyyy-MM-dd HH:mm");
                } catch(Exception e) {
                    throw new ChachaException("The description of an event cannot be empty.\n" + "Please enter again with a description."); 
                }
            case "list":
                if (inputArray.length == 1) {
                    return new ListCommand();
                } else {
                    throw new ChachaException("Sorry, I don't recognise this command.");
                }
            case "mark":
                if (inputArray.length == 2) {
                    String[] split = userInput.split("\\s+");
                    int taskIndex = Integer.valueOf(split[1]) - 1;
                    return new MarkCommand(taskIndex);
                } else {
                    throw new ChachaException("Please enter valid task number to mark.");
                }
            case "unmark":
                if (inputArray.length == 2) {
                    String[] split = userInput.split("\\s+");
                    int taskIndex = Integer.valueOf(split[1]) - 1;
                    return new UnmarkCommand(taskIndex);
                } else {
                    throw new ChachaException("Please enter valid task number to unmark.");
                }
            case "delete":
                if (inputArray.length == 2) {
                    String[] split = userInput.split("\\s+");
                    int taskIndex = Integer.valueOf(split[1]) - 1;
                    return new DeleteCommand(taskIndex);
                } else {
                    throw new ChachaException("Please enter valid task number to delete.");
                }
            case "exit":
                if (inputArray.length == 1) {
                    return new ExitCommand();
                } else {
                    throw new ChachaException("Sorry, I don't recognise this command.");
                }
            case "find":
                if (inputArray.length >= 2) {
                    int findCommandStr = 5;
                    String keywordsStr = userInput.substring(5);
                    String[] keywords = keywordsStr.split(", ");
                    //String keyword = split[1];
                    //return new FindCommand(keyword);
                    return new FindCommand(keywords);
                } else {
                    throw new ChachaException("Please enter valid keyword.");
                }
            case "sort":
                if (inputArray.length == 1) {
                    return new SortCommand();
                } else {
                    throw new ChachaException("Sorry, I don't recognise this command.");
                }
            default:
                throw new ChachaException("Invalid input.");

        } 
        
    }
    
}
