package duke;

import java.util.Scanner;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.UnmarkCommand;
import duke.command.ShowListCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;

public class Parser {
    //Exception Messages
    private static final String NO_DESC_TODO = "☹ OOPS!!! Description of the todo can't be empty.";
    private static final String NO_DESC_DEADLINE = "☹ OOPS!!! Description of the deadline can't be empty.";
    private static final String NO_DESC_EVENT = "☹ OOPS!!! Description of the event can't be empty.";

    private static final String NO_DATE_DEADLINE = "☹ OOPS!!! Date/time of the deadline can't be empty.";
    private static final String NO_DATE_EVENT = "☹ OOPS!!! Date/time of the event can't be empty.";
    private static final String NO_INDEX = "☹ OOPS!!! Index of the task to be marked/unmarked/deleted can't be empty.";

    private static final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static Command parse(String command) throws DukeException {
        Scanner sc = new Scanner(command);

        if (sc.hasNext("mark")) {
            String mark = sc.next();
            if (!sc.hasNextInt()) {
                throw new DukeException(NO_INDEX);
            } else {
                int i = sc.nextInt();
                sc.close();
                return new MarkCommand(i);
            }
        } else if (sc.hasNext("unmark")) {
            String unmark = sc.next();
            if (!sc.hasNextInt()) {
                throw new DukeException(NO_INDEX);
            } else {
                int i = sc.nextInt();
                sc.close();
                return new UnmarkCommand(i);
            }
        } else if (sc.hasNext("deadline")) {
            sc.useDelimiter("deadline\\s*|\\s*/by\\s*");
            if (!sc.hasNext()) {
                throw new DukeException(NO_DESC_DEADLINE);
            }
            String description = sc.next();
            if (!sc.hasNext()) {
                throw new DukeException(NO_DATE_DEADLINE);
            }
            String by = sc.next();
            sc.close();
            return new AddCommand("deadline", description, by);
        } else if (sc.hasNext("event")) {
            sc.useDelimiter("event\\s*|\\s*/at\\s*");
            if (!sc.hasNext()) {
                throw new DukeException(NO_DESC_EVENT);
            }
            String description = sc.next();
            if (!sc.hasNext()) {
                throw new DukeException(NO_DATE_EVENT);
            }
            String at = sc.next();
            sc.close();
            return new AddCommand("event", description, at);
        } else if (sc.hasNext("todo")) {
            sc.useDelimiter("todo\\s*");
            if (!sc.hasNext()) {
                throw new DukeException(NO_DESC_TODO);
            }
            String description = sc.next();
            sc.close();
            return new AddCommand("todo", description, null);
        } else if (sc.hasNext("delete")) {
            sc.useDelimiter("delete\\s*");
            if (!sc.hasNextInt()) {
                throw new DukeException(NO_INDEX);
            } else {
                int i = sc.nextInt();
                sc.close();
                return new DeleteCommand(i);
            }
        } else {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ShowListCommand();
            } else {
                throw new DukeException(UNKNOWN_COMMAND);
            }
        }
    }
}
