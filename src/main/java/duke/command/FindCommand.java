package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        TaskList tasksFound = Command.taskList.find(keyword);
        if (tasksFound.size() == 0) {
            Command.ui.printMessages(new String[]{"No tasks found with keyword: " + keyword});
        } else {
            String[] toPrint = new String[tasksFound.size() + 1];
            toPrint[0] = "Tasks found with keyword: " + keyword;
            int i = 1;
            for (String line : tasksFound.getAllTasksInDisplayFormat()) {
                toPrint[i] = line;
                i++;
            }
            Command.ui.printMessages(toPrint);
        }
    }
}
