package maria;

import java.time.LocalDate;

import maria.command.Command;
import maria.command.CommandAddDeadline;
import maria.command.CommandAddEvent;
import maria.command.CommandAddTodo;
import maria.command.CommandExit;
import maria.command.CommandFindTask;
import maria.command.CommandListAllTasks;
import maria.command.CommandMarkTask;
import maria.command.CommandRemoveTask;
import maria.command.CommandUnknown;
import maria.command.CommandUnmarkTask;

/**
 * Parses commands.
 */
public class Parser {

    public static Command parse(String commandStr) {

        try {

            String[] commandStrArr = commandStr.split(" ");
            String commandMain = commandStrArr[0];

            switch (commandMain) {
            case "bye":
                return new CommandExit();
            case "todo": {
                String name = commandStrArr[1];
                boolean done = Boolean.parseBoolean(commandStrArr[2]);
                return new CommandAddTodo(name, done);
            }
            case "deadline": {
                String name = commandStrArr[1];
                boolean done = Boolean.parseBoolean(commandStrArr[2]);
                LocalDate deadline = LocalDate.parse(commandStrArr[3]);
                return new CommandAddDeadline(name, done, deadline);
            }
            case "event": {
                String name = commandStrArr[1];
                boolean done = Boolean.parseBoolean(commandStrArr[2]);
                LocalDate start = LocalDate.parse(commandStrArr[3]);
                LocalDate end = LocalDate.parse(commandStrArr[4]);
                return new CommandAddEvent(name, done, start, end);
            }
            case "list":
                return new CommandListAllTasks();
            case "mark": {
                int index = Integer.parseInt(commandStrArr[1]);
                return new CommandMarkTask(index);
            }
            case "unmark": {
                int index = Integer.parseInt(commandStrArr[1]);
                return new CommandUnmarkTask(index);
            }
            case "delete": {
                int index = Integer.parseInt(commandStrArr[1]);
                return new CommandRemoveTask(index);
            }
            case "find": {
                String searchString = commandStrArr[1];
                return new CommandFindTask(searchString);
            }
            default:
                return new CommandUnknown();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new CommandUnknown();
        }

    }

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
