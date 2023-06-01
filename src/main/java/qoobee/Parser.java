package qoobee;

import java.time.DateTimeException;

/**
 * Represents a parser that executes commands based on user's input.
 */
public class Parser {

    protected Ui ui;
    protected TaskList tasks;

    /**
     * Creates a parser given a ui and taskList.
     * @param ui The interface that user interacts with.
     * @param tasklist The list of tasks of the user.
     */
    public Parser(Ui ui, TaskList tasklist) {
        this.ui = ui;
        this.tasks = tasklist;
    }

    /**
     * Executes a specific action given a user's input.
     * @param input The user's input.
     */
    public String parse(String input) {
        assert input.length() > 0 : "Input cannot be empty";
        try {
            String[] commands = input.split(" ", 2);
            String command = commands[0];
            switch (command) {
            case "bye":
                this.ui.exit();
                return "Bye. Don't miss me too much!";
            case "list":
                return tasks.printTasks();
            case "mark":
                Task taskToMark = tasks.getTask(Integer.parseInt(commands[1].trim()) - 1);
                return tasks.mark(taskToMark);
            case "unmark":
                Task taskToUnmark = tasks.getTask(Integer.parseInt(commands[1].trim()) - 1);
                return tasks.unmark(taskToUnmark);
            case "todo":
                return tasks.addToDo(commands);
            case "deadline":
                return tasks.addDeadline(commands);
            case "event":
                return tasks.addEvent(commands);
            case "delete":
                return tasks.removeTask(commands);
            case "find":
                return tasks.findTask(commands[1]);
            case "priority":
                String[] priorityString = commands[1].split(" ", 2);
                if (priorityString.length != 2) {
                    throw new QoobeeException("Select a valid task and priority :(");
                } else {
                    Task taskToSetPriority = tasks.getTask(Integer.parseInt(priorityString[0]) - 1);
                    return tasks.setPriority(taskToSetPriority, priorityString[1]);
                }
            default:
                return "I'm sorry, but I don't know what that means :^(";
            }
        } catch (QoobeeException e) {
            return (e.toString());
        } catch (DateTimeException e) {
            return ("Please enter in the format of yyyy-mm-dd hh:mm!");
        }
    }

}
