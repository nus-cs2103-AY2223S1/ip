package duke;

import duke.command.*;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The duke.Parser takes in a string and detects keywords.
 * This will be used to execute methods that handle task creation, storage, deletion etc.
 */
public class Parser {

    private static final String ERROR_MESSAGE =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static Ui ui = new Ui();

    public static String find(String keyword, TaskList tasks) {
        String message = "Here are the matching tasks in your list!\n%s";

        ArrayList<String> foundTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(keyword)) {
                String taskString = String.format("%d: %s", i + 1, task);
                foundTasks.add(taskString);
            }
        }

        String tasksString = String.join("\n", foundTasks);
        return String.format(message, tasksString);

    }

//    public static void parseInput(Storage storage, String input, TaskList taskArr) throws DukeException {
//        if (input.equals("list")) {
//            ui.printArrAsNumberedList(taskArr);
//        } else if (input.startsWith("mark")) {
//            taskArr.get(Integer.parseInt(input.substring(5)) - 1).mark();
//        } else if (input.startsWith("unmark")) {
//            taskArr.get(Integer.parseInt(input.substring(7)) - 1).unmark();
//        } else if (input.startsWith("find")) {
//            String[] split = input.split(" ");
//            System.out.println(find(split[1], taskArr));
//        } else {
//            // this is under task creation
//            if (input.startsWith("event")) {
//                String[] inputArr = input.split("/");
//                taskArr.add(new Event(inputArr[0], LocalDate.parse(inputArr[1].substring(3))));
//                ui.printAddTask(taskArr.get(taskArr.size() - 1).toString());
//            } else if (input.startsWith("todo")) {
//
//                if (input.substring(4).equals("")){
//                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//                } else {
//                    taskArr.add(new ToDo(input));
//                    ui.printAddTask(taskArr.get(taskArr.size() - 1).toString());
//                }
//            } else if (input.startsWith("deadline")) {
//                String[] inputArr = input.split("/");
//                taskArr.add(new Deadline(inputArr[0], LocalDate.parse(inputArr[1].substring(3))));
//                ui.printAddTask(taskArr.get(taskArr.size() - 1).toString());
//            } else if (input.startsWith("delete")) {
//                int index = Integer.parseInt(input.substring(7)) - 1;
//                System.out.printf("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
//                        taskArr.get(index).toString(), (taskArr.size() - 1));
//                taskArr.remove(index);
//            } else {
//                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        }
//        storage.saveFileData(taskArr);
//
//    }


    public static Command parseInput(Storage storage, String input, TaskList taskArr) throws DukeException {
        String[] split = input.trim().split(" ");
        if (split.length == 0) {
            throw new DukeException(ERROR_MESSAGE);
        }
        String keyPhrase = split[0];
        String parsedInput = String.join(" ", Arrays.copyOfRange(split, 1, split.length)).trim();
        switch (keyPhrase) {
        case "list": case "ls":
            return parseList(parsedInput);
        case "bye":
            return parseBye(parsedInput);
        case "find":
            return parseFind(parsedInput);
        case "mark":
            return parseMark(parsedInput);
        case "unmark":
            return parseUnmark(parsedInput);
        case "delete": case "del": case "rm":
            return parseDelete(parsedInput);
        case "todo": case "t":
            return parseToDo(parsedInput);
        case "deadline": case "d":
            return parseDeadline(parsedInput);
        case "event": case "e":
            return parseEvent(parsedInput);
        default:
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    public static Command parseInputOld(Storage storage, String input, TaskList taskArr) throws DukeException {
        String[] split = input.split(" ");
        if (input.equals("list")) {
            return parseList(input);
        } else if (input.startsWith("mark")) {
            return parseMark(input.substring(5));
        } else if (input.startsWith("unmark")) {
            return parseUnmark(input.substring(7));
        } else if (input.startsWith("find")) {
            return parseFind(split[1]);
        } else {
            // this is under task creation
            if (input.startsWith("event")) {
                return parseEvent(input);
            } else if (input.startsWith("todo")) {

                if (input.substring(4).equals("")){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    return parseToDo(input);
                }
            } else if (input.startsWith("deadline")) {
                return parseDeadline(input);
            } else if (input.startsWith("delete")) {
                return parseDelete(input.substring(6));
            } else {
                throw new DukeException(ERROR_MESSAGE);
            }
        }

    }

    private static void checkEmpty(String input) throws DukeException {
        if (!input.isEmpty()) {
            throw new DukeException("Please add an argument after your command phrase.");
        }
    }

    private static void checkNotEmpty(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("There should be no argument after your command phrase.");
        }
    }

    private static void checkIndex(String input) throws DukeException {
        if (!isNumber(input)) {
            String localErrorMessage = "OOPS!!! Duke requires you to input a number! \n" +
                    "Usage: commandName ## (where ## is the index you input)";
            throw new DukeException(localErrorMessage);
        }
    }

    private static boolean isNumber(String input) {
        for (int i=0; i < input.length(); i++) {
            boolean bool = Character.isDigit(input.charAt(i));
            if (!bool) {
                return false;
            }
        }
        return true;
    }

    private static Command parseList(String input) throws DukeException {
        checkEmpty(input);
        return new ListCommand();
    }
    private static Command parseBye(String input) throws DukeException {
        checkEmpty(input);
        return new ByeCommand();
    }
    private static Command parseFind(String input) throws DukeException {
        checkNotEmpty(input);
        return new FindCommand(input);
    }
    private static Command parseMark(String input) throws DukeException {
        checkNotEmpty(input);
        checkIndex(input);
        return new MarkCommand(Integer.parseInt(input));
    }

    private static Command parseUnmark(String input) throws DukeException {
        checkNotEmpty(input);
        checkIndex(input);
        return new UnmarkCommand(Integer.parseInt(input));
    }

    private static Command parseDelete(String input) throws DukeException {
        checkNotEmpty(input);
        checkIndex(input);
        return new DeleteCommand(Integer.parseInt(input));
    }
    
    private static Command parseToDo(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDoCommand(input);
    }

    private static Command parseDeadline(String input) throws DukeException {
        String keyword = "/by";
        if (!input.contains(keyword)) {
            throw new DukeException(ERROR_MESSAGE + "\n"
                    + "Usage: deadline *deadlineContent* /by *date in YYYY-MM-DD format");
        }
        int keywordPosition = input.indexOf(keyword);
        String description = input.substring(0, keywordPosition);
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(input.substring(keywordPosition + 1
                    + keyword.length()));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please key in your date in YYYY-MM-DD format.");
        }
        return new DeadlineCommand(description, date);
    }

    private static Command parseEvent(String input) throws DukeException {
        String keyword = "/at";
        if (!input.contains(keyword)) {
            throw new DukeException(ERROR_MESSAGE + "\n"
                    + "Usage: event *event description* /at *date in YYYY-MM-DD format*");
        }
        int keywordPosition = input.indexOf(keyword);
        String description = input.substring(0, keywordPosition);
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(input.substring(keywordPosition + 1
                    + keyword.length()));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please key in your date in YYYY-MM-DD format.");
        }
        return new EventCommand(description, date);
    }

}
