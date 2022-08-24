import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(TaskList tasks, String s) throws DukeException {
        if (s.equals("bye")) {
            return new ExitCommand();
        }
        else if (s.equals("list")) {
            return new ListCommand();
        }
        else if (s.startsWith("todo")) {
            try {
                String afterCommand = s.substring(4);
                return new TodoCommand(afterCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        }
        else if (s.startsWith("deadline")) {
            try {
                String afterCommand = s.substring(8);
                if (!afterCommand.contains("/by")) {
                    throw new DukeException("You must specify /by for a deadline task.");
                }
                if (afterCommand.indexOf("/by") == 0) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String[] strArray = afterCommand.split("/by");
                if (strArray[0].isEmpty()) {
                    throw new DukeException("The date field of a deadline cannot be empty.");
                }
                LocalDate date = LocalDate.parse(strArray[1].trim());
                return new DeadlineCommand(strArray[0], date);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
        else if (s.startsWith("event")) {
            try {
                String afterCommand = s.substring(5);
                if (!afterCommand.contains("/at")) {
                    throw new DukeException("You must specify /at for an event task.");
                }
                if (afterCommand.indexOf("/at") == 0) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                String[] strArray = afterCommand.split("/at");
                if (strArray[0].isEmpty()) {
                    throw new DukeException("The remark of an event cannot be empty.");
                }
                return new EventCommand(strArray[0], strArray[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of an remark cannot be empty.");
            }
        }
        else if (s.startsWith("mark")) {
            try {
                Task task;
                String afterCommand = s.substring(4);
                int idx = Integer.parseInt(String.valueOf(afterCommand).trim());
                try {
                    task = tasks.get(idx - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid index.");
                }
                return new MarkCommand(task);
            } catch (NumberFormatException e) {
                throw new DukeException("Argument is not an integer.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Argument cannot be empty.");
            }
        }
        else if (s.startsWith("unmark")) {
            try {
                Task task;
                String afterCommand = s.substring(6);
                int idx = Integer.parseInt(String.valueOf(afterCommand).trim());
                try {
                    task = tasks.get(idx - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid index.");
                }
                return new UnmarkCommand(task);
            } catch (NumberFormatException e) {
                throw new DukeException("Argument is not an integer.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Argument cannot be empty.");
            }
        }
        else if (s.startsWith("delete")) {
            try {
                Task task;
                String afterCommand = s.substring(6);
                int idx = Integer.parseInt(String.valueOf(afterCommand).trim());
                try {
                    task = tasks.get(idx);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid index.");
                }
                return new DeleteCommand(idx);
            } catch (NumberFormatException e) {
                throw new DukeException("Argument is not an integer.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Argument cannot be empty.");
            }
        }
        else {
            return new InvalidCommand();
        }
    }
}
