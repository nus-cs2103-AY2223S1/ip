package ted.parser;

import java.time.format.DateTimeParseException;
import ted.command.*;
import ted.exception.TedException;
import ted.task.*;

public class Parser {
    public static Command parse(String command) throws TedException{
        Command c;
        String[] temp = command.split(" ", 2);
        String action = temp[0];
        try {
            switch (action) {
                case "bye":
                    if (temp.length != 1) {
                        throw new TedException("Oh no, I don't understand T_T\n");
                    }
                    c = new ByeCommand();
                    break;
                case "list":
                    if (temp.length != 1) {
                        throw new TedException("Oh no, I don't understand T_T\n");
                    }
                    c = new ListCommand();
                    break;
                case "find":
                    c = new FindCommand(temp[1]);
                    break;
                case "mark":
                    if (temp.length == 1) {
                        throw new TedException("Oh no, please indicate task to mark T_T\n");
                    }
                    c = new MarkCommand(Integer.parseInt(temp[1]));
                    break;
                case "unmark":
                    if (temp.length == 1) {
                        throw new TedException("Oh no, please indicate task to unmark T_T\n");
                    }
                    c = new UnmarkCommand(Integer.parseInt(temp[1]));
                    break;
                case "delete":
                    if (temp.length == 1) {
                        throw new TedException("Oh no, please indicate task to delete T_T\n");
                    }
                    c = new DeleteCommand(Integer.parseInt(temp[1]));
                    break;
                case "todo":
                    if (temp.length == 1) {
                        throw new TedException("Oh no, please include task description T_T\n");
                    }
                    c = new AddCommand(new Todo(temp[1]));
                    break;
                case "deadline":
                    if (temp.length == 1) {
                        throw new TedException("Oh no, please include task description T_T\n");
                    }
                    String[] deadlineDesc = temp[1].split(" /by ", 2);
                    if (deadlineDesc.length == 1) {
                        throw new TedException("Oh no, please include both deadline description and time T_T\n");
                    }
                    c = new AddCommand(new Deadline(deadlineDesc[0], deadlineDesc[1]));
                    break;
                case "event":
                    if (temp.length == 1) {
                        throw new TedException("Oh no, please include task description T_T\n");
                    }
                    String[] eventDesc = temp[1].split(" /at ", 2);
                    if (eventDesc.length == 1) {
                        throw new TedException("Oh no, please include both event description and time T_T\n");
                    }
                    c = new AddCommand(new Event(eventDesc[0], eventDesc[1]));
                    break;
                default:
                    throw new TedException("Oh no, I don't understand T_T\n");
            }
        } catch (NumberFormatException e) {
            throw new TedException("Oh no, please indicate task to mark/unmark/delete with a number T_T\n");
        } catch (DateTimeParseException e) {
            throw new TedException("Oh no, please input the date in yyyy-mm-dd hh:mm format T_T\n");
        }

        return c;
    }
}
