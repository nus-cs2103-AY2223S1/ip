package duke;

import java.time.format.DateTimeParseException;
/**
 * Continuously asks the user for input and handles the inputs
 *
 * @author Sean Lam
 */
public class Ui {
    protected TaskList itemList;

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
            return "Here are the tasks in your list:\n" + itemList;
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
        case "/help":
            return listCommands();
        // unrecognised commands
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static String greetUser() {
        String greeting = "Welcome to your personal assistant Duke! How may I help you today?";
        return greeting + "\nFor more information about available commands, enter '/help' into the text box";
    }

    private String listCommands() {
        String commands = "Here is a list of possible commands";
        String addTaskCommands = "\n\tAdd tasks:" +
                "\n1. todo DESCRIPTION" +
                "\n2. deadline DESCRIPTION /by YYYY-MM-DD HH:mm" +
                "\n3. event DESCRIPTION /by YYYY-MM-DD HH:mm-HH:mm";
        String others = "\n\tOther functions include:" +
                "\n4. mark TASK_INDEX" +
                "\n5. unmark TASK_INDEX" +
                "\n6. find TASK_INDEX" +
                "\n7. delete TASK_INDEX" +
                "\n8. list" +
                "\n9. bye";
        return commands + addTaskCommands + others;
    }

    private String addTodo(String input) throws DukeException {
        if (Parser.isInvalidDescription(input)) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        } else {
            Task toAdd = new ToDo(Parser.getDescription(input));
            return itemList.addTask(toAdd);
        }
    }

    private String addDeadline(String input) throws DukeException {
        Parser.isInvalidInput(input);
        //eg. by 2019-10-03 18:00
        try {
            Task toAdd = new Deadline(Parser.getDescription(input),
                    Parser.getDate(input),
                    Parser.getFrom(input));
            return itemList.addTask(toAdd);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid input detected. Required input format: " +
                    "deadline {description} /by dd-MM-yy HH:mm");
        }
    }

    private String addEvent(String input) throws DukeException {
        try {
            Parser.isInvalidInput(input);
            //eg. at 2019-10-03 18:00-19:00
            Task toAdd = new Event(Parser.getDescription(input),
                    Parser.getDate(input),
                    Parser.getFrom(input),
                    Parser.getTo(input));
            return itemList.addTask(toAdd);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid input detected. Required input format: " +
                    "event {description} /at dd-MM-yy HH:mm-HH:mm");
        }
    }

    public void showLoadingError() {
        System.out.println("Unable to load file. New file has been created");
    }
}
