package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

public class Exit extends Command {
    @Override
    public void execute(TaskList tasks, Ui uI, FileOperations fileOperations) {
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Exit) {
            return true;
        }
        return false;
    }
}
