package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ArchiveCommand extends Command{

    private String input;
    private TaskList archiveTasks;
    private Storage archiveStorage;
    private String intRegex = "[0-9]+";

    public ArchiveCommand(String input) {
        this.input = input.strip();

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        this.input = this.input.strip();
        StringBuilder toReturn = new StringBuilder();

        if (this.input.equalsIgnoreCase("list")) {
            return this.listArchiveTasks();
        }
        else if (this.input.equalsIgnoreCase("all")) {
            for(int i = 0; i < tasks.getCount(); i++) {
                archiveTasks.add(tasks.remove(0));
            }

            toReturn.append("You have successfully archived all tasks!\n");
            toReturn.append(this.listArchiveTasks());
            archiveStorage.write(archiveTasks);
            return toReturn.toString();
        } else if (this.input.matches(intRegex)){
            int index = Integer.parseInt(this.input);
            if (index >= tasks.getCount()) {
                throw new DukeException("This index does not exist and can't be archived! Try again with a task index that exists!");
            } else {
                Task archiveTask = (tasks.remove(index - 1));
                archiveTasks.add(archiveTask);
                toReturn.append("You have successfully archived " + archiveTask.toString() + "\n");
                toReturn.append(this.listArchiveTasks());
                archiveStorage.write(archiveTasks);
                return toReturn.toString();
            }
        }

        else {
            throw new DukeException("Oops! Seems like this archive command does not exist!\n " +
                    "Try 'archive all', 'archive (index of item to archive)' instead!\n");
        }
    }

    @Override
    public boolean isArchive() {
        return true;
    }

    @Override
    public void setArchiveTasks(TaskList archiveTasks) {
        this.archiveTasks = archiveTasks;
    }
    @Override
    public void setArchiveStorage(Storage archiveStorage) {
        this.archiveStorage = archiveStorage;
    }

    private String listArchiveTasks() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("These are the current tasks in your archive list!\n");
        for (int i = 0; i < archiveTasks.getCount(); i++) {
            if (archiveTasks.get(i) == null) {
                break;
            }
            else {
                toReturn.append((i+1) + ". " + archiveTasks.get(i).toString() + "\n");
            }
        }
        return toReturn.toString();
    }
}
