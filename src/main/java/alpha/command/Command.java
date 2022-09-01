package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

import java.io.IOException;
public abstract class Command {

    /**
     * Executes the command parsed by the Parser class.
     *
     * @param taskList Object of TaskList class to help operate on the list of tasks.
     * @param uI Object of Ui class to display messages to the user.
     * @param fileOperations Object of the FileOperations class to help read and write file.
     * @throws AlphaException  If methods of tasks or fileOperations return an AlphaException.
     */
    public abstract void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException;
}
