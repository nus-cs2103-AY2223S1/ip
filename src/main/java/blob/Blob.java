package blob;

import blob.commands.Command;
import blob.commands.CommandResult;
import blob.commands.TaskCommand;
import blob.exception.BlobException;
import blob.exception.ErrorLoadingTaskException;
import blob.parser.Parser;
import blob.storage.Storage;
import blob.tasks.TaskList;
import blob.ui.TextUi;

/**
 * The Blob class represents the Blob application, a chatbot to help users remember tasks.
 */
public class Blob {
    private final Storage storage;
    private final TaskList taskList;
    private final TextUi ui;
    private final Parser parser;

    Blob(String filePath) {
        TaskList taskList1;
        this.ui = new TextUi();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            taskList1 = storage.loadTaskList();
        } catch (ErrorLoadingTaskException exception) {
            ui.speakToUser(exception.getBlobMessages());
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }

    /**
     * Ends the Blob interaction.
     */
    public void end() {
        storage.saveTaskList(taskList);
        ui.sayGoodbyeToUser();
        System.exit(0);
    }

    public String[] getResponse(String input) {
        try {
            Command command = parser.parseUserInput(input);
            CommandResult result;

            if (command.isByeCommand()) {
                end();
            }

            if (command.isTaskCommand()) {
                TaskCommand taskCommand = (TaskCommand) command;
                taskCommand.setTaskList(taskList);
                result = taskCommand.execute();
                storage.saveTaskList(taskList);
            } else {
                result = command.execute();
            }
            return result.getResultMessages();
        } catch (BlobException exception) {
            return exception.getBlobMessages();
        }
    }
}
