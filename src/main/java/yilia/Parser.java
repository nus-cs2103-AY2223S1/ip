package yilia;

import yilia.command.AddCommand;
import yilia.command.Command;
import yilia.command.DeleteCommand;
import yilia.command.ExitCommand;
import yilia.command.ListCommand;
import yilia.command.MarkCommand;
import yilia.command.UnmarkCommand;
import yilia.exception.DescriptionEmptyException;
import yilia.exception.NoIndexException;
import yilia.exception.TimeFormatException;
import yilia.exception.YiliaException;

public class Parser {
    public static Command parse(String text) throws YiliaException, NoIndexException,
            DescriptionEmptyException, TimeFormatException {
        String[] info = text.strip().split(" ", 2);
        if (info[0].equals("list")) {
            return new ListCommand(false);
        } else if (info[0].equals("delete")) {
            if (info.length == 1) {
                throw new NoIndexException("delete");
            }
            return new DeleteCommand(Integer.parseInt(info[1]));
        } else if (info[0].equals("mark")) {
            if (info.length == 1) {
                throw new NoIndexException("mark");
            }
            return new MarkCommand(Integer.parseInt(info[1]));
        } else if (info[0].equals("unmark")) {
            if (info.length == 1) {
                throw new NoIndexException("unmark");
            }
            return new UnmarkCommand(Integer.parseInt(info[1]));
        } else if (info[0].equals("deadline")) {
            if (info.length == 1) {
                throw new DescriptionEmptyException(Type.DEADLINE);
            }
            return new AddCommand(info[1], Type.DEADLINE);
        } else if (info[0].equals("event")) {
            if (info.length == 1) {
                throw new DescriptionEmptyException(Type.EVENT);
            }
            return new AddCommand(info[1], Type.EVENT);
        } else if (info[0].equals("todo")) {
            if (info.length == 1) {
                throw new DescriptionEmptyException(Type.TODO);
            }
            return new AddCommand(info[1], Type.TODO);
        } else if (info[0].equals("bye")) {
            return new ExitCommand();
        } else {
            throw new YiliaException();
        }
    }

    public static boolean parseStringToBoolean(String num) {
        return Integer.parseInt(num) == 1;
    }
}
