package commands;

import arguments.Argument;
import arguments.SearchArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.Task;
import task.TaskModel;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    protected TaskModel taskModel;
    protected SearchArgument search;

    /**
     * Initialise find command
     * @param taskModel TaskModel to use
     */
    public FindCommand(TaskModel taskModel) {
        super("find");
        this.taskModel = taskModel;
    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        search = new SearchArgument(input);
        List<String> errs = Argument.validateArguments(search);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }
        List<Task> allTasks = taskModel.getAllTasks();
        if (allTasks.size() == 0) {
            return new CommandResponse("No tasks to search.");
        }
        String searchQuery = search.getParameter();
        List<String> stringList = allTasks
                .stream()
                .filter(t -> t.toString().contains(searchQuery))
                .map(t -> t.toString())
                .collect(Collectors.toList());
        if (stringList.size() == 0) {
            return new CommandResponse(String.format("No matching tasks found for query: '%s'", searchQuery));
        }
        return new CommandResponse(OutputLogger.numberedOutput(stringList));
    }
}
