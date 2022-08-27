package maria;

import java.time.LocalDate;

import maria.command.Command;
import maria.command.CommandAddDeadline;
import maria.command.CommandAddEvent;
import maria.command.CommandAddTodo;
import maria.command.CommandUnknown;

/**
 * Parses commands.
 */
public class Parser {

    /**
     * Parses the string given by the storage file.
     * @param storageStr The storage string read from file
     * @return A Command object that can be executed
     */
    public static Command parseStorage(String storageStr) {

        try {
            String[] tokens = storageStr.split("\\|\\|\\|");
            String name = tokens[0];
            boolean done = Boolean.parseBoolean(tokens[1]);
            String taskType = tokens[2];

            switch (taskType) {
            case "todo":
                return new CommandAddTodo(name, done);
            case "deadline":
                LocalDate deadline = LocalDate.parse(tokens[3]);
                return new CommandAddDeadline(name, done, deadline);
            case "event":
                LocalDate start = LocalDate.parse(tokens[3]);
                LocalDate end = LocalDate.parse(tokens[4]);
                return new CommandAddEvent(name, done, start, end);
            default:
                return new CommandUnknown();
            }
        } catch (Exception e) {
            return new CommandUnknown();
        }

    }

}
