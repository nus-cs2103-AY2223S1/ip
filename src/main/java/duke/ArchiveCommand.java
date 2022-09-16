package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ArchiveCommand extends Command {

    enum ArchiveType {
        LOAD, INDEX, ARCHIVE
    }

    ArchiveType type;
    int index;

    public ArchiveCommand (ArchiveType type) {
        this.type = type;
    }

    public ArchiveCommand (ArchiveType type, int index) {
        this.type = type;
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Adds a task to the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        switch (type) {
            case LOAD:
                return executeLoad(taskList, ui, storage);
            case ARCHIVE:
                return executeArchive(taskList, ui, storage);
            case INDEX:
                return executeArchiveTask(taskList, ui, storage);
            default:
                return "";
        }
    }

    private String executeLoad(TaskList taskList, Ui ui, Storage storage) {
        try {
            List<Task> tasks = storage.loadFromArchive();
            taskList.append(tasks);
            return ui.showTotalTasks(taskList);
        } catch (FileNotFoundException e) {
            return ui.showError("I can't find the archive file!");
        } catch (IOException e) {
            return ui.showError("I can't clear the archive file!");
        }
    }

    private String executeArchive(TaskList taskList, Ui ui, Storage storage) {
        boolean wasSaved = storage.saveToArchive(taskList);
        if (!wasSaved) {
            return ui.showError("Problem saving to archive!");
        }
        taskList.clear();
        return "archived all tasks!!";
    }

    private String executeArchiveTask(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        boolean wasSaved = storage.saveTaskToArchive(task);
        if (!wasSaved) {
            return ui.showError("Problem saving to archive!");
        }
        String response = new DeleteCommand(index).execute(taskList, ui, storage);
        response += "\n" + ui.displayTask(ui.ARCHIVED_TASK, task);
        return  response;
    }

}
