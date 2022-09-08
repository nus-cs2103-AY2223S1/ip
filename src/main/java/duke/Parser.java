package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * deals with making sense of the user command, as well as processing what needs to be done
 *
 * @author eugeneleong
 * @version 1.0
 */
public class Parser {

    /**
     * Returns the type of command that the user has inputted
     * @param input by the user
     * @return type of command
     */
    public static String getCommandType(String input) {
        if (input.equals("list")) {
            return "LIST";
        } else if (input.startsWith("mark ")) {
            return "MARK";
        } else if (input.startsWith("unmark ")) {
            return "UNMARK";
        } else if (input.startsWith("todo ")) {
            return "TODO";
        } else if (input.startsWith("deadline ")) {
            return "DEADLINE";
        } else if (input.startsWith("event ")) {
            return "EVENT";
        } else if (input.startsWith("delete ")) {
            return "DELETE";
        } else if (input.startsWith("find ")) {
            return "FIND";
        } else if (input.equals("bye")) {
            return "BYE";
        } else { // to handle unknown inputs, e.g. 'blah', 'todo'
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Processes the command to be done, and decides whether to end the job
     * @param command description of command
     * @param commandType type of command
     * @param tasks current tasks to do
     * @param ui to say bye when job is done
     * @return boolean isExit
     */
    public static String process(String command, String commandType, TaskList tasks, Ui ui) {
        switch(commandType) {
        case "LIST":
            return ui.printTasks(tasks);
        case "MARK":
            int markIdx = Integer.parseInt(command.substring(5)) - 1;
            tasks.mark(markIdx);
            assert tasks.getTask(markIdx).getStatusIcon().equals("X"); // For A-Assertions
            return ui.printMarked(tasks.getTask(markIdx));
        case "UNMARK":
            int unmarkIdx = Integer.parseInt(command.substring(7)) - 1;
            tasks.unmark(unmarkIdx);
            assert tasks.getTask(unmarkIdx).getStatusIcon().equals(" "); // For A-Assertions
            return ui.printUnmarked(tasks.getTask(unmarkIdx));
        case "TODO":
            assert command.startsWith("todo"); // For A-Assertions
            Task tD = new ToDo(command.substring(5));
            tasks.add(tD);
            return ui.printAddedTask(tD, tasks.getSize());
        case "DEADLINE":
            assert command.startsWith("deadline"); // For A-Assertions
            String dlAction = command.substring(9, command.indexOf("/") - 1);
            Task dl = new Deadline(dlAction, formatEventTime(command));
            tasks.add(dl);
            return ui.printAddedTask(dl, tasks.getSize());
        case "EVENT":
            assert command.startsWith("event"); // For A-Assertions
            String eAction = command.substring(6, command.indexOf("/") - 1);
            Task ev = new Event(eAction, formatEventTime(command));
            tasks.add(ev);
            return ui.printAddedTask(ev, tasks.getSize());
        case "DELETE":
            int deleteIdx = Integer.parseInt(command.substring(7)) - 1;
            Task taskToDelete = tasks.getTask(deleteIdx);
            tasks.delete(deleteIdx);
            return ui.printDeletedTask(taskToDelete, tasks.getSize());
        case "FIND":
            String keyword = command.substring(5);
            return tasks.findTask(keyword);
        case "BYE":
            System.exit(0);
            return null;
        default:
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Formats the event date and timing nicely as a LocalDateTime type
     * @param input time in String
     * @return LocalDateTime
     */
    public static LocalDateTime formatEventTime(String input) {
        try {
            String time = input.substring(input.indexOf("/") + 4); // Format: dd/MM/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException dtpe) {
            System.out.println("Please give me a correct time/date! :(");
            return null;
        }
    }
}
