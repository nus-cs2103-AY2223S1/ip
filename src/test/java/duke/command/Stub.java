package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

class TaskListStub extends TaskList {
    public TaskListStub() {
        super();
    }
}

class StorageStub extends Storage {
    public StorageStub(String fileName) {
        super(fileName);
    }
}

class CommandStub extends Command {
    public CommandStub() {
        super();
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "";
    };
}
