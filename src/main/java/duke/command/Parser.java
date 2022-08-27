package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    public static Command parse(String command) {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ModifyCommand(ModifyCommand.CommandType.LIST);
        } else if (command.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                return new ModifyCommand(ModifyCommand.CommandType.DONE, taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid number for this command");
            }
        } else if (command.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                return new ModifyCommand(ModifyCommand.CommandType.UNDONE, taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid number for this command");
            }
        } else if (command.startsWith("todo")) {
            try {
                String description = getSecondHalf(command);
                return new AddCommand(new Todo(description));
            } catch (Exception e) {
                e.printStackTrace();
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] full = getDescriptionAndDate(getSecondHalf(command), " /by ");
                String description = full[0];
                String deadline = full[1];
                return new AddCommand(new Deadline(description, deadline));
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please format deadline request correctly.");
            }
        } else if (command.startsWith("event")) {
            try {
                String[] full = getDescriptionAndDate(getSecondHalf(command), " /at ");
                String description = full[0];
                String at = full[1];
                return new AddCommand(new Event(description, at));
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please format event request correctly.");
            }
        } else if (command.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
                return new DeleteCommand(taskNumber);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Please provide a number for this command");
            }
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static String getSecondHalf(String command) {
        return command.split(" ", 2)[1];
    }

    private static String[] getDescriptionAndDate(String secondHalf, String delimiter) {
        return secondHalf.split(delimiter);
    }

}
