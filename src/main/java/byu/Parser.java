package byu;

import java.util.Locale;
import commands.*;
import exceptions.*;
import task.*;

public class Parser {

    public static Command parse(String fullCommand) {
        Command c = new NextCommand();
        try {
            Instruction i = getInstruction(fullCommand);
            String description = getDescription(i, fullCommand);
            switch(i) {
                case MARK:
                    c = prepareMark(description);
                    break;
                case UNMARK:
                    c = prepareUnmark(description);
                    break;
                case DEADLINE:
                    c = prepareDeadline(description);
                    break;
                case EVENT:
                    c = prepareEvent(description);
                    break;
                case TODO:
                    c = prepareToDo(description);
                    break;
                case DELETE:
                    c = prepareDelete(description);
                    break;
                case BYE:
                    c = new ByeCommand();
                    break;
                case LIST:
                    c = new ListCommand();
                    break;
            }
        } catch (DukeException e) {
            System.out.print(e.getMessage());
            c = new NextCommand();
        }
        return c;
    }

    private static Instruction getInstruction(String fullCommand) throws InvalidInstruction {
        try {
            String instruction = fullCommand.split(" ")[0];
            Instruction instruct = Instruction.valueOf(instruction.toUpperCase(Locale.ROOT));
            return instruct;
        } catch (IllegalArgumentException e) {
            throw new InvalidInstruction();
        }
    }

    private static String getDescription(Instruction i, String fullCommand) throws EmptyDescription {
        switch (i) {
            case MARK:
            case UNMARK:
            case DEADLINE:
            case TODO:
            case EVENT:
            case DELETE:
                String[] str = fullCommand.split(" ");
                if (str.length == 1) {
                    throw new EmptyDescription(i.name());
                } else {
                    return fullCommand.substring(str[0].length() + 1);
                }
            default:
                return "";
        }
    }

    private static MarkCommand prepareMark(String d) {
        //try {
            Integer index = Integer.valueOf(d);
            return new MarkCommand(index);
        //} catch (NumberFormatException e) {
            // throw
        //}
    }

    private static UnmarkCommand prepareUnmark(String d) {
        //try {
            Integer index = Integer.valueOf(d);
            return new UnmarkCommand(index);
        //} catch (NumberFormatException e) {
            // throw
        //}
    }

    private static AddCommand prepareEvent(String d) throws EmptyDescription {
        String[] substrings = d.split(" /at ");
        if (substrings.length == 1) {
            throw new EmptyDescription("Event");
        } else {
            Events e = new Events(substrings[0], substrings[1]);
            return new AddCommand(e);
        }
    }
    private static AddCommand prepareDeadline(String d) throws EmptyDescription {
        String[] substrings = d.split(" /by ");
        if (substrings.length == 1) {
            throw new EmptyDescription("Deadline");
        } else {
            Deadlines deadline = new Deadlines(substrings[0], substrings[1]);
            return new AddCommand(deadline);
        }
    }

    private static AddCommand prepareToDo(String d) {
        ToDos t = new ToDos(d);
        return new AddCommand(t);
    }

    private static DeleteCommand prepareDelete(String d) {
        //try {
            Integer index = Integer.valueOf(d);
            return new DeleteCommand(index);
        //} catch (NumberFormatException e) {
            // throw
        //}
    }


/*


} catch (DukeException e) {
            System.out.println(e.getMessage());
        while (!.equals("bye")) {
            String[] arr = response.split(" ");
            try {
                Instruction instruct = Instruction.valueOf(instruction.toUpperCase(Locale.ROOT));
                list.execute(instruct, response);
            } catch (IllegalArgumentException e) {
                DukeException de = new InvalidInstruction();
                System.out.println(de.getMessage());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                response = sc.nextLine();
            }
        }
    }

 */
}
