package command;

import java.util.List;

import henry.Task;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_SUCCESS = "I'VE FOUND THESE MATCHING TASKS:\n\t\t\t %1$s";
    private final String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public CommandResult execute() {
        List<Task> tasks = taskList.getTasks();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        int i = 1;
        for (Task task : tasks) {
            if (task.toSimpleString().contains(search)) {
                sb.append(" ").append(i).append(". ").append(task).append("\n");
                i++;
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sb.toString().trim()));
    }
}
