package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandDelete extends Command {
    private static final String deleteMessage = "Noted. I've removed this task:";

    public CommandDelete(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        StringBuilder beforeDeleteString = new StringBuilder();
        beforeDeleteString.append(deleteMessage + "\n").append(taskList.get(taskNumber)).append("\n");
        System.out.println(HORIZONTAL_LINE + "\n" + beforeDeleteString);
        taskList.remove(taskNumber);
        StringBuilder afterDeleteString = new StringBuilder();
        afterDeleteString.append("Now you have ").append(String.valueOf(taskList.size())).append(" tasks in the list" +
                ".\n");
        System.out.println(afterDeleteString + "\n" + HORIZONTAL_LINE);
        return beforeDeleteString.append(afterDeleteString).toString();
    }

}
