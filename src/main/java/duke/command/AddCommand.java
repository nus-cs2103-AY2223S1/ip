package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TasksList;

public class AddCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] words;

    public AddCommand(TasksList tasksList, Storage storage, String[] words) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.words = words;
    }

    @Override
    public void execute() throws DukeException {
        tasksList.addTask(this.words, this.storage);
    }

    public static boolean isCommand(String s) {
        return s.equals("todo") || s.equals("deadline") || s.equals("event");
    }
}
