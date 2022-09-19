package pony.command;

import java.util.ArrayList;

import pony.Parser;
import pony.PonyException;
import pony.Storage;
import pony.TaskList;
import pony.Ui;

import pony.task.Task;



/**
 * Command for Find.
 */
public class FindCommand extends Command {

    private String commandDetails;

    /**
     * Constructor for Find command.
     *
     * @param commandDetails Details of command.
     */
    public FindCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes a Find command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     * @return A list of task matching the find keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String message = "";
        try {
            String description = Parser.parseFindDetails(commandDetails).toLowerCase();
            ArrayList<Task> result = new ArrayList<>();
            for (int i = 0; i < tasks.sizeOf(); i++) {
                Task task = tasks.getTask(i);
                if (task.toString().toLowerCase().contains(description)) {
                    result.add(task);
                }
            }
            message = ui.printFindResult(result);
        } catch (PonyException e) {
            message = e.getMessage();
        }
        return message;
    }
}

