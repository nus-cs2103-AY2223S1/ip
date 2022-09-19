package pony.command;

import pony.Parser;
import pony.PonyException;
import pony.Storage;
import pony.Ui;
import pony.TaskList;
import pony.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String commandDetails;

    public FindCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

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

