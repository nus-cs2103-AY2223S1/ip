package duke;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Consumer;

import command.*;
import exception.DukeException;
import exception.IncorrectInputException;
import exception.IncorrectInputFormatException;
import task.*;

/**
 * A class that encapsulates the Parser object
 * which deals with making sense of the user command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Parser {

    private final Ui ui;
    private SavedTaskHandler storage;

    public Parser(SavedTaskHandler storage) {
        this.storage = storage;
        ui = new Ui();

    }

    /**
     * Returns a boolean value representing whether the input is Numeric
     * @param string The string that will be parsed to check if it is Numeric
     *
     * @return A boolean showing if the input string is Numeric
     */
    public static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void checkInput(String input) throws DukeException, IncorrectInputException, IncorrectInputFormatException, DateTimeParseException {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("bye");
        commandList.add("list");
        commandList.add("mark");
        commandList.add("unmark");
        commandList.add("delete");
        commandList.add("deadline");
        commandList.add("event");
        commandList.add("todo");
        commandList.add("find");
        commandList.add("priority");
        ArrayList<String> priorityList = new ArrayList<>();
        priorityList.add("high");
        priorityList.add("medium");
        priorityList.add("low");
        String[] splitStr = input.split(" ");
        String commandString = splitStr[0];

        if (input.equals("bye") || input.equals("list")) {
            return;
        }

        if (!commandList.contains(commandString)) {
            throw new IncorrectInputException("Your inputted command does not exist...");
        }

        if (commandString.equals("mark") || commandString.equals("unmark") || commandString.equals("delete")) {
            if (splitStr.length != 2) {
                throw new IncorrectInputException("Please input a number after the command");
            }

            if (!isNumeric(splitStr[1])) {
                throw new IncorrectInputFormatException("What you typed following the command is not a number...");
            }
        }

        if (commandString.equals("todo") || commandString.equals("find")) {
            if (splitStr.length != 2) {
                throw new IncorrectInputException("Please input a task name after the command...");
            }
        }

        if (commandString.equals("priority")) {
            if (splitStr.length != 3) {
                throw new IncorrectInputException("Make sure your input is in the format <priority> <task number> <high, medium, low>");
            }

            if (!isNumeric(splitStr[1]) || !priorityList.contains(splitStr[2])) {
                throw new IncorrectInputFormatException("Make sure your input is in the format <priority> <task number> <high, medium, low>");
            }
        }

        if (commandString.equals("event")) {
            if (splitStr.length < 4) {
                throw new IncorrectInputException("Make sure your input is in the format <event> <event name> </at> <date>");
            }

            String[] splitEventStr = input.split("/at");

            if (splitEventStr.length != 2 || splitStr[1].equals("/at")) {
                throw new IncorrectInputFormatException("Make sure your input is in the format <event> <event name> </at> <date>");
            }
        }

        if (commandString.equals("deadline")) {
            if (splitStr.length < 4) {
                throw new IncorrectInputException("Make sure your input is in the format <event> <event name> </by> <date>");
            }

            String[] splitEventStr = input.split("/by ");

            if (splitEventStr.length != 2 || splitStr[1].equals("/by")) {
                throw new IncorrectInputFormatException("Make sure your input is in the format <event> <event name> </at> <date>");
            }
            LocalDate.parse(splitEventStr[1]);
        }
    }
    public Command parse1(String input) throws ParseException, DukeException, IncorrectInputFormatException, IncorrectInputException {
        String[] splitStr = input.split("\\s+");
        TaskList taskList = storage.getTaskList();
        String commandString = splitStr[0];
        checkInput(input);
        switch (commandString) {

            case "bye":
                storage.write(taskList);
                return new ByeCommand();

            case "list":
                return new ListCommand(taskList);

            case "mark":
                int index = Integer.valueOf(splitStr[1]) - 1;
                return new MarkCommand(taskList, index);

            case "unmark":
                int index1 = Integer.valueOf(splitStr[1]) - 1;
                return new UnmarkCommand(taskList, index1);

            case "delete":
                int index2 = Integer.valueOf(splitStr[1]) - 1;
                return new DeleteCommand(taskList, index2);

            case "deadline":
                return new DeadlineCommand(taskList, input, splitStr[0]);

            case "event":
                return new EventCommand(taskList, input, splitStr[0]);

            case "todo":
                return new TodoCommand(taskList, input);

            case "find":
                return new FindCommand(taskList, input);

            case "priority":
                int index3 = Integer.valueOf(splitStr[1]) - 1;
                String priority = splitStr[2];
                return new PriorityCommand(taskList, index3, priority);
            default:
                assert false; //should not reach here
                return null;
        }
    }

    /**
     * Parses the user's input and assign different actions according to the input
     * @param input The string that will be parsed
     *
     */
    public String parse(String input) throws ParseException {
        String[] splitStr = input.split("\\s+");
        TaskList taskList = storage.getTaskList();
        String errorMessage = "";

        if (input.equalsIgnoreCase("bye")) {
            storage.write(taskList);
            return ui.goodbyeMessage();

        } else if (input.equalsIgnoreCase("list")) {
            int counter = 1;
            return taskList.list();

        } else if (input.substring(0, Math.min(input.length(), 4)).equals("mark")
                || input.substring(0, Math.min(input.length(), 6)).equals("unmark")
                || input.substring(0, Math.min(input.length(), 6)).equals("delete")) {
            String str = "";
            boolean isError = false;
            String type = splitStr[0];

            try {

                if (splitStr.length > 2 || splitStr.length == 1 || !isNumeric(splitStr[1])) {
                    isError = true;
                    throw new DukeException("LUNA is unsure of what you are asking of her... ");
                }
            } catch (DukeException e) {
                errorMessage = e.toString();
            }

            if (!isError) {
                str = splitStr[1];
                int index = Integer.valueOf(str) - 1;

                if (input.contains("unmark")) {
                    Task task = taskList.get(index);
                    task.unmark();
                    String taskName = task.toString();
                    taskList.set(index, task);
                    return ui.unmarkMessage(taskName);

                } else if (input.contains("mark")) {
                    Task task = taskList.get(index);
                    task.mark();
                    String taskName = task.toString();
                    taskList.set(index, task);
                    return ui.markMessage(taskName);

                } else {
                    Task task = taskList.get(index);
                    taskList.remove(index);
                    String taskName = task.toString();
                    return ui.deleteMessage(taskName, taskList.size());

                }
            }
        } else if (input.substring(0, Math.min(input.length(), 8)).equals("priority")) {
            String str = "";
            boolean isError = false;
            String type = splitStr[0];

            try {

                if (splitStr.length > 3 || splitStr.length == 1 || !isNumeric(splitStr[1])) {
                    isError = true;
                    throw new DukeException("LUNA is unsure of what you are asking of her... ");
                }
            } catch (DukeException e) {
                errorMessage = e.toString();
            }

            if (!isError) {
                str = splitStr[1];
                int index = Integer.valueOf(str) - 1;
                String priority = splitStr[2];
                if (priority.equalsIgnoreCase("high")) {
                    Task task = taskList.get(index);
                    task.setPriority("[H]");
                    String taskName = task.toString();
                    taskList.set(index, task);
                    return ui.highPriorityMessage(taskName);

                } else if (priority.equalsIgnoreCase("medium")) {
                    Task task = taskList.get(index);
                    task.setPriority("[M]");
                    String taskName = task.toString();
                    taskList.set(index, task);
                    return ui.mediumPriorityMessage(taskName);

                } else if (priority.equalsIgnoreCase("low")) {
                    Task task = taskList.get(index);
                    task.setPriority("[L]");
                    String taskName = task.toString();
                    taskList.set(index, task);
                    return ui.lowPriorityMessage(taskName);

                } else {
                    return ui.incorrectPriorityMessage();
                }
            }


        } else {
            String type = splitStr[0];
            String[] findTask = input.split(type);
            String actualTask = "";
            boolean isError = false;

            try {
                if (findTask.length == 0) {
                    isError = true;

                    if (type.equals("todo")) {
                        throw new DukeException("To do? To do what? ");
                    } else if (type.equals("event")) {
                        throw new DukeException("And what event exactly? When is the event? ");
                    } else if (type.equals("deadline")) {
                        throw new DukeException("You'll probably miss it if you continue to "
                                + "give LUNA vague requests... ");
                    } else if (type.equals("deadline")){
                        throw new DukeException("You'll probably miss it if you continue to give LUNA vague requests... ");
                    } else if (type.equals("find")) {
                        throw new DukeException("Find... What exactly? ");
                    } else {
                        throw new DukeException("LUNA has consulted the Moon Goddess and even she "
                                + "has no idea what you're saying. ");
                    }
                } else {
                    String theTask = findTask[1].split(" /")[0];
                    actualTask = theTask;
                }
            } catch (DukeException e) {
                errorMessage = e.toString();
            }

            if (!isError) {
                if (type.equals("todo")) {

                    ToDos todo = new ToDos(actualTask);
                    taskList.add(todo);
                    return ui.todoMessage(todo, taskList.size());

                } else if (type.equals("event")) {
                    String[] splitStr2 = input.split("/at");
                    Events event = new Events(actualTask, splitStr2[1]);

                    taskList.add(event);
                    return ui.eventMessage(event, taskList.size());

                } else if (type.equals("deadline")) {
                    String[] splitStr2 = input.split("/by ");
                    Deadlines deadline = new Deadlines(actualTask, splitStr2[1]);

                    taskList.add(deadline);
                    return ui.deadlineMessage(deadline, taskList.size());
                } else if (type.equals("find")) {
                    TaskList findList = new TaskList();
                    String taskName = actualTask;
                    Consumer<? super Task> consumer = x -> {
                        if (x.toString().contains(taskName)) {
                            findList.add(x);
                        }
                    };

                    taskList.forEach(consumer);
                    return ui.findMessage() + findList.list();


                } else {
                    try {
                        throw new DukeException("LUNA has consulted the Moon Goddess and "
                                + "even she has no idea what you're saying. ");
                    } catch (DukeException e) {
                        errorMessage = e.toString();
                    }
                }
            }
        }
        return errorMessage;
    }
}

