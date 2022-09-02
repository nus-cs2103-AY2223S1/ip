package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to parse inputs into actions to be taken.
 */
public class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String DEADLINE_BY = "/by";
    private static final String EVENT = "event";
    private static final String EVENT_AT = "/at";
    private static final String DELETE = "delete";
    private static final String SPACE = " ";
    private static final String FIND = "find";
    private static final String ERROR_MESSAGE = "Sorry, I cannot understand what you exactly mean.\n"
            + "Certain commands require input parameters.";
    private String saveCommand(Duke d, String input, boolean isLoading) {
        try {
            d.getStorage().writeToFile(input, isLoading);
            return "";
        } catch (IOException e) {
            return "\nThis command could not be saved due to an unknown error.";
        }
    }
    private String mark(Duke d, String input, boolean isLoading) {
        try {
            String parameter = input.substring((this.MARK + this.SPACE).length());
            int param = Integer.parseInt(parameter);
            return d.markTask(param)
                    + saveCommand(d, this.MARK + this.SPACE + param + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.ERROR_MESSAGE;
        }
    }
    private String unmark(Duke d, String input, boolean isLoading) {
        try {
            String parameter = input.substring((this.UNMARK + this.SPACE).length());
            int param = Integer.parseInt(parameter);
            return d.unmarkTask(param)
                    + saveCommand(d, this.UNMARK + this.SPACE + param + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.ERROR_MESSAGE;
        }
    }
    private String todo(Duke d, String input, boolean isLoading) {
        String s = input.substring((this.TODO + this.SPACE).length());
        if (s.isBlank()) {
            return "The task is empty, what do you really mean?";
        } else {
            return d.addTodo(s) + saveCommand(d, this.TODO + this.SPACE + s + "\n", isLoading);
        }
    }
    private String deadline(Duke d, String input, boolean isLoading) {
        String s = input.substring((this.DEADLINE + this.SPACE).length());
        // deadline flag is not in command
        if (!s.contains(this.SPACE + this.DEADLINE_BY + this.SPACE)) {
            return "Either the task is empty or the deadline is empty, or both\n"
                    + "The syntax is: deadline <taskName> /by <deadline>\n"
                    + "If there is no deadline, please add it as a todo instead.";
        }
        // separate task and deadline and check if either is empty
        String task = s.substring(0, s.indexOf(this.DEADLINE_BY) - this.SPACE.length());
        String deadline = s.substring(s.indexOf(this.DEADLINE_BY)
                + this.DEADLINE_BY.length() + this.SPACE.length());
        if (task.isBlank()) {
            return "The task is empty, what do you really mean?";
        } else if (deadline.isBlank()) {
            return "The deadline is empty, do you mean it has no deadline?\n"
                    + "If it is, please add it as a todo instead.";
        }
        // both task and deadline exist
        try {
            LocalDate date = LocalDate.parse(deadline);
            return d.addDeadline(task, date)
                    + saveCommand(d, this.DEADLINE + this.SPACE + s + "\n", isLoading);
        } catch (DateTimeParseException e) {
            return d.addDeadline(task, deadline)
                    + saveCommand(d, this.DEADLINE + this.SPACE + s + "\n", isLoading);
        }
    }
    private String event(Duke d, String input, boolean isLoading) {
        String s = input.substring((this.EVENT + this.SPACE).length());
        // event time flag not found
        if (!s.contains(this.SPACE + this.EVENT_AT + this.SPACE)) {
            return "Either the task is empty or the event time is empty, or both\n"
                    + "The syntax is: event <eventName> /at <eventTime>";
        }
        // separate event and time and check if either is empty
        String event = s.substring(0, s.indexOf(this.EVENT_AT) - this.SPACE.length());
        String time = s.substring(s.indexOf(this.EVENT_AT) + this.EVENT_AT.length()
                + this.SPACE.length());
        if (event.isBlank()) {
            return "The event is empty, what do you really mean?";
        } else if (time.isBlank()) {
            return "The time is empty, do you mean it never starts?";
        }
        // both event and time exist
        try {
            LocalDate t = LocalDate.parse(time);
            return d.addEvent(event, t)
                    + saveCommand(d, this.EVENT + this.SPACE + s + "\n", isLoading);
        } catch (DateTimeParseException e) {
            return d.addEvent(event, time)
                    + saveCommand(d, this.EVENT + this.SPACE + s + "\n", isLoading);
        }
    }
    private String delete(Duke d, String input, boolean isLoading) {
        try {
            String parameter = input.substring((this.DELETE + this.SPACE).length());
            int param = Integer.parseInt(parameter);
            return d.deleteTask(param)
                    + saveCommand(d, this.DELETE + this.SPACE + parameter + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.ERROR_MESSAGE;
        }
    }
    private String find(Duke d, String input) {
        String s = input.substring((this.FIND + this.SPACE).length());
        if (s.isBlank()) {
            return "The query is empty, what do you really mean?";
        } else {
            return d.find(s);
        }
    }

    /**
     * Parses an input to decode it.
     *
     * @param d Duke bot to be instructed.
     * @param input Input to be parsed.
     * @param isLoading Boolean to indicate if the Duke bot is loading.
     * @return Associated message from Duke.
     */
    public String parse(Duke d, String input, boolean isLoading) {
        if (input.equals(this.BYE)) {
            return d.stopBot();
        } else if (input.equals(this.LIST)) {
            return d.getList();
        } else if (input.startsWith(this.MARK + this.SPACE)) {
            return this.mark(d, input, isLoading);
        } else if (input.startsWith(this.UNMARK + this.SPACE)) {
            return this.unmark(d, input, isLoading);
        } else if (input.startsWith(this.TODO + this.SPACE)) {
            return this.todo(d, input, isLoading);
        } else if (input.startsWith(this.DEADLINE + this.SPACE)) {
            return this.deadline(d, input, isLoading);
        } else if (input.startsWith(this.EVENT + this.SPACE)) {
            return this.event(d, input, isLoading);
        } else if (input.startsWith(this.DELETE + this.SPACE)) {
            return this.delete(d, input, isLoading);
        } else if (input.startsWith(this.FIND + this.SPACE)) {
            return this.find(d, input);
        }
        return this.ERROR_MESSAGE;
    }
}
