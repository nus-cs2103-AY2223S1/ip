import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String command) throws FredException {
        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new ListCommand();

        } else if (command.startsWith("mark")) {
            if (command.trim().equals("mark")) {
                throw new FredException("The input of mark cannot be empty!");
            }

            int index;
            try {
                index = Integer.parseInt(command.substring(5));
            } catch (NumberFormatException e) {
                throw new FredException("mark can only take in an integer!");
            }

            return new MarkCommand(index);

        } else if (command.startsWith("unmark")) {
            if (command.trim().equals("unmark")) {
                throw new FredException("The input of unmark cannot be empty!");
            }

            int index;
            try {
                index = Integer.parseInt(command.substring(7));
            } catch (NumberFormatException e) {
                throw new FredException("unmark can only take in an integer!");
            }

            return new UnmarkCommand(index);

        } else if (command.startsWith("todo")) {
            if (command.trim().equals("todo")) {
                throw new FredException("The description of a todo cannot be empty.");
            }
            return new AddCommand(TaskType.TODO, command.substring(5));

        } else if (command.startsWith("event")) {
            if (command.trim().equals("event")) {
                throw new FredException("The description of a event cannot be empty.");
            }
            int split = command.indexOf("/at");
            if (split == -1) {
                throw new FredException("No date provided! Usage: \"TASK /at DATE\"");
            }

            LocalDate date;
            try {
                date = LocalDate.parse(command.substring(split + 4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                throw new FredException("Input date as yyyy-MM-dd!");
            }
            return new AddCommand(TaskType.EVENT, command.substring(6, split - 1), date);

        } else if (command.startsWith("deadline")) {
            if (command.trim().equals("deadline")) {
                throw new FredException("The description of a deadline cannot be empty.");
            }
            int split = command.indexOf("/by");
            if (split == -1) {
                throw new FredException("No date provided! Usage: \"TASK /by DATE\"");
            }

            LocalDate date;
            try {
                date = LocalDate.parse(command.substring(split + 4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                throw new FredException("Input date as yyyy-MM-dd!");
            }
            return new AddCommand(TaskType.DEADLINE, command.substring(9, split - 1), date);

        } else if (command.startsWith("delete")) {
            if (command.trim().equals("delete")) {
                throw new FredException("The input of delete cannot be empty!");
            }

            int index;
            try {
                index = Integer.parseInt(command.substring(7));
            } catch (NumberFormatException e) {
                throw new FredException("delete can only take in an integer!");
            }

            return new DeleteCommand(index);

        } else if (command.equals("save")) {
            return new SaveCommand();

        } else {
            throw new FredException("I'm sorry, but I don't know what that means :(");
        }
    }
}
