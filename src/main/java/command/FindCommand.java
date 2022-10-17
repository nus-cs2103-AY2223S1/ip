package command;

import duke.Storage;
import duke.Ui;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Command that filters out TaskList and prints out those with matching keywords.
 * It is able to filter multiple types of field such as tasklist number, the type of task
 * or the name of the task.
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

        if (cmd.equals("T")) {
            for (int j = 0; j < tasklist.size(); j++) {
                if (tasklist.get(j).toString().startsWith("[T]")) {
                    output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
                    output.append("\n");
                }
            }
            return output.toString();
        } else if (cmd.equals("E")) {
            for (int j = 0; j < tasklist.size(); j++) {
                if (tasklist.get(j).toString().startsWith("[E]")) {
                    output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
                    output.append("\n");
                }
            }
            return output.toString();
        } else if (cmd.equals("D")) {
            for (int j = 0; j < tasklist.size(); j++) {
                if (tasklist.get(j).toString().startsWith("[D]")) {
                    output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
                    output.append("\n");
                }
            }
            return output.toString();
        }

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
