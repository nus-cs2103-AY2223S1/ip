package duke.util;

import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import java.time.format.DateTimeParseException;

/**
 * To handle the user's input and execute the commands.
 */
public class Parser {
    private final TaskList list;

    /**
     * Valid commands as datatype
     */
    public enum ListCommands {
        todo, deadline, event, mark, unmark, delete, find
    }

    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Parses the string from either the save file or user.
     * Returns whether the program should exit.
     *
     * @param userInput String to be parsed.
     * @param fromSave true if input is from save file, false if from user.
     * @return true if program should exit, if not false.
     * @throws WrongArgumentException If argument is of the wrong format.
     * @throws FileParseException If there's an error when the line was written to save file previously.
     * @throws NoArgumentException If only the command is given without any arguments.
     */
    public String parseInput(String userInput, boolean fromSave)
            throws WrongArgumentException, FileParseException, NoArgumentException {
        if (isListCommand(userInput.split(" ")[0])) {
            return this.parseListCommands(userInput, fromSave);
        } else if (userInput.equals("bye")) {
            list.save();
            return Ui.showBye();
        } else if (userInput.equals("list")) {
            return Ui.displayList(list);
        } else if (userInput.equals("/?")) {
            return Ui.showHelp();
        } else {
            return "what's this?! REDO!!!!";
        }
    }

    private boolean isListCommand(String input) {
        for (ListCommands c : ListCommands.values()) {
            if (c.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses the string if the command is one that is handled by the TaskList, and it will call the appropriate methods
     *
     * @param input String from save file or user.
     * @param fromSave true if string is from file, false if not
     * @throws WrongArgumentException If argument is of the wrong format.
     * @throws FileParseException If there's an error when the line was written to save file previously.
     * @throws NoArgumentException If only the command is given without any arguments.
     */
    private String parseListCommands(String input, boolean fromSave)
            throws WrongArgumentException, FileParseException, NoArgumentException {
        String mark = null;
        String[] arr;
        if (fromSave) {
            String[] item = input.split("\\|");
            mark = item[1];
            arr = item[0].split(" ", 2);
        } else {
            arr = input.split(" ", 2);
        }
        ListCommands command = ListCommands.valueOf(arr[0]);

        try {
            switch (command) {
            case mark: {
                int index = Integer.parseInt(arr[1]) - 1;
                return Ui.markTaskDone(list.markAsDone(index));
            }
            case unmark: {
                int index = Integer.parseInt(arr[1]) - 1;
                return Ui.markTaskNotDone(list.markAsNotDone(index));
            }
            case delete: {
                int index = Integer.parseInt(arr[1]) - 1;
                return Ui.deleteTask(list.delete(index));
            }
            case find:
                String keyword = arr[1];
                return Ui.findKeyword(keyword, list.searchFor(keyword));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new WrongArgumentException(arr[1], e);
        }

        switch (command) {
        case todo:
            try {
                list.add(new ToDo(arr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoArgumentException(0, e);
            }
            break;
        case deadline:
            try {
                String[] desc = arr[1].split(" /by ");
                list.add(new Deadline(desc[0], desc[1]));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                if (fromSave) {
                    throw new FileParseException(input, e);
                } else {
                    if (e instanceof ArrayIndexOutOfBoundsException) {
                        throw new NoArgumentException(1, e);
                    } else {
                        // e will definitely be a DateTimeParseException
                        throw new WrongArgumentException(((DateTimeParseException) e).getParsedString(), e);
                    }
                }
            }
            break;
        case event:
            try {
                String[] desc = arr[1].split(" /at ");
                list.add(new Event(desc[0], desc[1]));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                if (fromSave) {
                    throw new FileParseException(input, e);
                } else {
                    if (e instanceof ArrayIndexOutOfBoundsException) {
                        throw new NoArgumentException(2, e);
                    } else {
                        //e will definitely be a DateTimeParseException
                        throw new WrongArgumentException(((DateTimeParseException) e).getParsedString(), e);
                    }
                }
            }
            break;
        }
        if (mark != null && mark.equals("X")) {
            list.markAsDone(list.getSize() - 1);
        }
        if (!fromSave) {
            return Ui.addTask(list.lastTaskAdded()) + Ui.getListSize(list);
        }

        return "";
    }
}
