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

    /**
     * Parses an input to decode it.
     *
     * @param d Duke bot to be instructed.
     * @param input Input to be parsed.
     * @param isLoading Boolean to indicate if the Duke bot is loading.
     */
    public void parse(Duke d, String input, boolean isLoading) {
        if (input.equals(this.bye)) {
            System.out.println("Byebye! See you again soon!");
            d.stop();
        } else if (input.equals(this.list)) {
            d.getList();
        } else if (input.startsWith(this.mark + this.space)) {
            try {
                String parameter = input.substring((this.mark + this.space).length());
                int param = Integer.parseInt(parameter);
                d.markTask(param);
            } catch (NumberFormatException e) {
                System.err.println("Invalid task.");
            }
        } else if (input.startsWith(this.unmark + this.space)) {
            try {
                String parameter = input.substring((this.unmark + this.space).length());
                int param = Integer.parseInt(parameter);
                d.unmarkTask(param);
            } catch (NumberFormatException e) {
                System.err.println("Invalid task.");
            }
        } else if (input.startsWith(this.todo + this.space)) {
            String s = input.substring((this.todo + this.space).length());
            if (s.isBlank()) {
                System.out.println("The task is empty, what do you really mean?");
            } else {
                d.addTodo(s);
                try {
                    d.getStorage().writeToFile(this.todo + this.space + s + "\n", isLoading);
                } catch (IOException e) {
                    System.err.println("This command could not be saved due to an unknown error.");
                }
            }
        } else if (input.startsWith(this.deadline + this.space)) {
            String s = input.substring((this.deadline + this.space).length());
            if (!s.contains(this.space + this.deadlineBy + this.space)) {
                if (s.startsWith(this.deadlineBy)) {
                    System.out.println("The task is empty, what do you really mean?");
                } else {
                    System.out.println("The deadline is empty, do you mean it has no deadline?");
                    System.out.println("If it is, please add it as a todo instead.");
                }
            } else {
                String task = s.substring(0, s.indexOf(this.deadlineBy) - this.space.length());
                String deadline = s.substring(s.indexOf(this.deadlineBy) + this.deadlineBy.length()
                        + this.space.length());
                if (task.isBlank()) {
                    System.out.println("The task is empty, what do you really mean?");
                } else if (deadline.isBlank()) {
                    System.out.println("The deadline is empty, do you mean it has no deadline?");
                    System.out.println("If it is, please add it as a todo instead.");
                } else {
                    LocalDate date = null;
                    try {
                        date = LocalDate.parse(deadline);
                        d.addDeadline(task, date);
                        try {
                            d.getStorage().writeToFile(this.deadline + this.space + s + "\n", isLoading);
                        } catch (IOException e) {
                            System.err.println("This command could not be saved due to an unknown error.");
                        }
                    } catch (DateTimeParseException e) {
                        d.addDeadline(task, deadline);
                        try {
                            d.getStorage().writeToFile(this.deadline + this.space + s + "\n", isLoading);
                        } catch (IOException ex) {
                            System.err.println("This command could not be saved due to an unknown error.");
                        }
                    }
                }
            }
        } else if (input.startsWith(this.event + this.space)) {
            String s = input.substring((this.event + this.space).length());
            if (!s.contains(space + eventAt + space)) {
                if (s.startsWith(eventAt)) {
                    System.out.println("The event is empty, what do you really mean?");
                } else {
                    System.out.println("The time is empty, do you mean it never starts?");
                }
            } else {
                String event = s.substring(0, s.indexOf(eventAt) - space.length());
                String time = s.substring(s.indexOf(eventAt) + eventAt.length()
                        + space.length());
                if (event.isBlank()) {
                    System.out.println("The event is empty, what do you really mean?");
                } else if (time.isBlank()) {
                    System.out.println("The time is empty, do you mean it never starts?");
                } else {
                    LocalDate t = null;
                    try {
                        t = LocalDate.parse(time);
                        d.addEvent(event, t);
                        try {
                            d.getStorage().writeToFile(this.event + this.space + s + "\n", isLoading);
                        } catch (IOException e) {
                            System.err.println("This command could not be saved due to an unknown error.");
                        }
                    } catch (DateTimeParseException e) {
                        d.addEvent(event, time);
                        try {
                            d.getStorage().writeToFile(this.event + this.space + s + "\n", isLoading);
                        } catch (IOException ex) {
                            System.err.println("This command could not be saved due to an unknown error.");
                        }
                    }
                }
            }
        } else if (input.startsWith(this.delete + this.space)) {
            try {
                String parameter = input.substring((this.delete + this.space).length());
                int param = Integer.parseInt(parameter);
                d.deleteTask(param);
                try {
                    d.getStorage().writeToFile(this.delete + this.space + parameter + "\n", isLoading);
                } catch (IOException e) {
                    System.err.println("This command could not be saved due to an unknown error.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid task.");
            }
        } else {
            System.out.println("Sorry, I cannot understand what you exactly mean.");
            System.out.println("Certain commands require input parameters.");
        }
    }
}
