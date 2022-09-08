package command;

import duke.Storage;
import duke.Ui;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

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
        for (int j = 0; j < tasklist.size(); j++) {
            if (tasklist.get(j).toString().contains(cmd)) {
                output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
                output.append("\n");
            }
        }
        return output.toString();
    }
}
