package doke;

import javafx.util.Pair;

/**
 * A class to represent the making sense of the inputted text.
 *
 * @author Stevan Gerard Gunawan
 */
public class Parser {

    protected boolean isCorrectTimeSignifier(String[] strings, int i) {
        return (!strings[i].equals("/at") && strings[0].equals("event"))
                || (!strings[i].equals("/by") && strings[0].equals("deadline"));
    }

    protected Pair<String, String> getDescriptionTimePair(String[] strings) {
        String word = "";
        String time = "";
        String action = strings[0];
        int length = strings.length;
        int i = 1;

        while (i < length && isCorrectTimeSignifier(strings, i)) {
            if (i == 1) {
                word = word + strings[i];
            } else {
                word = word + " " + strings[i];
            }
            i++;
        }

        if (i != length) {
            time = strings[++i];
            i++;
            while (i < length) {
                time += " " + strings[i];
                i++;
            }
        }

        Pair<String, String> pair = new Pair<>(action, time);
        return pair;
    }

    /**
     * Return a boolean to signify whether the doke program should end or not.
     * It processes the string to see if it is a valid create and add,
     * delete, mark, unmark, bye, list or otherwise, invalid command.
     *
     * @param taskList the taskList to be modified when needed.
     * @param storage the storage to be modified when needed.
     * @param input the text to be processed.
     * @param ui to print out messages
     * @return boolean to signify whether the doke program should end or not.
     */
    public boolean processString(TaskList taskList, Storage storage, String input, Ui ui) {
        String[] strings = input.split(" ");
        String action = strings[0];
        if (input.equals("bye")) {
            return Command.byeCommand();
        } else if (input.equals("list")) {
            return Command.listCommand(taskList, ui);
        } else if (action.equals("todo")) {
            return Command.addToDoCommand(taskList, ui, storage, input.substring(5));
        } else if (action.equals("deadline")) {
            return Command.addDeadlineCommand(taskList, ui, storage, getDescriptionTimePair(strings));
        } else if (action.equals("event")) {
            return Command.addEventCommand(taskList, ui, storage, getDescriptionTimePair(strings));
        } else if (action.equals("mark")) {
            return Command.markCommand(taskList, ui, storage, Miscellaneous.toInt(strings[1]));
        } else if (action.equals("unmark")) {
            return Command.unmarkCommand(taskList, ui, storage, Miscellaneous.toInt(strings[1]));
        } else if (action.equals("delete")) {
            return Command.deleteCommand(taskList, ui, storage, Miscellaneous.toInt(strings[1]));
        } else if (action.equals("find")) {
            return Command.findCommand(taskList, ui, input);
        } else {
            try {
                throw new DokeException();
            } catch (DokeException d) {
                ui.printOut(d.toString());
            }
            return true;
        }
    }
}
