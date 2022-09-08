package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

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
        Scanner sc = new Scanner(input);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(markTask(d, sc.next(), isLoading) + "\n");
        }
        return sb.toString();
    }
    private String markTask(Duke d, String input, boolean isLoading) {
        try {
            int index = Integer.parseInt(input) - 1;
            return d.markTask(index)
                    + saveCommand(d, this.MARK + this.SPACE + input + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.ERROR_MESSAGE;
        } catch (IndexOutOfBoundsException e) {
            return "I cannot mark a task that does not exist!";
        }
    }
    private String unmark(Duke d, String input, boolean isLoading) {
        Scanner sc = new Scanner(input);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(unmarkTask(d, sc.next(), isLoading) + "\n");
        }
        return sb.toString();
    }
    private String unmarkTask(Duke d, String input, boolean isLoading) {
        try {
            int index = Integer.parseInt(input) - 1;
            return d.unmarkTask(index)
                    + saveCommand(d, this.UNMARK + this.SPACE + input + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.ERROR_MESSAGE;
        } catch (IndexOutOfBoundsException e) {
            return "I cannot unmark a task that does not exist!";
        }
    }
    private String todo(Duke d, String input, boolean isLoading) {
        if (input.isBlank()) {
            return "The task is empty, what do you really mean?";
        } else {
            return d.addTodo(input)
                    + saveCommand(d, this.TODO + this.SPACE + input + "\n", isLoading);
        }
    }
    private String deadline(Duke d, String input, boolean isLoading) {
        // deadline flag is not in command
        if (!input.contains(this.SPACE + this.DEADLINE_BY + this.SPACE)) {
            return "Either the task is empty or the deadline is empty, or both\n"
                    + "The syntax is: deadline <taskName> /by <deadline>\n"
                    + "If there is no deadline, please add it as a todo instead.";
        }
        // separate task and deadline and check if either is empty
        String task = input.substring(0, input.indexOf(this.DEADLINE_BY) - this.SPACE.length());
        String deadline = input.substring(input.indexOf(this.DEADLINE_BY)
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
                    + saveCommand(d, this.DEADLINE + this.SPACE + input + "\n", isLoading);
        } catch (DateTimeParseException e) {
            return d.addDeadline(task, deadline)
                    + saveCommand(d, this.DEADLINE + this.SPACE + input + "\n", isLoading);
        }
    }
    private String event(Duke d, String input, boolean isLoading) {
        // event time flag not found
        if (!input.contains(this.SPACE + this.EVENT_AT + this.SPACE)) {
            return "Either the task is empty or the event time is empty, or both\n"
                    + "The syntax is: event <eventName> /at <eventTime>";
        }
        // separate event and time and check if either is empty
        String event = input.substring(0, input.indexOf(this.EVENT_AT) - this.SPACE.length());
        String time = input.substring(input.indexOf(this.EVENT_AT) + this.EVENT_AT.length()
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
                    + saveCommand(d, this.EVENT + this.SPACE + input + "\n", isLoading);
        } catch (DateTimeParseException e) {
            return d.addEvent(event, time)
                    + saveCommand(d, this.EVENT + this.SPACE + input + "\n", isLoading);
        }
    }
    private String delete(Duke d, String input, boolean isLoading) {
        Scanner sc = new Scanner(input);
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // deletion must be started from the largest index to preserve order
        while (sc.hasNext()) {
            try {
                int index = Integer.parseInt(sc.next()) - 1;
                if (!pq.contains(index)) {
                    pq.add(index);
                }
            } catch (NumberFormatException e) {
                sb.append(this.ERROR_MESSAGE);
            }
        }
        // start deletion process
        while (!pq.isEmpty()) {
            sb.append(deleteTask(d, pq.remove(), isLoading) + "\n");
        }
        return sb.toString();
    }
    private String deleteTask(Duke d, int input, boolean isLoading) {
        try {
            return d.deleteTask(input)
                    + saveCommand(d, this.DELETE + this.SPACE + (input + 1) + "\n", isLoading);
        } catch (NumberFormatException e) {
            return this.ERROR_MESSAGE;
        } catch (IndexOutOfBoundsException e) {
            return "I cannot delete a task that does not exist!";
        }
    }
    private String findTask(Duke d, String input) {
        if (input.isBlank()) {
            return "The query is empty, what do you really mean?";
        } else {
            return d.findTask(input);
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
        assert d.getIsRunning() : "Duke should be running.";
        if (input.equals(this.BYE)) {
            return d.stopBot();
        } else if (input.equals(this.LIST)) {
            return d.listToString();
        } else if (input.startsWith(this.MARK + this.SPACE)) {
            return this.mark(d, input.substring((this.MARK + this.SPACE).length()), isLoading);
        } else if (input.startsWith(this.UNMARK + this.SPACE)) {
            return this.unmark(d, input.substring((this.UNMARK + this.SPACE).length()), isLoading);
        } else if (input.startsWith(this.TODO + this.SPACE)) {
            return this.todo(d, input.substring((this.TODO + this.SPACE).length()), isLoading);
        } else if (input.startsWith(this.DEADLINE + this.SPACE)) {
            return this.deadline(d, input.substring((this.DEADLINE + this.SPACE).length()), isLoading);
        } else if (input.startsWith(this.EVENT + this.SPACE)) {
            return this.event(d, input.substring((this.EVENT + this.SPACE).length()), isLoading);
        } else if (input.startsWith(this.DELETE + this.SPACE)) {
            return this.delete(d, input.substring((this.DELETE + this.SPACE).length()), isLoading);
        } else if (input.startsWith(this.FIND + this.SPACE)) {
            return this.findTask(d, input.substring((this.FIND + this.SPACE).length()));
        }
        return this.ERROR_MESSAGE;
    }
}
