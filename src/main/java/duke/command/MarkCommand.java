package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TasksList;

public class MarkCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] words;

    public MarkCommand(TasksList tasksList, Storage storage, String[] words) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.words = words;
    }

    @Override
    public void execute() throws DukeException {
        this.tasksList.markTask(words, storage);
    }

    public static boolean isCommand(String s) {
        return s.equals("mark");
    }
}
