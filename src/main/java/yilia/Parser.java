package yilia;

import yilia.command.*;
import yilia.exception.*;

/**
 * Represents the parser to parse text to other meaning types.
 */
public class Parser {
    /**
     * parses the text into commands.
     *
     * @param text The text.
     * @return The command.
     * @throws YiliaException  If the command is unknown to Yilia.
     * @throws NoIndexException  If the index is not given by users.
     */
    public static Command parse(String text) throws DescriptionEmptyException, KeywordMissingException,
            NoIndexException, TimeFormatException, YiliaException {
        String[] info = text.strip().split(" ", 2);
        if (info[0].equals("list")) {
            return new ListCommand(false);
        } else if (info[0].equals("delete")) {
            if (info.length == 1) {
                throw new NoIndexException("delete");
            }
            String[] nums = info[1].strip().split(" ");
            int[] indices = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                indices[i] = Integer.parseInt(nums[i].strip());
            }
            return new DeleteCommand(indices);
        } else if (info[0].equals("mark")) {
            if (info.length == 1) {
                throw new NoIndexException("mark");
            }
            String[] nums = info[1].strip().split(" ");
            int[] indices = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                indices[i] = Integer.parseInt(nums[i].strip());
            }
            return new MarkCommand(indices);
        } else if (info[0].equals("unmark")) {
            if (info.length == 1) {
                throw new NoIndexException("unmark");
            }
            String[] nums = info[1].strip().split(" ");
            int[] indices = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                indices[i] = Integer.parseInt(nums[i].strip());
            }
            return new UnmarkCommand(indices);
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
        } else if (info[0].equals("find")) {
            if (info.length == 1) {
                throw new KeywordMissingException();
            }
            return new FindCommand(info[1]);
        } else if (info[0].equals("bye")) {
            return new ExitCommand();
        } else {
            throw new YiliaException();
        }
    }

    /**
     * parses the text into boolean values.
     *
     * @param num The text representing a number.
     * @return The boolean value parsed.
     */
    public static boolean parseStringToBoolean(String num) {
        return Integer.parseInt(num) == 1;
    }
}
