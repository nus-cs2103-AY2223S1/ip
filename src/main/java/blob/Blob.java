package blob;

import java.util.Scanner;

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
     * Start the interaction with an instance of blob.Blob
     */
    public void start() {
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        while (true) {
            ui.promptUserInput();
            try {
                Command command = parser.parseUserInput(sc.nextLine());
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

                ui.speakToUser(result.getResultMessages());

            } catch (BlobException exception) {
                ui.speakToUser(exception.getBlobMessages());
            }
        }
    }

    /**
     * Ends the Blob interaction.
     */
    public void end() {
        storage.saveTaskList(taskList);
        ui.sayGoodbyeToUser();
        System.exit(0);
    }

    /**
     * Starts the main application
     *
     * @param args The additional parameters to start the application with (not used)
     */
    public static void main(String[] args) {
        Blob blob = new Blob("data/tasks.txt");
        blob.start();
    }
}
