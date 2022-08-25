package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> outputList = new ArrayList<Task>();
        int keywordLength = this.keyword.length();
        try {
            for (Task task : taskList.getList()) {
                String taskString = task.toString();
                int i = 0;
                while (i < taskString.length() - keywordLength) {
                    String taskSubstr = taskString.substring(i, i + keywordLength);
                    if (taskSubstr.equals(this.keyword)) {
                        outputList.add(task);
                    }
                    i++;
                }
            }
            TaskList outputTaskList = new TaskList(outputList);
            ui.printList(outputTaskList);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Oops! String Index Out Of Bounds");
        }
    }

    public boolean isExit() {
        return this.IS_EXIT;
    }

}
