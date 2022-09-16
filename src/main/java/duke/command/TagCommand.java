package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class TagCommand extends Command {
    private int taskIndex;
    private String tagDesc;

    public TagCommand(String desc) {
        desc = desc.replace("tag ", "");
        String numberOnly = desc.replaceAll("[^0-9]", "");
        tagDesc = desc.replace(numberOnly, "").substring(1);

        assert !numberOnly.isEmpty() : "index of task cannot be empty";
        assert !tagDesc.isEmpty() : "tag description cannot be empty";

        taskIndex = Integer.parseInt(numberOnly);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.tagInTaskList(taskIndex, tagDesc);
            storage.rebuildFile(tasks.getIterator());
            ui.nextOutput("Alright, this task is tagged:\n"
                    + tasks.getTask(taskIndex).toString() + "\n"
                    + super.nextAction);
        } catch (IOException ioe) {
            ui.nextOutput("Something went wrong: " + ioe.getMessage());
        }
    }
}
