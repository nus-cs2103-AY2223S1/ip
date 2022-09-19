package pony.command;

import pony.Parser;
import pony.PonyException;
import pony.Storage;
import pony.Ui;
import pony.task.Task;
import pony.task.TaskList;
import pony.task.ToDo;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String commandDetails;

    public FindCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            String description = Parser.parseFindDetails(commandDetails);
            ArrayList<Task> result = new ArrayList<>();
            for (int i = 0; i < tasks.sizeOf(); i++) {
                Task task = tasks.getTask(i);
                if (task.toString().contains(description)) {
                    result.add(task);
                }
            }
            ui.printFindResult(result);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        }
    }
}

