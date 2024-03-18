package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.clearFile();
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            char taskType = taskList.readTask(i).charAt(1);
            String status = taskList.readStatus(i);
            String tag = taskList.getTag(i);
            switch (taskType) {
            case 'T': {
                String taskToAppend = taskList.storeIntoFileFormat("T", taskList.convertStatus(status),
                        taskList.getDescription(i), tag, "");
                storage.appendToFile(taskToAppend);
                break;
            }
            case 'D': {
                String taskToAppend = taskList.storeIntoFileFormat("D", taskList.convertStatus(status),
                        taskList.getDescription(i), tag, taskList.getDate(i));
                storage.appendToFile(taskToAppend);
                break;
            }
            case 'E': {
                String taskToAppend = taskList.storeIntoFileFormat("E", taskList.convertStatus(status),
                        taskList.getDescription(i), tag, taskList.getDate(i));
                storage.appendToFile(taskToAppend);
                break;
            }
            default:
                break;
            }
        }
        return ui.getGoodbyeMessage();
    }
}
