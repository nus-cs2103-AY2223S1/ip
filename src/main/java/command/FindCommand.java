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
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {

        System.out.println("Ok! Here are some things I found that matched your description");
        for (int j = 0; j < tasklist.size(); j++) {
            if (tasklist.get(j).toString().contains(cmd)) {
                System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
            }
        }

    }
}
