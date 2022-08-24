package parser;

import duke.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the processing of user input and commands the Duke to do specific tasks.
 */
public class Parser {

    protected static final String[] DATE_PATTERNS = {
            "yyyy-M-d HH:mm", "yyyy/M/d HH:mm", "yyyy M d HH:mm",
            "d-M-yyyy HH:mm", "d/M/yyyy HH:mm", "d M yyyy HH:mm",
            "yyyy-MM-d HH:mm", "yyyy/MM/d HH:mm", "yyyy MM d HH:mm",
            "d-MM-yyyy HH:mm", "d/MM/yyyy HH:mm", "d MM yyyy HH:mm",
            "yyyy-MMM-d HH:mm", "yyyy/MMM/d HH:mm", "yyyy MMM d HH:mm",
            "d-MMM-yyyy HH:mm", "d/MMM/yyyy HH:mm", "d MMM yyyy HH:mm",
            "yyyy-MMMM-d HH:mm", "yyyy/MMMM/d HH:mm", "yyyy MMMM d HH:mm",
            "d-MMMM-yyyy HH:mm", "d/MMMM/yyyy HH:mm", "d MMMM yyyy HH:mm",
            "yyyy-M-dd HH:mm", "yyyy/M/dd HH:mm", "yyyy M dd HH:mm",
            "dd-M-yyyy HH:mm", "dd/M/yyyy HH:mm", "dd M yyyy HH:mm",
            "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy MM dd HH:mm",
            "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm", "dd MM yyyy HH:mm",
            "yyyy-MMM-dd HH:mm", "yyyy/MMM/dd HH:mm", "yyyy MMM dd HH:mm",
            "dd-MMM-yyyy HH:mm", "dd/MMM/yyyy HH:mm", "dd MMM yyyy HH:mm",
            "yyyy-MMMM-dd HH:mm", "yyyy/MMMM/dd HH:mm", "yyyy MMMM dd HH:mm",
            "dd-MMMM-yyyy HH:mm", "dd/MMMM/yyyy HH:mm", "dd MMMM yyyy HH:mm"
    };

    /**
     * Reads user input and executes task on the {@code taskList}.
     *
     * @param input String line that the user inputs.
     * @param taskList TaskList object at the moment when the method is called.
     * @return Response line of the UI.
     */
    public String processInput(String input, TaskList taskList) {
        String header;
        String line;
        String footer;
        String response;
        Task task;
        try {
            String[] split = input.split("\\s+", 2);
            if (input.matches("\\s*")) {
                throw DukeException.DukeEmptyInputException();
            }
            String command = split[0];
            switch (command) {
                case "todo": case "event": case "deadline":
                    try {
                        String name = split[1];
                        if (command.equals("todo")) {
                            task = new Todo(name);
                        } else if (command.equals("deadline")) {
                            String[] details = name.split("\\s+/by\\s+");
                            task = new Deadline(details[0], this.parseTime(details[1]));
                        } else {
                            String[] details = name.split("\\s+/at\\s+");
                            task = new Event(details[0], this.parseTime(details[1]));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw DukeException.DukeInvalidFormatException();
                    }
                    taskList.addTask(task);
                    header = "Got it. I've added this task:";
                    line = String.format("  %s", task);
                    footer = String.format("Now you have %s task in the list", taskList.getSize());
                    response = String.format("%s\n%s\n%s", header, line, footer);
                    break;
                case "mark":
                    try {
                        if (split.length == 2) {
                            int num = Integer.parseInt(split[1]);
                            task = taskList.getTask(num);
                            task.mark();
                            header = "Nice! I've marked this task as done:";
                            line = String.format("  %s", task);
                            response = String.format("%s\n%s", header, line);
                        } else {
                            throw DukeException.DukeInvalidIndexException();
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw DukeException.DukeInvalidIndexException();
                    }
                    break;
                case "unmark":
                    try {
                        if (split.length == 2) {
                            int num = Integer.parseInt(split[1]);
                            task = taskList.getTask(num);
                            task.unmark();
                            header = "OK, I've marked this task as not done yet:";
                            line = String.format("  %s", task);
                            response = String.format("%s\n%s", header, line);
                        } else {
                            throw DukeException.DukeInvalidIndexException();
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw DukeException.DukeInvalidIndexException();
                    }
                    break;
                case "delete":
                    try {
                        if (split.length == 2) {
                            int num = Integer.parseInt(split[1]);
                            task = taskList.getTask(num);
                            taskList.removeTask(num);
                            header = "Noted. I've removed this task:";
                            line = String.format("  %s", task.toString());
                            response = String.format("%s\n%s", header, line);
                        } else {
                            throw DukeException.DukeInvalidIndexException();
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw DukeException.DukeInvalidIndexException();
                    }
                    break;
                case "bye":
                    if (input.equals("bye")) {
                        response = "Bye. Hope to see you again soon!";
                    } else {
                        throw DukeException.DukeUnknownCommandException();
                    }
                    break;
                case "list":
                    if (input.equals("list")) {
                        if (taskList.getSize() > 0) {
                            header = "Here are the tasks in your list";
                            response = String.format("%s\n%s", header, taskList);
                        } else {
                            response = "There are no tasks in your list";
                        }
                    } else {
                        throw DukeException.DukeUnknownCommandException();
                    }
                    break;
                default:
                    throw DukeException.DukeUnknownCommandException();
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Reads user input and executes task on the {@code taskList}.
     *
     * @param input String line that the user inputs.
     * @param taskList TaskList object at the moment when the method is called.
     * @return Response line of the UI.
     * @throws DukeException
     */
    public LocalDateTime parseTime(String date) throws DukeException {
        for (String pattern : DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
            }
        }
        throw DukeException.DukeInvalidDateFormatException();
    }
}