package duke;

import java.util.Scanner;

/**
 * Continuously asks the user for input and handles the inputs
 *
 * @author Sean Lam
 */
public class Ui {
    TaskList itemList;

    public Ui(TaskList itemList) {
        this.itemList = itemList;
    }

    public String getOneInput(String input) throws DukeException{
        int index;
        String command = Parser.getCommand(input);
        switch (command) {
            // Exit
        case "bye":
            return "Bye. Hope to see you again soon!";
        // List out items
        case "list":
            return "Here are the tasks in your list:" + itemList;
        case "delete":
            return itemList.deleteTask(Parser.getDescription(input));
        // mark items
        case "mark":
            index = Parser.getIndex(input);
            return itemList.editMarkStatus(index, true);
        // unmark items
        case "unmark":
            index = Parser.getIndex(input);
            return itemList.editMarkStatus(index, false);
        case "todo":
            return addTodo(input);
        case "deadline":
            return addDeadline(input);
        case "event":
            return addEvent(input);
        case "find":
            return itemList.findTask(Parser.getDescription(input));
        // unrecognised commands
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String addTodo(String input) throws DukeException {
        if (Parser.isInvalidDescription(input)) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task toAdd = new ToDo(Parser.getDescription(input));
            return itemList.addTask(toAdd);
        }
    }

    private String addDeadline(String input) throws DukeException {
        Parser.isInvalidInput(input);
        //eg. by 2019-10-03 18:00
        Task toAdd = new Deadline(Parser.getDescription(input),
                Parser.getDate(input),
                Parser.getFrom(input));
        return itemList.addTask(toAdd);
    }

    private String addEvent(String input) throws DukeException {
        Parser.isInvalidInput(input);
        //eg. at 2019-10-03 18:00-19:00
        Task toAdd = new Event(Parser.getDescription(input),
                Parser.getDate(input),
                Parser.getFrom(input),
                Parser.getTo(input));
        return itemList.addTask(toAdd);

    }

    public void showLoadingError() {
        System.out.println("Unable to load file. New file has been created");
    }
}
