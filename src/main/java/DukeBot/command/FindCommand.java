package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.Task;
import DukeBot.TaskList;
import DukeBot.Ui;

public class FindCommand extends Command {

    private TaskList tasks;

    private String command;

    public FindCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        String[] strings = command.split(" ", 2);
        if (strings.length != 2) {
            throw new DukeException("Find command in this format: find <task>");
        }
        String taskToFind = strings[1];
        TaskList tasksToShow = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(taskToFind)) {
                tasksToShow.add(task);
            }
        }
        return Ui.showList(tasksToShow, true);
    }
}
