import command.Command;
import exception.DukeEmptyDescriptionException;
import exception.DukeException;
import exception.DukeInvalidCommandException;
import exception.DukeNumberFormatException;

public class Parser {

    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            bye();
        } else if (command.equals("list")) {
            displayList();
        } else if (command.startsWith("mark")) {
            String[] splitCommand = command.split("\\s+",2);
            if (!splitCommand[0].equals("mark")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("mark");
            }
            try {
                String markItem = splitCommand[1];
                int itemNumber = Integer.parseInt(markItem);
                mark(itemNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeNumberFormatException();
            }
        } else if (command.startsWith("unmark")) {
            String[] splitCommand = command.split("\\s+",2);
            if (!splitCommand[0].equals("unmark")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("unmark");
            }
            try {
                String unmarkItem = splitCommand[1];
                int itemNumber = Integer.parseInt(unmarkItem);
                unmark(itemNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeNumberFormatException();
            }
        } else if (command.startsWith("delete")) {
            String[] splitCommand = command.split("\\s+",2);
            if (!splitCommand[0].equals("delete")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("delete");
            }
            try {
                String deleteItem = splitCommand[1];
                int itemNumber = Integer.parseInt(deleteItem);
                delete(itemNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeNumberFormatException();
            }
        } else if (command.startsWith("todo")) {
            String[] splitCommand = command.split("\\s+",2);
            if (!splitCommand[0].equals("todo")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("todo");
            }
            String desc = splitCommand[1];
            addTodo(desc);
        } else if (command.startsWith("deadline")) {
            String[] splitCommand = command.split("\\s+",2);
            if (!splitCommand[0].equals("deadline")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("deadline");
            }
            String desc = splitCommand[1];
            addDeadline(desc);
        } else if (command.startsWith("event")) {
            String[] splitCommand = command.split("\\s+",2);
            if (!splitCommand[0].equals("event")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("event");
            }
            String desc = splitCommand[1];
            addEvent(desc);
        } else {
            throw new DukeInvalidCommandException(command);
        }
    }
}
