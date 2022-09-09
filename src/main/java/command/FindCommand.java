package command;

import duke.Storage;
import duke.Ui;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Represent a find command
 */
public class FindCommand extends Command {
    private String cmd;

    public FindCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a find command
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        StringBuilder output = new StringBuilder("Ok! Here are some things I found that matched your description:\n");

        Pattern intPattern = Pattern.compile("\\d+");
        Pattern negativeintPattern = Pattern.compile("-");

        assert negativeintPattern.matcher(cmd).matches() : "Please enter a positive task number";

        if (intPattern.matcher(cmd).matches()) {
            assert Integer.parseInt(cmd) > tasklist.size(): "Please enter a number within your task number";
            output.append(String.format("List %d: ", Integer.parseInt(cmd)) + tasklist.get(Integer.parseInt(cmd)).toString());
            return output.toString();
        }

        for (int j = 0; j < tasklist.size(); j++) {
            if (tasklist.get(j).toString().contains(cmd)) {
                output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
                output.append("\n");
            }
        }
        return output.toString();
    }
}
