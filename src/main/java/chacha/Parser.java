package chacha;

import chacha.commands.AddCommand;
import chacha.commands.Command;
import chacha.commands.DeleteCommand;
import chacha.commands.ExitCommand;
import chacha.commands.ListCommand;
import chacha.commands.MarkCommand;
import chacha.commands.UnmarkCommand;
import chacha.tasks.Deadline;
import chacha.tasks.Event;
import chacha.tasks.Todo;
import chacha.CustomException;

public class Parser {
    public static Command parse(String userInput) throws CustomException {
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
                    throw new CustomException("The description of a todo cannot be empty.\n" + "Please enter again with a description.");
                }
            case "deadline":
                try {
                    String description = userInput.substring(0, userInput.indexOf("/"));
                    description = description.substring(userInput.indexOf("deadline ") + 9);
                    description.trim();   
                    String date = userInput.substring(userInput.indexOf("/by ") + 4);
                    date.trim();
                    
                    Deadline deadline = new Deadline(description, date);
                    return new AddCommand(deadline);
                    
                } catch(Exception e) {
                    throw new CustomException("The description of a deadline cannot be empty.\n" + "Please enter again with a description.");
                }
            case "event":
                try {
                    String description = userInput.substring(0, userInput.indexOf("/"));
                    description = description.substring(userInput.indexOf("event ") + 6);
                    description.trim();
                    String range = userInput.substring(userInput.indexOf("/at ") + 4);
                    range.trim();
                    Event event = new Event(description, range);
                    return new AddCommand(event);
                } catch(Exception e) {
                    throw new CustomException("The description of an event cannot be empty.\n" + "Please enter again with a description."); 
                }
            case "list":
                if (inputArray.length == 1) {
                    return new ListCommand();
                } else {
                    throw new CustomException("Sorry, I don't recognise this command.");
                }
            case "mark":
                if (inputArray.length == 2) {
                    String[] split = userInput.split("\\s+");
                    int taskIndex = Integer.valueOf(split[1]) - 1;
                    return new MarkCommand(taskIndex);
                } else {
                    throw new CustomException("Please enter valid task number to mark.");
                }
            case "unmark":
                if (inputArray.length == 2) {
                    String[] split = userInput.split("\\s+");
                    int taskIndex = Integer.valueOf(split[1]) - 1;
                    return new UnmarkCommand(taskIndex);
                } else {
                    throw new CustomException("Please enter valid task number to unmark.");
                }
            case "delete":
                if (inputArray.length == 2) {
                    String[] split = userInput.split("\\s+");
                    int taskIndex = Integer.valueOf(split[1]) - 1;
                    return new DeleteCommand(taskIndex);
                } else {
                    throw new CustomException("Please enter valid task number to delete.");
                }
            case "bye":
                if (inputArray.length == 1) {
                    return new ExitCommand();
                } else {
                    throw new CustomException("Sorry, I don't recognise this command.");
                }

        } 
        Command commands = new ListCommand();
        return commands;
    }
    
}
