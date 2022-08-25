package duke.util;

import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import java.time.format.DateTimeParseException;


public class Parser {
    private final TaskList list;
    enum ListCommands {
        todo, deadline, event, mark, unmark, delete,
    }

    public Parser(TaskList list) {
        this.list = list;
    }

    public boolean parseInput(String userInput, boolean fromSave) throws WrongArgumentException, FileParseException, NoArgumentException {
        if (isListCommand(userInput.split(" ")[0])) {
            this.parseListCommands(userInput, fromSave);
        } else if (userInput.equals("bye")) {
            Ui.showBye();
            return true;
        } else if (userInput.equals("list")) {
            Ui.displayList(list);
        } else if (userInput.equals("/?")) {
            Ui.showHelp();
        } else {
            System.out.println("what's this?! REDO!!!!");
        }
        return false;
    }

    private boolean isListCommand(String input) {
        for (ListCommands c : ListCommands.values()) {
            if (c.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    private void parseListCommands(String input, boolean fromSave) throws WrongArgumentException, FileParseException, NoArgumentException {
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
                    Ui.markTaskDone(list.markAsDone(index));
                    return;
                }
                case unmark: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    Ui.markTaskNotDone(list.markAsNotDone(index));
                    return;
                }
                case delete: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    Ui.deleteTask(list.delete(index));
                    return;
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new WrongArgumentException(arr[1], e);
        }

        switch (command) {
            case todo: {
                try {
                    list.add(new ToDo(arr[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NoArgumentException(0, e);
                }
                break;
            }
            case deadline: {
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
            }
            case event: {
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
        }
        if (mark != null && mark.equals("X")) {
            list.markAsDone(list.getSize() - 1);
        }

        if (!fromSave) {
            Ui.addTask(list.lastTaskAdded());
            Ui.getListSize(list);
        }

    }
}
