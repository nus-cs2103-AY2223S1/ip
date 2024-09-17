package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Recurring;
import duke.task.ToDo;



/**
 * To handle the user's input and execute the commands.
 */
public class Parser {
    private final TaskList list;

    /**
     * Valid commands as datatype
     */
    public enum ListCommands {
        todo, deadline, event, recurring, mark, unmark, delete, find, remaining
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
            throws WrongArgumentException, FileParseException, NoArgumentException, ClassCastException {
        if (isListCommand(userInput.split(" ")[0])) {
            return this.parseListCommands(userInput, fromSave);
        } else if (userInput.equals("bye")) {
            list.save();
            return Ui.showBye();
        } else if (userInput.equals("list")) {
            return Ui.displayList(list);
        } else if (userInput.equals("/?")) {
            return Ui.showHelp();
        } else if (userInput.equals("hello")) {
            return "hiii!!";
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
            throws WrongArgumentException, FileParseException, NoArgumentException, ClassCastException {
        String mark = null;
        String response;
        String[] arr;
        String[] item = new String[0];
        if (fromSave) {
            item = input.split("\\|");
            mark = item[1];
            arr = item[0].split(" ", 2);
        } else {
            arr = input.split(" ", 2);
        }
        ListCommands command = ListCommands.valueOf(arr[0]);
        try {
            response = parseTaskCommand(command, arr[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoArgumentException(command.toString(), e);
        }

        if (response != null) {
            return response;
        }
        if (item.length == 3) {
            parseAddTaskCommand(command, true, arr[1], item[2]);
        } else {
            parseAddTaskCommand(command, fromSave, arr[1]);
        }
        if (mark != null && mark.equals("X")) {
            list.markAsDone(list.getSize() - 1);
        }
        if (!fromSave) {
            return Ui.addTask(list.lastTaskAdded()) + Ui.getListSize(list);
        }
        return "";
    }

    private String parseTaskCommand(ListCommands command, String index)
            throws WrongArgumentException, ClassCastException {
        try {
            switch (command) {
            case mark: {
                int n = Integer.parseInt(index) - 1;
                return Ui.markTaskDone(list.markAsDone(n));
            }
            case unmark: {
                int n = Integer.parseInt(index) - 1;
                return Ui.markTaskNotDone(list.markAsNotDone(n));
            }
            case delete: {
                int n = Integer.parseInt(index) - 1;
                return Ui.deleteTask(list.delete(n));
            }
            case find:
                return Ui.findKeyword(index, list.searchFor(index));
            case remaining: {
                int n = Integer.parseInt(index) - 1;
                Recurring t = (Recurring) list.getTask(n);
                return Ui.showRemaining(t);
            }
            default:
                return null;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new WrongArgumentException(index, e);
        } catch (ClassCastException f) {
            throw new ClassCastException("Task " + index + " is not a Recurring Task");
        }
    }

    private void parseAddTaskCommand(ListCommands command, boolean fromSave, String... arg)
            throws NoArgumentException, WrongArgumentException, FileParseException {
        switch (command) {
        case todo:
            try {
                list.add(new ToDo(arg[0]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoArgumentException("todo", e);
            }
            break;
        case deadline:
            try {
                String[] desc = arg[0].split(" /by ");
                list.add(new Deadline(desc[0], desc[1]));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                if (fromSave) {
                    throw new FileParseException(command + arg[0], e);
                } else {
                    if (e instanceof ArrayIndexOutOfBoundsException) {
                        throw new NoArgumentException("deadline", e);
                    } else {
                        // e will definitely be a DateTimeParseException
                        throw new WrongArgumentException(((DateTimeParseException) e).getParsedString(), e);
                    }
                }
            }
            break;
        case event:
            try {
                String[] desc = arg[0].split(" /at ");
                list.add(new Event(desc[0], desc[1]));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                if (fromSave) {
                    throw new FileParseException(command + arg[0], e);
                } else {
                    if (e instanceof ArrayIndexOutOfBoundsException) {
                        throw new NoArgumentException("event", e);
                    } else {
                        //e will definitely be a DateTimeParseException
                        throw new WrongArgumentException(((DateTimeParseException) e).getParsedString(), e);
                    }
                }
            }
            break;
        case recurring:
            try {
                String[] desc = arg[0].split(" /every ");
                String[] arr = desc[1].split(" \\*");
                Recurring r = new Recurring(desc[0], arr[0], Integer.parseInt(arr[1]));
                if (fromSave) {
                    r.calculateRemaining(LocalDateTime.parse(arg[1]));
                }
                list.add(r);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                if (fromSave) {
                    throw new FileParseException(command + arg[0], e);
                } else {
                    if (e instanceof ArrayIndexOutOfBoundsException) {
                        throw new NoArgumentException("recurring", e);
                    } else {
                        //e will definitely be a DateTimeParseException
                        throw new WrongArgumentException(((DateTimeParseException) e).getParsedString(), e);
                    }
                }
            }
            break;
        default:
            assert false;
        }
    }
}
