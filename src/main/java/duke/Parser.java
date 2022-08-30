package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to parse inputs into actions to be taken.
 */
public class Parser {
    private String bye = "bye";
    private String list = "list";
    private String mark = "mark";
    private String unmark = "unmark";
    private String todo = "todo";
    private String deadline = "deadline";
    private String deadlineBy = "/by";
    private String event = "event";
    private String eventAt = "/at";
    private String delete = "delete";
    private String space = " ";
    private String find = "find";
    private String errorMessage = "Sorry, I cannot understand what you exactly mean.\n"
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
            String parameter = input.substring((this.mark + this.space).length());
            int param = Integer.parseInt(parameter);
            return d.markTask(param)
                    + saveCommand(d, this.mark + this.space + param + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.errorMessage;
        }
    }
    private String unmark(Duke d, String input, boolean isLoading) {
        try {
            String parameter = input.substring((this.unmark + this.space).length());
            int param = Integer.parseInt(parameter);
            return d.unmarkTask(param)
                    + saveCommand(d, this.unmark + this.space + param + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.errorMessage;
        }
    }
    private String todo(Duke d, String input, boolean isLoading) {
        String s = input.substring((this.todo + this.space).length());
        if (s.isBlank()) {
            return "The task is empty, what do you really mean?";
        } else {
            return d.addTodo(s) + saveCommand(d, this.todo + this.space + s + "\n", isLoading);
        }
    }
    private String deadline(Duke d, String input, boolean isLoading) {
        String s = input.substring((this.deadline + this.space).length());
        // deadline flag is not in command
        if (!s.contains(this.space + this.deadlineBy + this.space)) {
            if (s.startsWith(this.deadlineBy)) {
                return "The task is empty, what do you really mean?";
            } else {
                return "The deadline is empty, do you mean it has no deadline?\n"
                        + "If it is, please add it as a todo instead.";
            }
        } else {
            String task = s.substring(0, s.indexOf(this.deadlineBy) - this.space.length());
            String deadline = s.substring(s.indexOf(this.deadlineBy)
                    + this.deadlineBy.length() + this.space.length());
            if (task.isBlank()) {
                return "The task is empty, what do you really mean?";
            } else if (deadline.isBlank()) {
                return "The deadline is empty, do you mean it has no deadline?\n"
                        + "If it is, please add it as a todo instead.";
            } else {
                try {
                    LocalDate date = LocalDate.parse(deadline);
                    return d.addDeadline(task, date)
                            + saveCommand(d, this.deadline + this.space + s + "\n", isLoading);
                } catch (DateTimeParseException e) {
                    return d.addDeadline(task, deadline)
                            + saveCommand(d, this.deadline + this.space + s + "\n", isLoading);
                }
            }
        }
    }
    private String event(Duke d, String input, boolean isLoading) {
        String s = input.substring((this.event + this.space).length());
        // event time flag not found
        if (!s.contains(space + eventAt + space)) {
            if (s.startsWith(eventAt)) {
                return "The event is empty, what do you really mean?";
            } else {
                return "The time is empty, do you mean it never starts?";
            }
        } else {
            String event = s.substring(0, s.indexOf(eventAt) - space.length());
            String time = s.substring(s.indexOf(eventAt) + eventAt.length()
                    + space.length());
            if (event.isBlank()) {
                return "The event is empty, what do you really mean?";
            } else if (time.isBlank()) {
                return "The time is empty, do you mean it never starts?";
            } else {
                try {
                    LocalDate t = LocalDate.parse(time);
                    return d.addEvent(event, t)
                            + saveCommand(d, this.event + this.space + s + "\n", isLoading);
                } catch (DateTimeParseException e) {
                    return d.addEvent(event, time)
                            + saveCommand(d, this.event + this.space + s + "\n", isLoading);
                }
            }
        }
    }
    private String delete(Duke d, String input, boolean isLoading) {
        try {
            String parameter = input.substring((this.delete + this.space).length());
            int param = Integer.parseInt(parameter);
            return d.deleteTask(param)
                    + saveCommand(d, this.delete + this.space + parameter + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.errorMessage;
        }
    }
    private String find(Duke d, String input) {
        String s = input.substring((this.find + this.space).length());
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
        if (input.equals(this.bye)) {
            return d.stopBot();
        } else if (input.equals(this.list)) {
            return d.getList();
        } else if (input.startsWith(this.mark + this.space)) {
            return this.mark(d, input, isLoading);
        } else if (input.startsWith(this.unmark + this.space)) {
            return this.unmark(d, input, isLoading);
        } else if (input.startsWith(this.todo + this.space)) {
            return this.todo(d, input, isLoading);
        } else if (input.startsWith(this.deadline + this.space)) {
            return this.deadline(d, input, isLoading);
        } else if (input.startsWith(this.event + this.space)) {
            return this.event(d, input, isLoading);
        } else if (input.startsWith(this.delete + this.space)) {
            return this.delete(d, input, isLoading);
        } else if (input.startsWith(this.find + this.space)) {
            return this.find(d, input);
        }
        return "Sorry, I cannot understand what you exactly mean.\n"
                + "Certain commands require input parameters.";
    }
}
