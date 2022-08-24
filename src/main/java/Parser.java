import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseInput(String input) throws DukeException {
        //todo: initialize different command objects
        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("mark") || input.startsWith("unmark")) {
            String[] parts = input.split(" ");

            // Input validation
            if (parts.length != 2) {
                throw new DukeException("Wrong input format! mark/unmark <item number>\ne.g. 'mark 3'");
            }

            String mark = parts[0];
            int taskIndex;

            try {
                taskIndex = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number! mark/unmark <item number>\ne.g. 'mark 3'");
            }

            return new MarkCommand(mark, taskIndex);
        }

        if (input.startsWith("delete")) {
            String[] parts = input.split(" ");

            // Input validation
            if (parts.length != 2) {
                throw new DukeException("Wrong format! delete <item number>\ne.g. 'delete 3'");
            }

            int taskIndex;

            try {
                taskIndex = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number! delete <item number>\ne.g. 'delete 3'");
            }

            return new DeleteCommand(taskIndex);
        }

        if (input.startsWith("todo")) {
            String[] parts = input.split("todo", 2);
            String description = parts[1].trim();

            if (parts[1].equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            Task newTask = new Todo(description);

            return new AddCommand(newTask);
        }

        if (input.startsWith("deadline")) {
            String[] parts = input.split("deadline", 2);

            if (parts[1].equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }

            String[] details = parts[1].split("/by", 2);

            String description = details[0].trim();;
            LocalDate date;

            try {
                String inputDate = details[1].trim();
                if (inputDate.equals("")) {
                    throw new DukeException("A deadline needs a by date! e.g. deadline buy dinner /by 6pm");
                }
                date = LocalDate.parse(inputDate);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("A deadline needs a by date! e.g. deadline buy dinner /by 6pm");
            } catch (DateTimeParseException e) {
                throw new DukeException("Date must be in proper format!");
            }

            Task newTask = new Deadline(description, date);

            return new AddCommand(newTask);
        }

        if (input.startsWith("event")) {
            String[] parts = input.split("event", 2);

            if (parts[1].equals("")) {
                throw new DukeException("The description of a event cannot be empty.");
            }

            String[] details = parts[1].split("/at", 2);

            String description = details[0].trim();;
            LocalDate date;

            try {
                String inputDate = details[1].trim();
                if (inputDate.equals("")) {
                    throw new DukeException("An event needs a date! e.g. event meeting /at 2pm-4pm");
                }
                date = LocalDate.parse(inputDate);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("An event needs a date! e.g. event meeting /at 2pm-4pm");
            } catch (DateTimeParseException e) {
                throw new DukeException("Date must be in proper format!");
            }

            Task newTask = new Event(description, date);

            return new AddCommand(newTask);
        }

        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

}
